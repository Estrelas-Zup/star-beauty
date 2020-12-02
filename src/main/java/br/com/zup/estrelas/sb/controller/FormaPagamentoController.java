package br.com.zup.estrelas.sb.controller;

import java.util.List;
import javax.validation.Valid;
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
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.service.FormaPagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class FormaPagamentoController {

    @Autowired
    FormaPagamentoService formaPagamentoService;

    @GetMapping(path = "/{idFormaPagamento}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public FormaPagamento buscaFormaPagamento(@PathVariable Long idFormaPagamento) {
        return formaPagamentoService.buscaFormaPagamento(idFormaPagamento);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<FormaPagamento> listaFormaPagamentos() {
        return formaPagamentoService.listaFormaPagamentos();
    }

    @PostMapping
    public MensagemDTO adicionaFormaPagamento(
            @Valid @RequestBody FormaPagamentoDTO formaPagamentoDTO) {
        return formaPagamentoService.adicionaFormaPagamento(formaPagamentoDTO);
    }

    @PutMapping(path = "/{idFormaPagamento}")
    public MensagemDTO alteraFormaPagamento(@PathVariable Long idFormaPagamento,
            @Valid @RequestBody FormaPagamentoDTO alteraFormaPagamentoDTO) {
        return formaPagamentoService.alteraFormaPagamento(idFormaPagamento,
                alteraFormaPagamentoDTO);
    }

    @DeleteMapping(path = "/{idFormaPagamento}")
    public MensagemDTO removeFormaPagamento(@PathVariable Long idFormaPagamento) {
        return formaPagamentoService.removeFormaPagamento(idFormaPagamento);
    }

}
