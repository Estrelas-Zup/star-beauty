package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.ClienteDTO;
import br.com.zup.estrelas.sb.dto.InativaClienteDTO;
import br.com.zup.estrelas.sb.entity.Cliente;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface ClienteService {
    
    public Cliente insereCliente (ClienteDTO clienteDTO) throws RegrasDeNegocioException;
    
    public Cliente alteraCliente (Long idUsuario, ClienteDTO clienteDTO) throws RegrasDeNegocioException;
    
    public Cliente consultaCliente (Long idUsuario) throws RegrasDeNegocioException;
    
    public List<Cliente> listaClientes();
    
    public Cliente inativaCliente (Long idUsuario, InativaClienteDTO inativaClienteDTO) throws RegrasDeNegocioException;

} 
