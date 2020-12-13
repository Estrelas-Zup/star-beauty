package br.com.zup.estrelas.sb.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.sb.entity.Cliente;
import br.com.zup.estrelas.sb.repository.ClienteRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTests {
    private static final String CADASTRO_REALIZADO_COM_SUCESSO = "CADASTRO REALIZADO COM SUCESSO.";
    private static final String CLIENTE_JA_CADASTRADO = "CLIENTE JA CADASTRADO.";
    
    @Mock
    ClienteRepository clienteRepository;
    
    @InjectMocks
    ClienteService clienteService;
    
    @Test
    public void deveInserirUmCliente() {
        
        Cliente cliente = new Cliente();
        
        cliente.setIdUsuario(1L);
        cliente.setLogin("dayana@mail.com");
        cliente.setSenha("blablabla");
        cliente.setNome("Dayana");
        cliente.setEndereco("Rua dos Abacates, 23");
        cliente.setCep("08764789");
        cliente.setEstado("SP");
        cliente.setCidade("São Pualo");
        cliente.setBairro("Jardim das flores");
        cliente.setTelefone("98677-0987");
        cliente.setEmail("dayana@mail.com");
        cliente.isAtivo();

        Mockito.when(clienteRepository.existsById(1L)).thenReturn(false);
        
       // MensagemDTO mensagemRetornada = clienteService.insereCliente(cliente);
        //MensagemDTO mensagemEsperada = new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
        
       // Assert.assertEquals("Cliente deve ser cadastrado com sucesso",mensagemEsperada, mensagemRetornada);
        
    }
    
    @Test
    public void naoDeveInserirUmCliente() {
        
        Cliente cliente = new Cliente();
        
        cliente.setIdUsuario(1L);
        cliente.setLogin("dayana@mail.com");
        cliente.setSenha("blablabla");
        cliente.setNome("Dayana");
        cliente.setEndereco("Rua dos Abacates, 23");
        cliente.setCep("08764789");
        cliente.setEstado("SP");
        cliente.setCidade("São Pualo");
        cliente.setBairro("Jardim das flores");
        cliente.setTelefone("98677-0987");
        cliente.setEmail("dayana@mail.com");
        cliente.isAtivo();

        Mockito.when(clienteRepository.existsById(1L)).thenReturn(true);
        
//        MensagemDTO mensagemRetornada = clienteService.insereCliente(cliente);
//        MensagemDTO mensagemEsperada = new MensagemDTO(CLIENTE_JA_CADASTRADO);
//        
//        Assert.assertEquals("Não deve cadastrar um cliente que já existe",mensagemEsperada, mensagemRetornada);
//        
    }
}
