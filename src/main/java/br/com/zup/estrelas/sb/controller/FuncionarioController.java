package br.com.zup.estrelas.sb.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.FuncionarioDTO;
import br.com.zup.estrelas.sb.dto.InativaFuncionarioDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping
    public MensagemDTO adicionaFuncionario(@Valid @RequestBody FuncionarioDTO funcionario) {
        return funcionarioService.adicionaFuncionario(funcionario);
    }

    @GetMapping(path = "/{idFuncionario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Funcionario buscaFuncionario(@PathVariable Long idFuncionario) {
        return funcionarioService.buscaFuncionario(idFuncionario);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Funcionario> listaFuncionarios() {
        return funcionarioService.listaFuncionarios();
    }

    @PutMapping(path = "/{idFuncionario}")
    public MensagemDTO alteraFuncionario(@PathVariable Long idFuncionario,
            @Valid @RequestBody FuncionarioDTO alteraFuncionarioDTO) {
        return funcionarioService.alteraFuncionario(idFuncionario, alteraFuncionarioDTO);
    }

    @PutMapping(path = "/{idFuncionario}/inativa")
    public MensagemDTO inativaFuncionario(@PathVariable Long idFuncionario,
            @Valid @RequestBody InativaFuncionarioDTO inativaFuncionarioDTO) {
        return funcionarioService.inativaFuncionario(idFuncionario, inativaFuncionarioDTO);
    }

}
