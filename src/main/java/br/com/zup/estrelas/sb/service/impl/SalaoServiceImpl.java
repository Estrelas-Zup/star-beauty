package br.com.zup.estrelas.sb.service.impl;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaSalaoDTO;
import br.com.zup.estrelas.sb.dto.SalaoDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
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
    public Salao buscaSalao(Long idUsuario) throws RegrasDeNegocioException {
        return salaoRepository.findById(idUsuario).orElseThrow(() -> new RegrasDeNegocioException(
                "Não foi possível encontrar o salão pelo Id" + idUsuario));
    }

    @Override
    public List<Salao> listaSalao() {
        return (List<Salao>) salaoRepository.findAll();
    }

    @Override
    public Salao adicionaSalao(SalaoDTO salaoDTO) throws RegrasDeNegocioException {
        if (salaoRepository.existsByCnpj(salaoDTO.getCnpj())) {
            throw new RegrasDeNegocioException("SALÂO JÁ CADASTRADO!");
        }

        return criaSalao(salaoDTO);
    }

    @Override
    public Salao alteraSalao(Long idUsuario, SalaoDTO salaoDTO) throws RegrasDeNegocioException {
        if (!salaoRepository.existsById(idUsuario)) {
            throw new RegrasDeNegocioException("O SALÃO EM QUESTÂO NÂO EXISTE PARA SER ALTERADO!");
        }

        Salao salao = salaoRepository.findById(idUsuario).get();

        if (!salaoDTO.getCnpj().equals(salao.getCnpj())
                && salaoRepository.existsByCnpj(salaoDTO.getCnpj())) {
            throw new RegrasDeNegocioException("CNPJ ALTERADO JÁ EXISTE NO BANCO DE DADOS!");
        }

        return this.alteraInformacoesSalao(salao, salaoDTO);
    }

    @Override
    public Salao inativaSalao(Long idUsuario, InativaSalaoDTO inativaSalaoDTO) throws RegrasDeNegocioException {
        if (!salaoRepository.existsById(idUsuario)) {
            throw new RegrasDeNegocioException("O SALÃO EM QUESTÂO NÂO EXISTE PARAS SER INATIVADO!");
        }

        return this.finalizaInativacaoSalao(idUsuario, inativaSalaoDTO);
    }

    @Override
    public Salao adicionaFormaPagamento(Long idUsuario, FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException {

        if (!salaoRepository.existsById(idUsuario)) {
            throw new RegrasDeNegocioException("O SALÃO EM QUESTÃO NÃO FOI ENCONTRADO!");
        }

        return this.adicionaFormaPagamentoComSucesso(idUsuario, formaPagamentoDTO);
    }

    private Salao criaSalao(SalaoDTO salaoDTO) {

        Salao novoSalao = new Salao();

        BeanUtils.copyProperties(salaoDTO, novoSalao);
        novoSalao.setFormaPagamento(Collections.emptyList());
        novoSalao.setFuncionarios(Collections.emptyList());
        novoSalao.setAtivo(true);

        salaoRepository.save(novoSalao);

        return novoSalao;
    }

    private Salao alteraInformacoesSalao(Salao salao, SalaoDTO salaoDTO) {

        BeanUtils.copyProperties(salaoDTO, salao);

        salaoRepository.save(salao);

        return salao;
    }

    private Salao finalizaInativacaoSalao(Long idUsuario, InativaSalaoDTO inativaSalaoDTO) {

        Salao salao = salaoRepository.findById(idUsuario).get();
        
        salao.setAtivo(inativaSalaoDTO.isAtivo());

        salaoRepository.save(salao);

        return salao;
    }

    private Salao adicionaFormaPagamentoComSucesso(Long idUsuario,
            FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException {

        Salao salao = salaoRepository.findById(idUsuario).get();
        FormaPagamento formaPagamento =
                pagamentoRepository.findByTipoPagamento(formaPagamentoDTO.getTipoPagamento());
        List<FormaPagamento> formasPagamento = salao.getFormaPagamento();

        if (formasPagamento.contains(formaPagamento)) {
            throw new RegrasDeNegocioException("FORMA DE PAGAMENTO JÁ EXISTENTE NO CADASTRO!");
        }

        formasPagamento.add(formaPagamento);

        salaoRepository.save(salao);

        return salao;

    }

}
