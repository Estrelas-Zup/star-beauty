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
import br.com.zup.estrelas.sb.dto.ClienteDTO;
import br.com.zup.estrelas.sb.dto.InativaClienteDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Cliente;
import br.com.zup.estrelas.sb.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping(path = "/{idUsuario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Cliente consultaCliente(@PathVariable Long idUsuario) {
        return clienteService.consultaCliente(idUsuario);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Cliente> listaClientes() {
        return clienteService.listaClientes();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO insereCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return clienteService.insereCliente(clienteDTO);
    }

    @PutMapping(path = "/{idUsuario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO alteraCliente(@PathVariable Long idUsuario,
            @Valid @RequestBody ClienteDTO clienteDTO) {
        return clienteService.alteraCliente(idUsuario, clienteDTO);
    }

    @PutMapping(path = "/{idUsuario}/inativa")
    public MensagemDTO inativaCliente(@PathVariable Long idUsuario,
            @Valid @RequestBody InativaClienteDTO inativaClienteDTO) {
        return clienteService.inativaCliente(idUsuario, inativaClienteDTO);
    }

}
