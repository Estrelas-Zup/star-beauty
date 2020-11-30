package br.com.zup.estrelas.sb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;

@Repository
public interface ProfissionalAutonomoRepository extends CrudRepository<ProfissionalAutonomo, Long> {

    boolean existisByCpfCnpj(String cpfCnpj);

    ProfissionalAutonomo findByCpfCnpj(String cpfCnpj);

}
