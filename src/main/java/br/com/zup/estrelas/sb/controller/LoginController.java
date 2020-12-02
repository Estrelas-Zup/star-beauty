package br.com.zup.estrelas.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.config.JwtTokenUtil;
import br.com.zup.estrelas.sb.dto.LoginDTO;
import br.com.zup.estrelas.sb.dto.TokenDTO;
import br.com.zup.estrelas.sb.security.UsuarioDetailsServiceImpl;

@RestController
@CrossOrigin
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsuarioDetailsServiceImpl userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TokenDTO criaToken(
            @RequestBody LoginDTO informacoesLogin) throws Exception {
        autenticar(informacoesLogin.getLogin(), informacoesLogin.getSenha());
        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(informacoesLogin.getLogin());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new TokenDTO(token);
    }

    private void autenticar(String login, String senha) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(login, senha));
        } catch (DisabledException e) {
            // Tratar usuário desabilitadu.
        } catch (BadCredentialsException e) {
            // Tratar credencial inválida.
        }
    }

}
