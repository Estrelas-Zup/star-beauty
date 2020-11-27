package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.TransacaoDTO;
import br.com.zup.estrelas.sb.entity.Transacao;

public interface TransacaoService {
     
    public void criaTransacao (TransacaoDTO transacaoDTO);

    public Transacao buscaTransacao (Long idTransacao);
    
    public List<Transacao> listaTransacoes();
    
    public MensagemDTO removeTransacao (Long idTransacao);
    
    public MensagemDTO alteraTransacao (Long idTransacao, TransacaoDTO transacaoDTO);

}
