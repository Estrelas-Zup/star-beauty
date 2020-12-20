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
    
    public List<Agendamento> listaAgendamentosPorId() throws RegrasDeNegocioException;

    public Agendamento criaAgendamento(AgendamentoDTO agendamentoDTO)
            throws RegrasDeNegocioException;

    public Agendamento alteraAgendamento(Long idAgendamento, AgendamentoDTO agendamentoDTO) throws RegrasDeNegocioException;

    public Agendamento finalizaAgendamento(Long idAgendamento,
            FinalizaAgendamentoDTO finalizaAgendamentoDTO) throws RegrasDeNegocioException;

    public MensagemDTO deletaAgendamento(Long idAgendamento) throws RegrasDeNegocioException;

}
