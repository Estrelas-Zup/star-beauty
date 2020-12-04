package br.com.zup.estrelas.sb.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.service.FormaPagamentoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pagamentos")
public class FormaPagamentoController {

    @Autowired
    FormaPagamentoService formaPagamentoService;

    @ApiOperation(value = "Busca uma forma de pagamento")
    @GetMapping(path = "/{idFormaPagamento}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public FormaPagamento buscaFormaPagamento(@PathVariable Long idFormaPagamento) throws RegrasDeNegocioException {
        return formaPagamentoService.buscaFormaPagamento(idFormaPagamento);
    }

    @ApiOperation(value = "Lista todas as formas de pagamento")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<FormaPagamento> listaFormaPagamentos() {
        return formaPagamentoService.listaFormaPagamentos();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adiciona uma forma de pagamento")
    @PostMapping
    public FormaPagamento adicionaFormaPagamento(
            @Valid @RequestBody FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException {
        return formaPagamentoService.adicionaFormaPagamento(formaPagamentoDTO);
    }

    @ApiOperation(value = "Altera uma forma de pagamento")
    @PutMapping(path = "/{idFormaPagamento}")
    public FormaPagamento alteraFormaPagamento(@PathVariable Long idFormaPagamento,
            @Valid @RequestBody FormaPagamentoDTO alteraFormaPagamentoDTO) throws RegrasDeNegocioException {
        return formaPagamentoService.alteraFormaPagamento(idFormaPagamento,
                alteraFormaPagamentoDTO);
    }

    @ApiOperation(value = "Deleta uma forma de pagamento")
    @DeleteMapping(path = "/{idFormaPagamento}")
    public MensagemDTO removeFormaPagamento(@PathVariable Long idFormaPagamento)
            throws RegrasDeNegocioException {
        return formaPagamentoService.removeFormaPagamento(idFormaPagamento);
    }

}
