package br.com.zup.estrelas.sb.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.TransacaoDTO;
import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.entity.Transacao;
import br.com.zup.estrelas.sb.repository.TransacaoRepository;
import br.com.zup.estrelas.sb.service.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    // private static final String TRANSACAO_REGISTRADA_COM_SUCESSO =
    // "Transação registrada com sucesso.";
    private static final String TRANSACAO_REMOVIDA_COM_SUCESSO = "Transação removida com sucesso!";
    private static final String TRANSACAO_ALTERADA_COM_SUCESSO = "Transação alterada com sucesso.";
    private static final String TRANSACAO_INEXISTENTE = "Transação inexistente.";

    @Autowired
    TransacaoRepository transacaoRepository;

    public void criaTransacao(TransacaoDTO transacaoDTO) {

        Agendamento agendamento = transacaoDTO.getAgendamento();

        if (!agendamento.isRealizado()) {
            return;
        }

        this.adicionaTransacao(transacaoDTO);
    }

    public Transacao buscaTransacao(Long idTransacao) {
        return transacaoRepository.findById(idTransacao).orElse(null);
    }

    public List<Transacao> listaTransacoes() {
        return (List<Transacao>) transacaoRepository.findAll();
    }

    public MensagemDTO removeTransacao(Long idTransacao) {

        if (!transacaoRepository.existsById(idTransacao)) {
            return new MensagemDTO(TRANSACAO_INEXISTENTE);
        }

        transacaoRepository.deleteById(idTransacao);

        return new MensagemDTO(TRANSACAO_REMOVIDA_COM_SUCESSO);
    }

    public MensagemDTO alteraTransacao(Long idTransacao, TransacaoDTO transacaoDTO) {

        if (!transacaoRepository.existsById(idTransacao)) {
            return new MensagemDTO(TRANSACAO_INEXISTENTE);
        }

        return this.alteraInformacoesTransacao(transacaoDTO);
    }

    private void adicionaTransacao(TransacaoDTO transacaoDTO) {

        Transacao transacao = new Transacao();

        BeanUtils.copyProperties(transacaoDTO, transacao);

        transacaoRepository.save(transacao);
    }

    private MensagemDTO alteraInformacoesTransacao(TransacaoDTO transacaoDTO) {

        Transacao transacao = new Transacao();

        BeanUtils.copyProperties(transacaoDTO, transacao);

        transacaoRepository.save(transacao);

        return new MensagemDTO(TRANSACAO_ALTERADA_COM_SUCESSO);
    }

}
