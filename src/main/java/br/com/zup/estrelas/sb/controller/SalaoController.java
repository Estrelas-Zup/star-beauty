package br.com.zup.estrelas.sb.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaSalaoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.SalaoDTO;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.service.SalaoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/saloes")
public class SalaoController {

    @Autowired
    SalaoService salaoService;

    @ApiOperation(value = "Busca salão")
    @GetMapping(path = "/{idUsuario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Salao buscaSalao(@PathVariable Long idUsuario) throws RegrasDeNegocioException {
        return salaoService.buscaSalao(idUsuario);
    }

    @ApiOperation(value = "Lista todos os salões")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Salao> listaSalao() throws RegrasDeNegocioException {
        return salaoService.listaSalao();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adiciona salão")
    @PostMapping
    public MensagemDTO adicionaSalao(@Valid @RequestBody SalaoDTO salaoDTO) throws RegrasDeNegocioException {
        return salaoService.adicionaSalao(salaoDTO);
    }

    @ApiOperation(value = "Altera salão")
    @PutMapping(path = "/{idUsuario}")
    public MensagemDTO alteraSalao(@PathVariable Long idUsuario,
            @Valid @RequestBody SalaoDTO salaoDTO) throws RegrasDeNegocioException {
        return salaoService.alteraSalao(idUsuario, salaoDTO);
    }

    @ApiOperation(value = "Inativa salão")
    @PutMapping(path = "/{idUsuario}/inativa")
    public MensagemDTO inativaSalao(@PathVariable Long idUsuario,
            @Valid @RequestBody InativaSalaoDTO inativaSalaoDTO) throws RegrasDeNegocioException {
        return salaoService.inativaSalao(idUsuario, inativaSalaoDTO);
    }

    @ApiOperation(value = "Adiciona forma de pagamento para o salão")
    @PutMapping(path = "/{idUsuario}/pagamentos")
    public MensagemDTO adicionaFormaPagamento(@PathVariable Long idUsuario,
            @RequestBody FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException {
        return salaoService.adicionaFormaPagamento(idUsuario, formaPagamentoDTO);
    }


}
