package br.com.zup.estrelas.sb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sb.entity.Transacao;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long>{

}
