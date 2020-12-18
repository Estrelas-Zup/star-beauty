package br.com.zup.estrelas.sb.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sb.entity.Agendamento;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {

    boolean existsByFuncionarioIdFuncionarioAndDataHora(Long idFuncionario,
            LocalDateTime dataHoraAgendamento);

    boolean existsByAutonomoIdUsuarioAndDataHora(Long idUsuario, LocalDateTime dataHora);

    @Query("select case when (count(a) > 0)  then true else false end from Agendamento a where a.funcionario.idFuncionario = :idFuncionario and (a.dataHora between :dataHoraInicio and :dataHoraFim or a.dataHoraFim between :dataHoraInicio and :dataHoraFim or (a.dataHora < :dataHoraInicio and a.dataHoraFim > :dataHoraInicio))")
    boolean existsByFuncionarioAgenda(
            Long idFuncionario, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);
    
    @Query("select case when (count(a) > 0)  then true else false end from Agendamento a where a.autonomo.idUsuario = :idAutonomo and (a.dataHora between :dataHoraInicio and :dataHoraFim or a.dataHoraFim between :dataHoraInicio and :dataHoraFim or (a.dataHora < :dataHoraInicio and a.dataHoraFim > :dataHoraInicio))")
    boolean existsByAutonomoAgenda(
            Long idAutonomo, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);
    
    List<Agendamento> findAllByClienteIdUsuario(Long clienteId);
}
