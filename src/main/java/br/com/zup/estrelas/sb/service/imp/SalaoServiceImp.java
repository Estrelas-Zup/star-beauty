package br.com.zup.estrelas.sb.service.imp;

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
public class SalaoServiceImp implements SalaoService {

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

        MensagemDTO adicionadoComSucesso = criaSalao(salaoDTO);

        return adicionadoComSucesso;
    }

    @Override
    public MensagemDTO alteraSalao(Long idSalao, SalaoDTO salaoDTO) {
        if (!salaoRepository.existsById(idSalao)) {
            return new MensagemDTO("O SALÃO EM QUESTÂO NÂO EXISTE PARAS SER ALTERADO!");
        }

        MensagemDTO alteradoComSucesso = this.alteraInformacoesSalao(idSalao, salaoDTO);

        return alteradoComSucesso;
    }

    @Override
    public MensagemDTO removeSalao(Long idSalao) {
        if (!salaoRepository.existsById(idSalao)) {
            return new MensagemDTO("O SALÃO EM QUESTÂO NÂO EXISTE PARAS SER REMOVIDO!");
        }

        MensagemDTO removidoComSucesso = finalizaRemocaoSalao(idSalao);

        return removidoComSucesso;
    }

    @Override
    public MensagemDTO inativaSalao(Long idSalao, InativaSalaoDTO inativaSalaoDTO) {
        if (!salaoRepository.existsById(idSalao)) {
            return new MensagemDTO("O SALÃO EM QUESTÂO NÂO EXISTE PARAS SER INATIVADO!");
        }

        MensagemDTO inativadoComSucesso = this.finalizaInativacaoSalao(idSalao, inativaSalaoDTO);

        return inativadoComSucesso;
    }

    private MensagemDTO criaSalao(SalaoDTO salaoDTO) {

        Salao novoSalao = new Salao();

        BeanUtils.copyProperties(salaoDTO, novoSalao);
        novoSalao.setFormasPagamentos(Collections.emptyList());
        novoSalao.setFuncionarios(Collections.emptyList());

        salaoRepository.save(novoSalao);

        return new MensagemDTO("SALÃO CADASTRADO COM SUCESSO!");
    }

    private MensagemDTO alteraInformacoesSalao(Long idSalao, SalaoDTO salaoDTO) {

        Optional<Salao> salao = salaoRepository.findById(idSalao);

        BeanUtils.copyProperties(salaoDTO, salao);

        salaoRepository.save(salao.get());

        return new MensagemDTO("SALÃO ALERADO COM SUCESSO!");
    }

    private MensagemDTO finalizaRemocaoSalao(Long idSalao) {

        Salao salao = salaoRepository.findById(idSalao).get();

        salaoRepository.delete(salao);
        return new MensagemDTO("SALÃO REMOVIDO COM SUCESSO!");
    }

    private MensagemDTO finalizaInativacaoSalao(Long idSalao, InativaSalaoDTO inativaSalaoDTO) {

        Optional<Salao> salao = salaoRepository.findById(idSalao);

        BeanUtils.copyProperties(inativaSalaoDTO, salao);

        salaoRepository.save(salao.get());

        return new MensagemDTO("SALÃO INATIVADO COM SUCESSO!");
    }

}
