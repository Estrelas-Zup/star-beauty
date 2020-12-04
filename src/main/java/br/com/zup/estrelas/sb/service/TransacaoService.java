package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.TransacaoDTO;
import br.com.zup.estrelas.sb.entity.Transacao;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface TransacaoService {
     
    public void criaTransacao (TransacaoDTO transacaoDTO) throws RegrasDeNegocioException;

    public Transacao buscaTransacao (Long idTransacao) throws RegrasDeNegocioException;
    
    public List<Transacao> listaTransacoes();
    
    public MensagemDTO removeTransacao (Long idTransacao) throws RegrasDeNegocioException;
    
    public Transacao alteraTransacao (Long idTransacao, TransacaoDTO transacaoDTO) throws RegrasDeNegocioException;

}
