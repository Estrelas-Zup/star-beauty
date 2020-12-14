package br.com.zup.estrelas.sb.service;

import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import br.com.zup.estrelas.sb.dto.ClienteDTO;
import br.com.zup.estrelas.sb.dto.FuncionarioDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Cliente;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.ClienteRepository;
import br.com.zup.estrelas.sb.service.impl.ClienteServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTests {
    private static final String CLIENTE_INEXISTENTE = "CLIENTE INEXISTENTE.";
    private static final String CLIENTE_INATIVADO_COM_SUCESSO = "CLIENTE INATIVADO COM SUCESSO.";

    @Mock
    ClienteRepository clienteRepository;

    @InjectMocks
    ClienteServiceImpl clienteServiceImpl;

    @Test
    public void deveInserirUmCliente() throws RegrasDeNegocioException {

        ClienteDTO clienteDTO = this.ClienteDTOFactory();

        Mockito.when(clienteRepository.existsById(1L)).thenReturn(false);

        Cliente clienteRetornado = clienteServiceImpl.insereCliente(clienteDTO);
        Cliente clienteEsperado = new Cliente();

        BeanUtils.copyProperties(clienteDTO, clienteEsperado);
        clienteEsperado.setIdUsuario(1L);
        clienteEsperado.setAtivo(true);

        Assert.assertEquals(clienteRetornado, clienteEsperado);

    }

    @Test
    public void naoDeveInserirUmCliente() throws RegrasDeNegocioException {

        ClienteDTO clienteDTO = this.ClienteDTOFactory();

        Mockito.when(clienteRepository.existsById(1L)).thenReturn(true);

        Cliente clienteRetornado = clienteServiceImpl.insereCliente(clienteDTO);
        Cliente clienteEsperado = new Cliente();

        BeanUtils.copyProperties(clienteDTO, clienteEsperado);
        clienteEsperado.setIdUsuario(1L);
        clienteEsperado.setAtivo(true);

        Assert.assertEquals(clienteRetornado, clienteEsperado);
    }

    @Test
    public void deveAlterarClienteComSucesso() {

        ClienteDTO clienteDTO = this.ClienteDTOFactory();
        Optional<Cliente> cliente = Optional.of(this.ClienteDTOFactory());

        Mockito.when(clienteRepository.existsById(1L)).thenReturn(true);
        Mockito.when(clienteRepository.findById(1L)).thenReturn(cliente);

        Cliente clienteRetornado = clienteServiceImpl.alteraCliente(1L, clienteDTO);
        Cliente clienteEsperado = new Cliente();

        BeanUtils.copyProperties(clienteDTO, clienteEsperado);
        clienteEsperado.setAtivo(true);
        clienteRetornado.setIdUsuario(1L);

        Assert.assertEquals(clienteRetornado, clienteEsperado);

    }

    @Test
    public void naoDeveAlterarFuncionarioComSucesso() {

        ClienteDTO clienteDTO = this.ClienteDTOFactory();

        Mockito.when(clienteRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = CLIENTE_INEXISTENTE;
        String erroRetornado = null;

        try {
            clienteServiceImpl.alteraCliente(1L, clienteDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);
    }

    @Test
    public void inativaClienteComSucesso() throws RegrasDeNegocioException {

        Mockito.when(clienteRepository.existsById(1L)).thenReturn(true);

        MensagemDTO mensagemRecebida = clienteServiceImpl.inativacliente(1L, clienteDTO);
        MensagemDTO mensagemEsperada = new MensagemDTO(CLIENTE_INATIVADO_COM_SUCESSO);

        Assert.assertEquals(mensagemRecebida, mensagemEsperada);

    }

    @Test
    public void naoDeveInativarFuncionarioComSucesso() {

        Mockito.when(clienteRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = CLIENTE_INEXISTENTE;
        String erroRetornado = null;

        try {
            clienteServiceImpl.inativacliente(1L, clienteDTO);

        } catch (RegrasDeNegocioException e) {

            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroRetornado, erroEsperado);

    }

    private ClienteDTO ClienteDTOFactory() {

        ClienteDTO clienteDTO = new ClienteDTO();

        // clienteDTO.setIdUsuario(1L); -> Aqui fica uma dúvida de como colocar
        clienteDTO.setLogin("dayana@mail.com");
        clienteDTO.setSenha("blablabla");
        clienteDTO.setNome("Dayana");
        clienteDTO.setEndereco("Rua dos Abacates, 23");
        clienteDTO.setCep("08764789");
        clienteDTO.setEstado("SP");
        clienteDTO.setCidade("São Pualo");
        clienteDTO.setBairro("Jardim das flores");
        clienteDTO.setTelefone("98677-0987");
        clienteDTO.setEmail("dayana@mail.com");
        // clienteDTO.isAtivo();

        return clienteDTO;
    }

}
