package br.com.zup.estrelas.sb.service;

import java.time.LocalTime;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import br.com.zup.estrelas.sb.dto.FuncionarioDTO;
import br.com.zup.estrelas.sb.dto.InativaFuncionarioDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;

@RunWith(MockitoJUnitRunner.class)
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

    @Test
    public void deveInserirUmFuncionario() throws RegrasDeNegocioException {

        FuncionarioDTO funcionarioDTO = this.FuncionarioDTOFactory();

        Mockito.when(funcionarioRepository.existsById(1L)).thenReturn(false);

        Funcionario funcionarioRetornado = funcionarioService.insereFuncionario(funcionarioDTO);
        Funcionario funcionarioEsperado = new Funcionario();

        BeanUtils.copyProperties(funcionarioDTO, funcionarioEsperado);
        funcionarioEsperado.setIdFuncionario(1L);
        funcionarioEsperado.setAtivo(true);

        Assert.assertEquals(funcionarioRetornado, funcionarioEsperado);

    }



    @Test
    public void naoDeveInserirUmFuncionario() throws RegrasDeNegocioException {

        FuncionarioDTO funcionario = this.criaFuncionarioDTO();

        Mockito.when(funcionarioRepository.existsById(2L)).thenReturn(true);

        Funcionario funcionarioRetornado = funcionarioService.insereFuncionario(funcionario);
        Funcionario funcionarioEsperado = new Funcionario();

    }

    @Test
    public void deveInativarFuncionario() throws RegrasDeNegocioException {

        InativaFuncionarioDTO funcionarioInativado = new InativaFuncionarioDTO();

        Mockito.when(funcionarioRepository.existsById(2L)).thenReturn(true);

        Funcionario funcionarioRetornado =
                funcionarioService.inativaFuncionario(2L, funcionarioInativado);
        Funcionario funcionarioEsperado = new Funcionario();


    }

    @Test
    public void naoDeveInativarFuncionario() throws RegrasDeNegocioException {

        InativaFuncionarioDTO funcionarioInativado = new InativaFuncionarioDTO();

        Mockito.when(funcionarioRepository.existsById(2L)).thenReturn(false);

        Funcionario funcionarioRetornado =
                funcionarioService.inativaFuncionario(2L, funcionarioInativado);
        Funcionario funcionarioEsperado = new Funcionario();
    }


    private FuncionarioDTO criaFuncionarioDTO() {

        FuncionarioDTO funcionario = new FuncionarioDTO();

        funcionario.setNome("Dayana");
        funcionario.setCpf("34567865421");
        funcionario.setTelefone("982255600");
        funcionario.setHorarioAlmoco("12:00");
        funcionario.setHoraInicioExpediente(LocalTime.of(8, 00));
        funcionario.setHoraFimExpediente(LocalTime.of(18, 00));

        return funcionario;
    }

    private FuncionarioDTO FuncionarioDTOFactory() {
        // TODO Auto-generated method stub
        return null;
    }

}
