package br.com.zup.estrelas.sb.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.ClienteDTO;
import br.com.zup.estrelas.sb.dto.InativaClienteDTO;
import br.com.zup.estrelas.sb.entity.Cliente;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.ClienteRepository;
import br.com.zup.estrelas.sb.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final String ALTERACAO_IMPOSSIVEL_CPF_JA_EXISTE =
            "A ALTERAÇÃO NÃO É POSSÍVEL, POIS JÁ EXISTE OUTRO CLIENTE COM O CPF INFORMADO!";
    private static final String CLIENTE_INEXISTENTE = "CLIENTE INEXISTENTE!";
    private static final String CLIENTE_JA_CADASTRADO = "CLIENTE JÁ CADASTRADO!";

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Cliente consultaCliente(Long idUsuario) throws RegrasDeNegocioException {
        return clienteRepository.findById(idUsuario).orElseThrow(
                () -> new RegrasDeNegocioException("Cliente não encontrado pelo Id: " + idUsuario));
    }

    @Override
    public List<Cliente> listaClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public Cliente insereCliente(ClienteDTO clienteDTO) throws RegrasDeNegocioException {

        if (clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new RegrasDeNegocioException(CLIENTE_JA_CADASTRADO);
        }

        return this.adicionaCliente(clienteDTO);
    }

    @Override
    public Cliente alteraCliente(Long idUsuario, ClienteDTO clienteDTO)
            throws RegrasDeNegocioException {

        Optional<Cliente> clienteConsultado = clienteRepository.findById(idUsuario);

        if (clienteConsultado.isEmpty()) {
            throw new RegrasDeNegocioException(CLIENTE_INEXISTENTE);
        }

        Cliente cliente = clienteConsultado.get();

        if (!clienteDTO.getCpf().equals(cliente.getCpf())
                && clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new RegrasDeNegocioException(ALTERACAO_IMPOSSIVEL_CPF_JA_EXISTE);
        }

        return this.alteraInformacoesCliente(cliente, clienteDTO);
    }

    @Override
    public Cliente inativaCliente(Long idUsuario, InativaClienteDTO inativaClienteDTO)
            throws RegrasDeNegocioException {

        Optional<Cliente> clienteConsultado = clienteRepository.findById(idUsuario);

        if (clienteConsultado.isEmpty()) {
            throw new RegrasDeNegocioException(CLIENTE_INEXISTENTE);
        }
        return inativaClienteComSucesso(clienteConsultado, inativaClienteDTO);
    }

    private Cliente adicionaCliente(ClienteDTO clienteDTO) {

        Cliente cliente = new Cliente();

        BeanUtils.copyProperties(clienteDTO, cliente);
        cliente.setSenha(passwordEncoder.encode(clienteDTO.getSenha()));
        cliente.setAgendamentos(Collections.emptyList());
        cliente.setAtivo(true);
        cliente.setTipoUsuario(clienteDTO.getTipoUsuario());

        clienteRepository.save(cliente);

        return cliente;
    }

    private Cliente alteraInformacoesCliente(Cliente cliente, ClienteDTO clienteDTO) {

        BeanUtils.copyProperties(clienteDTO, cliente);
        cliente.setSenha(passwordEncoder.encode(clienteDTO.getSenha()));

        clienteRepository.save(cliente);

        return cliente;
    }

    private Cliente inativaClienteComSucesso(Optional<Cliente> clienteConsultado,
            InativaClienteDTO inativaClienteDTO) {

        Cliente cliente = clienteConsultado.get();

        BeanUtils.copyProperties(inativaClienteDTO, cliente);

        clienteRepository.save(cliente);

        return cliente;
    }
}
