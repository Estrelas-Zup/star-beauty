package br.com.zup.estrelas.sb.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FuncionarioDTO;
import br.com.zup.estrelas.sb.dto.InativaFuncionarioDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.service.FuncionarioService;
import io.swagger.annotations.ApiOperation;

@RestController
@PreAuthorize("hasAuthority('salao')")
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @ApiOperation(value = "Busca um funcionário")
    @GetMapping(path = "/{idFuncionario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Funcionario buscaFuncionario(@PathVariable Long idFuncionario)
            throws RegrasDeNegocioException {
        return funcionarioService.buscaFuncionario(idFuncionario);
    }

    @ApiOperation(value = "Lista todos os funcionários")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Funcionario> listaFuncionarios() {
        return funcionarioService.listaFuncionarios();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adiciona um funcionário")
    @PostMapping
    public Funcionario adicionaFuncionario(@Valid @RequestBody FuncionarioDTO funcionario)
            throws RegrasDeNegocioException {
        return funcionarioService.insereFuncionario(funcionario);
    }

    @ApiOperation(value = "Altera um funcionário")
    @PutMapping(path = "/{idFuncionario}")
    public Funcionario alteraFuncionario(@PathVariable Long idFuncionario,
            @Valid @RequestBody FuncionarioDTO alteraFuncionarioDTO)
            throws RegrasDeNegocioException {
        return funcionarioService.alteraFuncionario(idFuncionario, alteraFuncionarioDTO);
    }

    @ApiOperation(value = "Inativa um funcionário")
    @PutMapping(path = "/{idFuncionario}/inativa")
    public Funcionario inativaFuncionario(@PathVariable Long idFuncionario,
            @Valid @RequestBody InativaFuncionarioDTO inativaFuncionarioDTO)
            throws RegrasDeNegocioException {
        return funcionarioService.inativaFuncionario(idFuncionario, inativaFuncionarioDTO);
    }

    @ApiOperation(value = "Adiciona um serviço de um funcionário")
    @PutMapping("/{idFuncionario}/servicos")
    public Funcionario adicionaServicoFuncionario(@PathVariable Long idFuncionario,
            @Valid @RequestBody AdicionaServicoDTO adicionaServicoDTO)
            throws RegrasDeNegocioException {
        return funcionarioService.adicionaServicoFuncionario(idFuncionario, adicionaServicoDTO);
    }

}
