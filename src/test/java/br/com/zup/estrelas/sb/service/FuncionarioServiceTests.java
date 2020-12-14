package br.com.zup.estrelas.sb.service;

import java.time.LocalTime;
import java.util.Optional;
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
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;
import br.com.zup.estrelas.sb.service.impl.FuncionarioServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FuncionarioServiceTests {
    private static final String FUNCIONARIO_INATIVADO_COM_SUCESSO =
            "FUNCIONÁRIO INATIVADO COM SUCESSO";
    private static final String FUNCIONARIO_INEXISTENTE = "FUNCIONÁRIO_INEXISTENTE";

    @Mock
    FuncionarioRepository funcionarioRepository;

    @InjectMocks
    FuncionarioServiceImpl funcionarioServiceImpl;

    @Test
    public void deveInserirUmFuncionario() throws RegrasDeNegocioException {

        FuncionarioDTO funcionarioDTO = this.FuncionarioDTOFactory();

        Mockito.when(funcionarioRepository.existsById(1L)).thenReturn(false);

        Funcionario funcionarioRetornado = funcionarioServiceImpl.insereFuncionario(funcionarioDTO);
        Funcionario funcionarioEsperado = new Funcionario();

        BeanUtils.copyProperties(funcionarioDTO, funcionarioEsperado);
        funcionarioEsperado.setIdFuncionario(1L);
        funcionarioEsperado.setAtivo(true);

        Assert.assertEquals(funcionarioRetornado, funcionarioEsperado);

    }

    @Test
    public void naoDeveInserirUmFuncionario() throws RegrasDeNegocioException {

        FuncionarioDTO funcionarioDTO = this.FuncionarioDTOFactory();

        Mockito.when(funcionarioRepository.existsById(1L)).thenReturn(true);

        Funcionario funcionarioRetornado = funcionarioServiceImpl.insereFuncionario(funcionarioDTO);
        Funcionario funcionarioEsperado = new Funcionario();

        BeanUtils.copyProperties(funcionarioDTO, funcionarioEsperado);
        funcionarioEsperado.setIdFuncionario(1L);
        funcionarioEsperado.setAtivo(true);

        Assert.assertEquals(funcionarioRetornado, funcionarioEsperado);
    }

    @Test
    public void deveAlterarFuncionarioComSucesso() {

        FuncionarioDTO funcionarioDTO = this.FuncionarioDTOFactory();
        Optional<Funcionario> funcionario = Optional.of(this.FuncionarioDTOFactory());

        Mockito.when(funcionarioRepository.existsById(1L)).thenReturn(true);
        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(funcionario);

        Funcionario funcionarioRetornado =
                funcionarioServiceImpl.alteraFuncionario(1L, funcionarioDTO);
        Funcionario funcionarioEsperado = new Funcionario();

        BeanUtils.copyProperties(funcionarioDTO, funcionarioEsperado);
        funcionarioEsperado.setAtivo(true);
        funcionarioRetornado.setIdFuncionario(1L);

        Assert.assertEquals(funcionarioRetornado, funcionarioEsperado);

    }

    @Test
    public void naoDeveAlterarFuncionarioComSucesso() {

        FuncionarioDTO funcionarioDTO = this.FuncionarioDTOFactory();

        Mockito.when(funcionarioRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = FUNCIONARIO_INEXISTENTE;
        String erroRetornado = null;

        try {
            funcionarioServiceImpl.alteraFuncionario(1L, funcionarioDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);
    }

    @Test
    public void inativaFuncionarioComSucesso() throws RegrasDeNegocioException {

        Mockito.when(funcionarioRepository.existsById(1L)).thenReturn(true);

        MensagemDTO mensagemRecebida = funcionarioServiceImpl.inativaFuncionario(1L, funcionarioDTO);
        MensagemDTO mensagemEsperada = new MensagemDTO(FUNCIONARIO_INATIVADO_COM_SUCESSO);

        Assert.assertEquals(mensagemRecebida, mensagemEsperada);

    }

    @Test
    public void naoDeveInativarFuncionarioComSucesso() {

        Mockito.when(funcionarioRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = FUNCIONARIO_INEXISTENTE;
        String erroRetornado = null;

        try {
            funcionarioServiceImpl.inativaFuncionario(1L, funcionarioDTO);

        } catch (RegrasDeNegocioException e) {

            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroRetornado, erroEsperado);

    }


    private FuncionarioDTO FuncionarioDTOFactory() {

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();

        funcionarioDTO.setNome("Dayana");
        funcionarioDTO.setCpf("34567865421");
        funcionarioDTO.setTelefone("982255600");
        funcionarioDTO.setHorarioAlmoco("12:00");
        funcionarioDTO.setHoraInicioExpediente(LocalTime.of(8, 00));
        funcionarioDTO.setHoraFimExpediente(LocalTime.of(18, 00));

        return funcionarioDTO;
    }

    

}
