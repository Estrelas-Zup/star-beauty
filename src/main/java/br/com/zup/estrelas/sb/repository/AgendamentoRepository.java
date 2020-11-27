package br.com.zup.estrelas.sb.repository;

import java.time.LocalTime;
import org.springframework.data.repository.CrudRepository;
import br.com.zup.estrelas.sb.entity.Agendamento;

public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {

    boolean existsByFuncionarioIdFuncionarioAndHoraAgendamento(Long idFuncionario,
            LocalTime horaAgendamento);

}
