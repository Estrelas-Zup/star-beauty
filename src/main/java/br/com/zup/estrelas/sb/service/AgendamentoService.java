package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.AgendamentoDTO;
import br.com.zup.estrelas.sb.dto.FinalizaAgendamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface AgendamentoService {

    public Agendamento buscaAgendamento(Long idAgendamento) throws RegrasDeNegocioException;

    public List<Agendamento> listaAgendamento();

    public MensagemDTO criaAgendamento(AgendamentoDTO agendamentoDTO)
            throws RegrasDeNegocioException;

    public MensagemDTO alteraAgendamento(Long idAgendamento, AgendamentoDTO agendamentoDTO) throws RegrasDeNegocioException;

    public MensagemDTO finalizaAgendamento(Long idAgendamento,
            FinalizaAgendamentoDTO finalizaAgendamentoDTO) throws RegrasDeNegocioException;

    public MensagemDTO deletaAgendamento(Long idAgendamento) throws RegrasDeNegocioException;

}
