package br.com.zup.estrelas.sb.repository;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import br.com.zup.estrelas.sb.entity.Servico;

public interface ServicoRepository extends CrudRepository<Servico, Long> {
    
//    @Query ("SELECT s FROM Servico s WHERE s.nome_servico = ?1")
//    Servico findByNomeServico (String nomeServico);

}
