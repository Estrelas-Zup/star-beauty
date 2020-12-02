package br.com.zup.estrelas.sb.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import br.com.zup.estrelas.sb.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    public Optional<Usuario> findByLogin(String login);

}
