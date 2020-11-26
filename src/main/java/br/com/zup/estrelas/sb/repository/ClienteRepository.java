package br.com.zup.estrelas.sb.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sb.entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

    Optional<Cliente> findBycpf(String cpf);

}
