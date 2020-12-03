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
import br.com.zup.estrelas.sb.dto.AgendamentoDTO;
import br.com.zup.estrelas.sb.dto.FinalizaAgendamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;

    @GetMapping(path = "/{idAgendamento}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Agendamento buscaAgendamento(@PathVariable Long idAgendamento)
            throws RegrasDeNegocioException {
        return agendamentoService.buscaAgendamento(idAgendamento);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Agendamento> listaAgendamento() {
        return agendamentoService.listaAgendamento();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MensagemDTO criaAgendamento(@Valid @RequestBody AgendamentoDTO agendamentoDTO)
            throws RegrasDeNegocioException {
        return agendamentoService.criaAgendamento(agendamentoDTO);
    }

    @PutMapping(path = "/{idAgendamento}")
    public MensagemDTO alteraAgendamento(@PathVariable Long idAgendamento,
            @Valid @RequestBody AgendamentoDTO agendamentoDTO) throws RegrasDeNegocioException {
        return agendamentoService.alteraAgendamento(idAgendamento, agendamentoDTO);
    }

    @PutMapping(path = "/{idAgendamento}/finaliza")
    public MensagemDTO finalizaAgendamento(@PathVariable Long idAgendamento,
            @Valid @RequestBody FinalizaAgendamentoDTO finalizaAgendamentoDTO)
            throws RegrasDeNegocioException {
        return agendamentoService.finalizaAgendamento(idAgendamento, finalizaAgendamentoDTO);
    }

    @DeleteMapping(path = "/{idAgendamento}/cancela")
    public MensagemDTO deletaAgendamento(@PathVariable Long idAgendamento)
            throws RegrasDeNegocioException {
        return agendamentoService.deletaAgendamento(idAgendamento);
    }
}
