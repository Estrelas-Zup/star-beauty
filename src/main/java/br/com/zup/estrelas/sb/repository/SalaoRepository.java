package br.com.zup.estrelas.sb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sb.entity.Salao;

@Repository
public interface SalaoRepository extends CrudRepository<Salao, Long> {

    boolean existsByCnpj(String cnpj);
}
