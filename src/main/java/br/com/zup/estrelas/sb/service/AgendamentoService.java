package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.AgendamentoDTO;
import br.com.zup.estrelas.sb.dto.CancelaAgendamentoDTO;
import br.com.zup.estrelas.sb.dto.FinalizaAgendamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Agendamento;

public interface AgendamentoService {

    public Agendamento buscaAgendamento(Long idAgendamento);

    public List<Agendamento> listaAgendamento();

    public MensagemDTO criaAgendamento(AgendamentoDTO agendamentoDTO);

    public MensagemDTO alteraAgendamento(Long idAgendamento, AgendamentoDTO agendamentoDTO);

    public MensagemDTO finalizaAgendamento(Long idAgendamento,
            FinalizaAgendamentoDTO finalizaAgendamentoDTO);

    public MensagemDTO cancelaAgendamento(Long idAgendamento,
            CancelaAgendamentoDTO cancelaAgendamentoDTO);
}
