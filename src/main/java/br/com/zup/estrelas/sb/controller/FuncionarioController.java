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
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FuncionarioDTO;
import br.com.zup.estrelas.sb.dto.InativaFuncionarioDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.service.FuncionarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @ApiOperation(value = "Busca um funcionário")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna o funcionário"),
//            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
//            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping(path = "/{idFuncionario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Funcionario buscaFuncionario(@PathVariable Long idFuncionario) {
        return funcionarioService.buscaFuncionario(idFuncionario);
    }

    @ApiOperation(value = "Lista todos os funcionários")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Funcionario> listaFuncionarios() {
        return funcionarioService.listaFuncionarios();
    }

    @ApiOperation(value = "Adiciona um funcionário")
    @PostMapping
    public MensagemDTO adicionaFuncionario(@Valid @RequestBody FuncionarioDTO funcionario) {
        return funcionarioService.adicionaFuncionario(funcionario);
    }

    @ApiOperation(value = "Altera um funcionário")
    @PutMapping(path = "/{idFuncionario}")
    public MensagemDTO alteraFuncionario(@PathVariable Long idFuncionario,
            @Valid @RequestBody FuncionarioDTO alteraFuncionarioDTO) {
        return funcionarioService.alteraFuncionario(idFuncionario, alteraFuncionarioDTO);
    }

    @ApiOperation(value = "Inativa um funcionário")
    @PutMapping(path = "/{idFuncionario}/inativa")
    public MensagemDTO inativaFuncionario(@PathVariable Long idFuncionario,
            @Valid @RequestBody InativaFuncionarioDTO inativaFuncionarioDTO) {
        return funcionarioService.inativaFuncionario(idFuncionario, inativaFuncionarioDTO);
    }

    @ApiOperation(value = "Adiciona um serviço de um funcionário")
    @PutMapping("/{idFuncionario}/servicos")
    public MensagemDTO adicionaServicoFuncionario(@PathVariable Long idFuncionario,
            @Valid @RequestBody AdicionaServicoDTO adicionaServicoDTO) {
        return funcionarioService.adicionaServicoFuncionario(idFuncionario, adicionaServicoDTO);
    }

}
