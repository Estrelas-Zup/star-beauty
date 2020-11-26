package br.com.zup.estrelas.sb.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.zup.estrelas.sb.entity.Servico;

public interface ServicoRepository extends CrudRepository<Servico, Long> {
    
    
    boolean existsByNomeServico(String nomeServico);

}
