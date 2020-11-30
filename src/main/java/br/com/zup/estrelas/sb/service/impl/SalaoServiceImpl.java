package br.com.zup.estrelas.sb.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.InativaSalaoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.SalaoDTO;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.repository.SalaoRepository;
import br.com.zup.estrelas.sb.service.SalaoService;

@Service
public class SalaoServiceImpl implements SalaoService {

    @Autowired
    SalaoRepository salaoRepository;

    @Override
    public Salao buscaSalao(Long idSalao) {
        return salaoRepository.findById(idSalao).orElse(null);
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
    public MensagemDTO alteraSalao(Long idSalao, SalaoDTO salaoDTO) {
        if (!salaoRepository.existsById(idSalao)) {
            return new MensagemDTO("O SALÃO EM QUESTÂO NÂO EXISTE PARA SER ALTERADO!");
        }

        Salao salao = salaoRepository.findById(idSalao).get();

        if (salao.getCnpj() == salaoDTO.getCnpj()) {
            return new MensagemDTO("CNPJ ALTERADO JÁ EXISTE NO BANCO DE DADOS!");
        }

        return this.alteraInformacoesSalao(salao, salaoDTO);
    }

    @Override
    public MensagemDTO inativaSalao(Long idSalao, InativaSalaoDTO inativaSalaoDTO) {
        if (!salaoRepository.existsById(idSalao)) {
            return new MensagemDTO("O SALÃO EM QUESTÂO NÂO EXISTE PARAS SER INATIVADO!");
        }

        return this.finalizaInativacaoSalao(idSalao, inativaSalaoDTO);
    }

    private MensagemDTO criaSalao(SalaoDTO salaoDTO) {

        Salao novoSalao = new Salao();

        BeanUtils.copyProperties(salaoDTO, novoSalao);
        novoSalao.setFormaPagamento(Collections.emptyList());
        novoSalao.setFuncionario(Collections.emptyList());

        salaoRepository.save(novoSalao);

        return new MensagemDTO("SALÃO CADASTRADO COM SUCESSO!");
    }

    private MensagemDTO alteraInformacoesSalao(Salao salao, SalaoDTO salaoDTO) {

        BeanUtils.copyProperties(salaoDTO, salao);

        salaoRepository.save(salao);

        return new MensagemDTO("SALÃO ALERADO COM SUCESSO!");
    }

    private MensagemDTO finalizaInativacaoSalao(Long idSalao, InativaSalaoDTO inativaSalaoDTO) {

        Optional<Salao> salao = salaoRepository.findById(idSalao);

        BeanUtils.copyProperties(inativaSalaoDTO, salao);

        salaoRepository.save(salao.get());

        return new MensagemDTO("SALÃO INATIVADO COM SUCESSO!");
    }

}
