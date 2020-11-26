package br.com.zup.estrelas.sb.service.imp;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.AlteraAtivoClienteDTO;
import br.com.zup.estrelas.sb.dto.ClienteDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Cliente;
import br.com.zup.estrelas.sb.repository.ClienteRepository;
import br.com.zup.estrelas.sb.service.ClienteService;

@Service
public class ClienteServiceImp implements ClienteService {

    private static final String ALTERACAO_IMPOSSIVEL_CPF_JA_EXISTE = "A alteração não é possível pois já existe outro cliente com o CPF informado.";
    private static final String CLIENTE_INATIVADO_COM_SUCESSO = "Cliente inativado com sucesso!";
    private static final String CLIENTE_JA_INATIVADO = "Esse cliente já está inativado.";
    private static final String INFORMACOES_ALTERADAS_COM_SUCESSO = "Informações alteradas com sucesso!";
    private static final String CLIENTE_INEXISTENTE = "Cliente inexistente!";
    private static final String CLIENTE_CADASTRADO_COM_SUCESSO = "Cliente cadastrado com sucesso!";
    private static final String CLIENTE_JA_CADASTRADO = "Cliente já cadastrado!";

    @Autowired
    ClienteRepository clienteRepository;

    public MensagemDTO insereCliente(ClienteDTO clienteDTO) {

        if (clienteRepository.findBycpf(clienteDTO.getCpf()).isPresent()) {
            return new MensagemDTO(CLIENTE_JA_CADASTRADO);
        }

        Cliente cliente = new Cliente();

        BeanUtils.copyProperties(clienteDTO, cliente);
        cliente.setAtivo(true);
        
        clienteRepository.save(cliente);

        return new MensagemDTO(CLIENTE_CADASTRADO_COM_SUCESSO);
    }

    public MensagemDTO alteraCliente(Long idUsuario, ClienteDTO clienteDTO) {

        Optional<Cliente> clienteConsultado = clienteRepository.findById(idUsuario);

        if (clienteConsultado.isEmpty()) {
            return new MensagemDTO(CLIENTE_INEXISTENTE);
        }
        
        Cliente cliente = clienteConsultado.get();
        
        if (clienteDTO.getCpf() == cliente.getCpf()) {
            return new MensagemDTO (ALTERACAO_IMPOSSIVEL_CPF_JA_EXISTE);
        }

        BeanUtils.copyProperties(clienteDTO, cliente);

        clienteRepository.save(cliente);

        return new MensagemDTO(INFORMACOES_ALTERADAS_COM_SUCESSO);
    }

    public MensagemDTO alteraAtivoCliente(Long idUsuario,
            AlteraAtivoClienteDTO alteraAtivoClienteClienteDTO) {

        Optional<Cliente> cliente = clienteRepository.findById(idUsuario);

        if (cliente.isEmpty()) {
            return new MensagemDTO(CLIENTE_INEXISTENTE);
        }
        
        Cliente clienteAlteraAtivo = cliente.get();
        
        if (!clienteAlteraAtivo.isAtivo()) {
            return new MensagemDTO (CLIENTE_JA_INATIVADO);
        }
        
        clienteAlteraAtivo.setAtivo(false);
        
        clienteRepository.save(clienteAlteraAtivo);

        return new MensagemDTO (CLIENTE_INATIVADO_COM_SUCESSO);
    }

    public Cliente consultaCliente(Long idUsuario) {
        return clienteRepository.findById(idUsuario).orElse(null);
    }

    public List<Cliente> listaClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }

}
