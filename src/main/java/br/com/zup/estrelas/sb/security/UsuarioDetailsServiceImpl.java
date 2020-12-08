package br.com.zup.estrelas.sb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.entity.Usuario;
import br.com.zup.estrelas.sb.repository.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private static final String USUÁRIO_NÃO_ENCONTRADO = "USUÁRIO NÃO ENCONTRADO!";
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(USUÁRIO_NÃO_ENCONTRADO));;

        return new UsuarioDetails(usuario);
    }



}
