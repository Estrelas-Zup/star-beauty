package br.com.zup.estrelas.sb.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.TransacaoDTO;
import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.entity.Transacao;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.AgendamentoRepository;
import br.com.zup.estrelas.sb.repository.TransacaoRepository;
import br.com.zup.estrelas.sb.service.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private static final String TRANSACAO_REMOVIDA_COM_SUCESSO = "Transação removida com sucesso!";
    private static final String TRANSACAO_ALTERADA_COM_SUCESSO = "Transação alterada com sucesso.";
    private static final String TRANSACAO_INEXISTENTE = "Transação inexistente.";

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    AgendamentoRepository agendamentoRepository;

    public Transacao buscaTransacao(Long idTransacao) throws RegrasDeNegocioException {
        return transacaoRepository.findById(idTransacao)
                .orElseThrow(() -> new RegrasDeNegocioException(
                        "Transação não pode ser encontrada pelo Id " + idTransacao));
    }

    public List<Transacao> listaTransacoes() {
        return (List<Transacao>) transacaoRepository.findAll();
    }

    public void criaTransacao(TransacaoDTO transacaoDTO) {

        Agendamento agendamento =
                agendamentoRepository.findById(transacaoDTO.getIdAgendamento()).get();

        if (!agendamento.isRealizado()) {
            return;
        }

        this.adicionaTransacao(agendamento, transacaoDTO);
    }

    public MensagemDTO alteraTransacao(Long idTransacao, TransacaoDTO transacaoDTO) throws RegrasDeNegocioException {

        if (!transacaoRepository.existsById(idTransacao)) {
           throw new RegrasDeNegocioException(TRANSACAO_INEXISTENTE);
        }

        return this.alteraInformacoesTransacao(idTransacao, transacaoDTO);
    }

    public MensagemDTO removeTransacao(Long idTransacao) throws RegrasDeNegocioException {

        if (!transacaoRepository.existsById(idTransacao)) {
            throw new RegrasDeNegocioException(TRANSACAO_INEXISTENTE);
        }

        transacaoRepository.deleteById(idTransacao);

        return new MensagemDTO(TRANSACAO_REMOVIDA_COM_SUCESSO);
    }

    private void adicionaTransacao(Agendamento agendamento, TransacaoDTO transacaoDTO) {

        Transacao transacao = new Transacao();

        BeanUtils.copyProperties(transacaoDTO, transacao);
        transacao.setAgendamento(agendamento);

        transacaoRepository.save(transacao);
    }

    private MensagemDTO alteraInformacoesTransacao(Long idTransacao, TransacaoDTO transacaoDTO) {

        Transacao transacao = transacaoRepository.findById(idTransacao).get();

        BeanUtils.copyProperties(transacaoDTO, transacao);

        transacaoRepository.save(transacao);

        return new MensagemDTO(TRANSACAO_ALTERADA_COM_SUCESSO);
    }

}
