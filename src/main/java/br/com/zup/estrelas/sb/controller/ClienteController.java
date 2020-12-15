package br.com.zup.estrelas.sb.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.ClienteDTO;
import br.com.zup.estrelas.sb.dto.InativaClienteDTO;
import br.com.zup.estrelas.sb.entity.Cliente;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.service.ClienteService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @ApiOperation(value = "Consulta um cliente")
    @GetMapping(path = "/{idUsuario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Cliente consultaCliente(@PathVariable Long idUsuario) throws RegrasDeNegocioException {
        return clienteService.consultaCliente(idUsuario);
    }

    @ApiOperation(value = "Lista todos os clientes")
    @PreAuthorize("hasAuthority('salao') or hasAuthority('autonomo')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Cliente> listaClientes() {
        return clienteService.listaClientes();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere um cliente")
    @PreAuthorize("hasAuthority('cliente')")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Cliente insereCliente(@Valid @RequestBody ClienteDTO clienteDTO)
            throws RegrasDeNegocioException {
        return clienteService.insereCliente(clienteDTO);
    }

    @ApiOperation(value = "Altera informações de um cliente")
    @PreAuthorize("hasAuthority('cliente')")
    @PutMapping(path = "/{idUsuario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Cliente alteraCliente(@PathVariable Long idUsuario,
            @Valid @RequestBody ClienteDTO clienteDTO) throws RegrasDeNegocioException {
        return clienteService.alteraCliente(idUsuario, clienteDTO);
    }

    @ApiOperation(value = "Inativa um cliente")
    @PreAuthorize("hasAuthority('cliente')")
    @PutMapping(path = "/{idUsuario}/inativa")
    public Cliente inativaCliente(@PathVariable Long idUsuario,
            @Valid @RequestBody InativaClienteDTO inativaClienteDTO)
            throws RegrasDeNegocioException {
        return clienteService.inativaCliente(idUsuario, inativaClienteDTO);
    }

}
