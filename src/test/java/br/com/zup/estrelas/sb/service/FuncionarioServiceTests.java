package br.com.zup.estrelas.sb.service;

import java.time.LocalTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;

public class FuncionarioServiceTests {
    private static final String CADASTRO_REALIZADO_COM_SUCESSO = "CADASTRO REALIZADO COM SUCESSO!";
    private static final String FUNCIONARIO_JA_CADASTRADO = "FUNCIONÁRIO JÁ CADASTRADO";
    private static final String FUNCIONARIO_INATIVADO_COM_SUCESSO =
            "FUNCIONÁRIO INATIVADO COM SUCESSO";
    private static final String FUNCIONARIO_INEXISTENTE = "FUNCIONARIO_INEXISTENTE";


    @Mock
    FuncionarioRepository funcionarioRepository;

    @InjectMocks
    FuncionarioService funcionarioService;

    public void deveInserirUmFuncionario() {

        Funcionario funcionario = new Funcionario();

        funcionario.setIdFuncionario(2L);
        funcionario.setNome("Dayana");
        funcionario.setCpf("34567865421");
        funcionario.setTelefone("982255600");
        funcionario.setHorarioAlmoco("12:00");

        funcionario.setHoraInicioExpediente(LocalTime.of(8, 00));
        funcionario.setHoraFimExpediente(LocalTime.of(18, 00));
        funcionario.isAtivo();

        Mockito.when(funcionarioRepository.existsById(2L)).thenReturn(false);

        MensagemDTO mensagemRetornada = funcionarioService.insereFuncionario(funcionario);
        MensagemDTO mensagemEsperada = new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);

        Assert.assertEquals("Funcionário deve cadastrado com sucesso", mensagemEsperada,
                mensagemRetornada);

    }

    @Test
    public void naoDeveInserirUmFuncionario() {

        Funcionario funcionario = new Funcionario();

        funcionario.setIdFuncionario(2L);
        funcionario.setNome("Dayana");
        funcionario.setCpf("34567865421");
        funcionario.setTelefone("982255600");
        funcionario.setHorarioAlmoco("12:00");

        funcionario.setHoraInicioExpediente(LocalTime.of(8, 00));
        funcionario.setHoraFimExpediente(LocalTime.of(18, 00));
        funcionario.isAtivo();

        Mockito.when(funcionarioRepository.existsById(2L)).thenReturn(true);

        MensagemDTO mensagemRetornada = funcionarioService.insereFuncionario(funcionario);
        MensagemDTO mensagemEsperada = new MensagemDTO(FUNCIONARIO_JA_CADASTRADO);

        Assert.assertEquals("Não deve cadastrar um funcionário que já existe", mensagemEsperada,
                mensagemRetornada);

    }

    @Test
    public void deveInativarFuncionario() {

        Funcionario funcionario = new Funcionario();

        Mockito.when(funcionarioRepository.existsById(2L)).thenReturn(true);

        MensagemDTO mensagemRetornada = funcionarioService.inativaFuncionario(funcionario);
        MensagemDTO mensagemEsperada = new MensagemDTO(FUNCIONARIO_INATIVADO_COM_SUCESSO);

        Assert.assertEquals("Funcionário deve ser inativado com sucesso", mensagemEsperada,
                mensagemRetornada);
    }

    @Test
    public void naoDeveInativarFuncionario() {

        Funcionario funcionario = new Funcionario();

        Mockito.when(funcionarioRepository.existsById(2L)).thenReturn(false);

        MensagemDTO mensagemRetornada = funcionarioService.inativaFuncionario(funcionario);
        MensagemDTO mensagemEsperada = new MensagemDTO(FUNCIONARIO_INEXISTENTE);

        Assert.assertEquals("Não deve inativar um funcionário inexistente", mensagemEsperada,
                mensagemRetornada);
    }



}
