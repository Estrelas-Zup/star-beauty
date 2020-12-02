package br.com.zup.estrelas.sb.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaSalaoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.SalaoDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.repository.FormaPagamentoRepository;
import br.com.zup.estrelas.sb.repository.SalaoRepository;
import br.com.zup.estrelas.sb.service.SalaoService;

@Service
public class SalaoServiceImpl implements SalaoService {

    @Autowired
    SalaoRepository salaoRepository;

    @Autowired
    FormaPagamentoRepository pagamentoRepository;

    @Override
    public Salao buscaSalao(Long idUsuario) {
        return salaoRepository.findById(idUsuario).orElse(null);
    }

    @Override
    public List<Salao> listaSalao() {
        return (List<Salao>) salaoRepository.findAll();
    }

    @Override
    public MensagemDTO adicionaSalao(SalaoDTO salaoDTO) {
        if (salaoRepository.existsByCnpj(salaoDTO.getCnpj())) {
            return new MensagemDTO("SALÂO JÁ CADASTRADO!");
        }

        return criaSalao(salaoDTO);
    }

    @Override
    public MensagemDTO alteraSalao(Long idUsuario, SalaoDTO salaoDTO) {
        if (!salaoRepository.existsById(idUsuario)) {
            return new MensagemDTO("O SALÃO EM QUESTÂO NÂO EXISTE PARA SER ALTERADO!");
        }

        Salao salao = salaoRepository.findById(idUsuario).get();

        // por que a condição de alteração não funciona se comparar a diferenca entre cnpj's?
        if (!salaoDTO.getCnpj().equals(salao.getCnpj())
                && salaoRepository.existsByCnpj(salaoDTO.getCnpj())) {
            return new MensagemDTO("CNPJ ALTERADO JÁ EXISTE NO BANCO DE DADOS!");
        }

        return this.alteraInformacoesSalao(salao, salaoDTO);
    }

    @Override
    public MensagemDTO inativaSalao(Long idUsuario, InativaSalaoDTO inativaSalaoDTO) {
        if (!salaoRepository.existsById(idUsuario)) {
            return new MensagemDTO("O SALÃO EM QUESTÂO NÂO EXISTE PARAS SER INATIVADO!");
        }

        return this.finalizaInativacaoSalao(idUsuario, inativaSalaoDTO);
    }

    @Override
    public MensagemDTO adicionaFormaPagamento(Long idUsuario, FormaPagamentoDTO formaPagamentoDTO) {

        if (!salaoRepository.existsById(idUsuario)) {
            return new MensagemDTO("O SALÃO EM QUESTÃO NÃO FOI ENCONTRADO!");
        }

        return this.adicionaFormaPagamentoComSucesso(idUsuario, formaPagamentoDTO);
    }

    private MensagemDTO criaSalao(SalaoDTO salaoDTO) {

        Salao novoSalao = new Salao();

        BeanUtils.copyProperties(salaoDTO, novoSalao);
        novoSalao.setFormaPagamento(Collections.emptyList());
        novoSalao.setFuncionarios(Collections.emptyList());
        novoSalao.setAtivo(true);

        salaoRepository.save(novoSalao);

        return new MensagemDTO("SALÃO CADASTRADO COM SUCESSO!");
    }

    private MensagemDTO alteraInformacoesSalao(Salao salao, SalaoDTO salaoDTO) {

        BeanUtils.copyProperties(salaoDTO, salao);

        salaoRepository.save(salao);

        return new MensagemDTO("SALÃO ALTERADO COM SUCESSO!");
    }

    private MensagemDTO finalizaInativacaoSalao(Long idUsuario, InativaSalaoDTO inativaSalaoDTO) {

        Optional<Salao> salao = salaoRepository.findById(idUsuario);

        salao.get().setAtivo(inativaSalaoDTO.isAtivo());

        salaoRepository.save(salao.get());

        return new MensagemDTO("SALÃO INATIVADO COM SUCESSO!");
    }

    private MensagemDTO adicionaFormaPagamentoComSucesso(Long idUsuario,
            FormaPagamentoDTO formaPagamentoDTO) {

        Salao salao = salaoRepository.findById(idUsuario).get();
        FormaPagamento formaPagamento =
                pagamentoRepository.findByTipoPagamento(formaPagamentoDTO.getTipoPagamento());
        List<FormaPagamento> formasPagamento = salao.getFormaPagamento();

        if (formasPagamento.contains(formaPagamento)) {
            return new MensagemDTO("FORMA DE PAGAMENTO JÁ EXISTENTE NO CADASTRO!");
        }

        formasPagamento.add(formaPagamento);

        salaoRepository.save(salao);

        return new MensagemDTO("FORMA DE PAGAMENTO CADASTRADA COM SUCESSO!");

    }

}
