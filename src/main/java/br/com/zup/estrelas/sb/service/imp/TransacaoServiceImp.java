package br.com.zup.estrelas.sb.service.imp;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.TransacaoDTO;
import br.com.zup.estrelas.sb.entity.Transacao;
import br.com.zup.estrelas.sb.repository.TransacaoRepository;
import br.com.zup.estrelas.sb.service.TransacaoService;

@Service
public class TransacaoServiceImp implements TransacaoService {
    
    private static final String TRANSACAO_REGISTRADA_COM_SUCESSO = "Transação registrada com sucesso.";
    private static final String TRANSACAO_REMOVIDA_COM_SUCESSO = "Transação removida com sucesso!";
    private static final String TRANSACAO_ALTERADA_COM_SUCESSO = "Transação alterada com sucesso.";
    private static final String TRANSACAO_INEXISTENTE = "Transação inexistente.";

    @Autowired
    TransacaoRepository transacaoRepository;

    public MensagemDTO criaTransacao(TransacaoDTO transacaoDTO) {
        Transacao transacao = new Transacao();
        BeanUtils.copyProperties(transacaoDTO, transacao);
        transacaoRepository.save(transacao);
        
        return new MensagemDTO (TRANSACAO_REGISTRADA_COM_SUCESSO);
    }

    public Optional<Transacao> buscaTransacao(Long idTransacao) {
        
        return transacaoRepository.findById(idTransacao);
    }

    public List<Transacao> listaTransacoes() {
        
        return (List<Transacao>) transacaoRepository.findAll();
    }

    public MensagemDTO removeTransacao(Long idTransacao) {
        if (!transacaoRepository.existsById(idTransacao)) {
            return new MensagemDTO (TRANSACAO_INEXISTENTE);
        }
        transacaoRepository.deleteById(idTransacao);
        
        return new MensagemDTO (TRANSACAO_REMOVIDA_COM_SUCESSO);
    }

    public MensagemDTO alteraTransacao(Long idTransacao, TransacaoDTO transacaoDTO) {
        
        if (transacaoRepository.existsById(idTransacao)) {
            
            return new MensagemDTO (TRANSACAO_INEXISTENTE);
        }
        
        Transacao transacao = new Transacao();
        BeanUtils.copyProperties(transacaoDTO, transacao);
        
        transacaoRepository.save(transacao);
        
        return new MensagemDTO (TRANSACAO_ALTERADA_COM_SUCESSO);
    }

}
