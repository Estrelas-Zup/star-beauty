package br.com.zup.estrelas.sb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sb.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long>{
    
    boolean existsByCpf(String cpf);    

}
