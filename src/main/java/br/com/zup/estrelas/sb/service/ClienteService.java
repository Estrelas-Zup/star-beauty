package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.ClienteDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Cliente;

public interface ClienteService {
    
    public MensagemDTO insereCliente (ClienteDTO clienteDTO);
    
    public MensagemDTO alteraCliente (Long idUsuario, ClienteDTO clienteDTO);
    
    public Cliente consultaCliente (Long idUsuario);
    
    public List<Cliente> listaClientes();

} 
