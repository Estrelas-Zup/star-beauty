package br.com.zup.estrelas.sb.controller;

import java.util.List;
import javax.annotation.security.DenyAll;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.TransacaoDTO;
import br.com.zup.estrelas.sb.entity.Transacao;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.service.TransacaoService;
import io.swagger.annotations.ApiOperation;

@RestController
@DenyAll
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @ApiOperation(value = "Consulta transação")
    @GetMapping(path = "/{idTransacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Transacao consultaTransacao(@PathVariable Long idTransacao)
            throws RegrasDeNegocioException {
        return transacaoService.buscaTransacao(idTransacao);
    }

    @ApiOperation(value = "Lista todas as transações")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Transacao> listaTransacoes() {
        return transacaoService.listaTransacoes();
    }

    @ApiOperation(value = "Altera transação")
    @PutMapping(path = "/{idTransacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Transacao alteraTransacao(@PathVariable Long idTransacao,
            @Valid @RequestBody TransacaoDTO transacaoDTO) throws RegrasDeNegocioException {
        return transacaoService.alteraTransacao(idTransacao, transacaoDTO);
    }

    @ApiOperation(value = "Remove transação")
    @DeleteMapping(path = "/{idTransacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO removeTransacao(@PathVariable Long idTransacao)
            throws RegrasDeNegocioException {
        return transacaoService.removeTransacao(idTransacao);
    }

}
