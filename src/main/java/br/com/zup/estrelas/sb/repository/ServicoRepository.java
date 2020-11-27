package br.com.zup.estrelas.sb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sb.entity.Servico;

@Repository
public interface ServicoRepository extends CrudRepository<Servico, Long> {

    boolean existsByNomeServico(String nomeServico);

}
