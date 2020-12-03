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
import br.com.zup.estrelas.sb.dto.AgendamentoDTO;
import br.com.zup.estrelas.sb.dto.FinalizaAgendamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.service.AgendamentoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;

    @ApiOperation(value = "Busca um agendamento")
    @GetMapping(path = "/{idAgendamento}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Agendamento buscaAgendamento(@PathVariable Long idAgendamento) {
        return agendamentoService.buscaAgendamento(idAgendamento);
    }

    @ApiOperation(value = "Lista todos os agendamentos")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Agendamento> listaAgendamento() {
        return agendamentoService.listaAgendamento();
    }

    @ApiOperation(value = "Cria um agendamento")
    @PostMapping
    public MensagemDTO criaAgendamento(@Valid @RequestBody AgendamentoDTO agendamentoDTO) {
        return agendamentoService.criaAgendamento(agendamentoDTO);
    }

    @ApiOperation(value = "Altera um agendamento")
    @PutMapping(path = "/{idAgendamento}")
    public MensagemDTO alteraAgendamento(@PathVariable Long idAgendamento,
            @Valid @RequestBody AgendamentoDTO agendamentoDTO) {
        return agendamentoService.alteraAgendamento(idAgendamento, agendamentoDTO);
    }

    @ApiOperation(value = "Finaliza um agendamento")
    @PutMapping(path = "/{idAgendamento}/finaliza")
    public MensagemDTO finalizaAgendamento(@PathVariable Long idAgendamento,
            @Valid @RequestBody FinalizaAgendamentoDTO finalizaAgendamentoDTO) {
        return agendamentoService.finalizaAgendamento(idAgendamento, finalizaAgendamentoDTO);
    }

    @ApiOperation(value = "Deleta um agendamento")
    @DeleteMapping(path = "/{idAgendamento}/cancela")
    public MensagemDTO deletaAgendamento(@PathVariable Long idAgendamento) {
        return agendamentoService.deletaAgendamento(idAgendamento);
    }
}
