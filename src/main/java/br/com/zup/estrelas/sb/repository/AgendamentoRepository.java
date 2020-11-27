package br.com.zup.estrelas.sb.repository;

import java.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sb.entity.Agendamento;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {

    boolean existsByFuncionarioIdFuncionarioAndDataHora(Long idFuncionario,
            LocalDateTime dataHoraAgendamento);

}
