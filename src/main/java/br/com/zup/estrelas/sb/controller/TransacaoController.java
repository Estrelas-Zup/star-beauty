package br.com.zup.estrelas.sb.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.TransacaoDTO;
import br.com.zup.estrelas.sb.entity.Transacao;
import br.com.zup.estrelas.sb.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
    
    @Autowired
    TransacaoService transacaoService;
    
    @PostMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO criaTransacao (@RequestBody TransacaoDTO transacaoDTO) {
        
        return transacaoService.criaTransacao(transacaoDTO);
    }
    
    @PutMapping (path = "/{idTransacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO alteraTransacao (@PathVariable Long idTransacao, @RequestBody TransacaoDTO transacaoDTO) {
        
        return transacaoService.alteraTransacao(idTransacao, transacaoDTO);
    }
    
    @GetMapping (path = "/{idTransacao}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Optional<Transacao> consultaTransacao (@PathVariable Long idTransacao) {
        
        return transacaoService.buscaTransacao(idTransacao);
        
    }
    
    @GetMapping (produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Transacao> listaTransacoes () {
        
        return transacaoService.listaTransacoes();
    }
    
    @DeleteMapping (path = "/{idTransacao}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public MensagemDTO removeTransacao (@PathVariable Long idTransacao) {
        
        return transacaoService.removeTransacao(idTransacao);
    }

}
