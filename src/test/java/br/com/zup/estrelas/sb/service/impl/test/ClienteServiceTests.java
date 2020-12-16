package br.com.zup.estrelas.sb.service.impl.test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.zup.estrelas.sb.dto.ClienteDTO;
import br.com.zup.estrelas.sb.dto.InativaClienteDTO;
import br.com.zup.estrelas.sb.entity.Cliente;
import br.com.zup.estrelas.sb.enums.TipoUsuario;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.ClienteRepository;
import br.com.zup.estrelas.sb.service.impl.ClienteServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTests {

	private static final String CPF_JA_EXISTE = "A ALTERAÇÃO NÃO É POSSÍVEL, POIS JÁ EXISTE OUTRO CLIENTE COM O CPF INFORMADO!";
	private static final String CLIENTE_INEXISTENTE = "CLIENTE INEXISTENTE!";
	private static final String CLIENTE_JA_CADASTRADO = "CLIENTE JÁ CADASTRADO!";

	@Mock
	ClienteRepository clienteRepository;

	@Mock
	PasswordEncoder passwordEncoder;

	@InjectMocks
	ClienteServiceImpl clienteServiceImpl;

	@Test
	public void insereDeveInserirUmCliente() throws RegrasDeNegocioException {

		ClienteDTO clienteDTO = this.ClienteDTOFactory();

		Mockito.when(clienteRepository.existsByCpf(clienteDTO.getCpf())).thenReturn(false);

		Cliente clienteRetornado = clienteServiceImpl.insereCliente(clienteDTO);
		Cliente clienteEsperado = new Cliente();

		BeanUtils.copyProperties(clienteDTO, clienteEsperado);
		clienteEsperado.setAtivo(true);
		clienteEsperado.setAgendamentos(Collections.emptyList());

		Assert.assertEquals(clienteEsperado, clienteRetornado);

	}

	@Test
	public void insereNaoDeveInserirUmCliente() {

		ClienteDTO clienteDTO = this.ClienteDTOFactory();

		Mockito.when(clienteRepository.existsByCpf(clienteDTO.getCpf())).thenReturn(true);

		String erroEsperado = CLIENTE_JA_CADASTRADO;
		String erroRetornado = null;

		try {
			clienteServiceImpl.insereCliente(clienteDTO);
		} catch (RegrasDeNegocioException e) {
			erroRetornado = e.getMensagemErro();
		}

		Assert.assertEquals(erroEsperado, erroRetornado);
	}

	@Test
	public void alteraDeveAlterarClienteComSucesso() throws RegrasDeNegocioException {

		ClienteDTO clienteDTO = this.ClienteDTOFactory();
		Optional<Cliente> cliente = Optional.of(this.clienteFactory());

		Mockito.when(clienteRepository.findById(1L)).thenReturn(cliente);

		Cliente clienteRetornado = clienteServiceImpl.alteraCliente(1L, clienteDTO);
		Cliente clienteEsperado = new Cliente();

		BeanUtils.copyProperties(cliente.get(), clienteEsperado);

		Assert.assertEquals(clienteRetornado, clienteEsperado);

	}

	@Test
	public void alteraNaoDeveAlterarFuncionarioComSucessoCasoNaoExista() {

		ClienteDTO clienteDTO = this.ClienteDTOFactory();

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
	public void alteraNaoDeveAlterarClienteComSucessoCasoCpfSejaIgual() {

		ClienteDTO clienteDTO = this.ClienteDTOFactory();

		Optional<Cliente> cliente = Optional.of(this.clienteFactory());

		Mockito.when(clienteRepository.findById(1L)).thenReturn(cliente);

		Assert.assertFalse(clienteDTO.getCpf().equals(cliente.get().getCpf()));

		Mockito.when(clienteRepository.existsByCpf(clienteDTO.getCpf())).thenReturn(true);

		String erroEsperado = CPF_JA_EXISTE;
		String erroRetornado = null;

		try {
			clienteServiceImpl.alteraCliente(1L, clienteDTO);
		} catch (RegrasDeNegocioException e) {
			erroRetornado = e.getMensagemErro();
		}

		Assert.assertEquals(erroEsperado, erroRetornado);

	}

	@Test
	public void inativaDeveInativarClienteComSucesso() throws RegrasDeNegocioException {

		InativaClienteDTO inativaClienteDTO = new InativaClienteDTO();
		inativaClienteDTO.setAtivo(false);

		Optional<Cliente> cliente = Optional.of(this.clienteFactory());

		Mockito.when(clienteRepository.findById(1L)).thenReturn(cliente);
		Cliente clienteRetornado = clienteServiceImpl.inativaCliente(1L, inativaClienteDTO);
		Cliente clienteEsperado = new Cliente();

		BeanUtils.copyProperties(cliente.get(), clienteEsperado);
		clienteEsperado.setAtivo(inativaClienteDTO.isAtivo());

		Assert.assertEquals(clienteEsperado, clienteRetornado);

	}

	@Test
	public void inativaNaoDeveInativarClienteComSucessoCasoNaoExista() {

		InativaClienteDTO inativaClienteDTO = new InativaClienteDTO();
		inativaClienteDTO.setAtivo(false);

		String erroEsperado = CLIENTE_INEXISTENTE;
		String erroRetornado = null;
		
		try {
			clienteServiceImpl.inativaCliente(1L, inativaClienteDTO);
		} catch (RegrasDeNegocioException e) {
			erroRetornado = e.getMensagemErro();
		}

		Assert.assertEquals(erroEsperado, erroRetornado);
	}

	private ClienteDTO ClienteDTOFactory() {

		ClienteDTO clienteDTO = new ClienteDTO();

		clienteDTO.setLogin("dayana@mail.com");
		clienteDTO.setSenha(passwordEncoder.encode("blablabla"));
		clienteDTO.setNome("Dayana");
		clienteDTO.setEndereco("Rua dos Abacates, 23");
		clienteDTO.setCep("08764789");
		clienteDTO.setEstado("SP");
		clienteDTO.setCidade("São Pualo");
		clienteDTO.setBairro("Jardim das flores");
		clienteDTO.setTelefone("98677-0987");
		clienteDTO.setEmail("dayana@mail.com");
		clienteDTO.setTipoUsuario(TipoUsuario.CLIENTE);
		clienteDTO.setCpf("684.129.000-30");
		clienteDTO.setDataNascimento(LocalDate.of(1987, 04, 02));

		return clienteDTO;
	}

	private Cliente clienteFactory() {

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
		cliente.setTipoUsuario(TipoUsuario.CLIENTE);
		cliente.setCpf("995.396.170-06");
		cliente.setDataNascimento(LocalDate.of(1987, 04, 02));
		cliente.setAgendamentos(Collections.emptyList());

		return cliente;

	}

}
