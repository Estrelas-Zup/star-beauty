package br.com.zup.estrelas.sb.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;

public interface ProfissionalAutonomoRepository extends CrudRepository<ProfissionalAutonomo, Long> {

    boolean existisByCpf(String cpf);

    ProfissionalAutonomo findByCpf(String cpf);

}
