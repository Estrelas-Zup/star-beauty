package br.com.zup.estrelas.sb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.AgendamentoDTO;
import br.com.zup.estrelas.sb.dto.CancelaAgendamentoDTO;
import br.com.zup.estrelas.sb.dto.FinalizaAgendamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;

    @GetMapping(path = "/{idAgendamento}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Agendamento buscaAgendamento(@PathVariable Long idAgendamento) {
        return agendamentoService.buscaAgendamento(idAgendamento);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Agendamento> listaAgendamento() {
        return agendamentoService.listaAgendamento();
    }

    @PostMapping
    public MensagemDTO criaAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        return agendamentoService.criaAgendamento(agendamentoDTO);
    }

    @PutMapping(path = "/{idAgendamento}")
    public MensagemDTO alteraAgendamento(@PathVariable Long idAgendamento,
            @RequestBody AgendamentoDTO agendamentoDTO) {
        return agendamentoService.alteraAgendamento(idAgendamento, agendamentoDTO);
    }

    @PutMapping(path = "/{idAgendamento}/finaliza")
    public MensagemDTO finalizaAgendamento(@PathVariable Long idAgendamento,
            @RequestBody FinalizaAgendamentoDTO finalizaAgendamentoDTO) {
        return agendamentoService.finalizaAgendamento(idAgendamento, finalizaAgendamentoDTO);
    }

    @PutMapping(path = "/{idAgendamento}/cancela")
    public MensagemDTO cancelaAgendamento(@PathVariable Long idAgendamento,
            @RequestBody CancelaAgendamentoDTO cancelaAgendamentoDTO) {
        return agendamentoService.cancelaAgendamento(idAgendamento, cancelaAgendamentoDTO);
    }

}
