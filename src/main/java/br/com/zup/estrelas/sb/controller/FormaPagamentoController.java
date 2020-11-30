package br.com.zup.estrelas.sb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

//    @PostMapping
//    public MensagemDTO adicionaFormaPagamento(@RequestBody FormaPagamentoDTO formaPagamentoDTO) {
//        return formaPagamentoService.adicionaFormaPagamento(formaPagamentoDTO);
//    }

    @GetMapping(path = "/{idFormaPagamento}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public FormaPagamento buscaFormaPagamento(@PathVariable Long idFormaPagamento) {
        return formaPagamentoService.buscaFormaPagamento(idFormaPagamento);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<FormaPagamento> listaFormaPagamentos() {
        return formaPagamentoService.listaFormaPagamentos();
    }

    @DeleteMapping(path = "/{idFormaPagamento}")
    public MensagemDTO removeFormaPagamento(@PathVariable Long idFormaPagamento) {
        return formaPagamentoService.removeFormaPagamento(idFormaPagamento);
    }

    @PutMapping(path = "/{idFormaPagamento}")
    public MensagemDTO alteraFormaPagamento(@PathVariable Long idFormaPagamento,
            @RequestBody FormaPagamentoDTO alteraFormaPagamentoDTO) {
        return formaPagamentoService.alteraFormaPagamento(idFormaPagamento,
                alteraFormaPagamentoDTO);
    }
}
