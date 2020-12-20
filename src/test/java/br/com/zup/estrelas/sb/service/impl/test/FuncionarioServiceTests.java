package br.com.zup.estrelas.sb.service.impl.test;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FuncionarioDTO;
import br.com.zup.estrelas.sb.dto.InativaFuncionarioDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.enums.TipoServico;
import br.com.zup.estrelas.sb.enums.TipoUsuario;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;
import br.com.zup.estrelas.sb.repository.SalaoRepository;
import br.com.zup.estrelas.sb.repository.ServicoRepository;
import br.com.zup.estrelas.sb.service.impl.FuncionarioServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FuncionarioServiceTests {

    private static final String O_SALÃO_NÃO_EXISTE =
            "O SALÃO ONDE ESTA SENDO ALOCADO O FUNCIONÁRIO  NÃO EXISTE!";
    private static final String FUNCIONARIO_JA_CADASTRADO = "FUNCINÁRIO JÁ CADASTRADO!";
    private static final String FUNCIONARIO_INEXISTENTE = "FUNCIONÁRIO INEXISTENTE!";
    private static final String CPF_JÁ_EXISTE = "CPF JÁ EXISTE NO BANCO DE DADOS!";
    private static final String SERVICO_INEXISTENTE = "SERVIÇO INEXISTENTE!";

    @Mock
    FuncionarioRepository funcionarioRepository;

    @Mock
    SalaoRepository salaoRepository;

    @Mock
    ServicoRepository servicoRepository;

    @Mock
    List<Servico> servicos;

    @InjectMocks
    FuncionarioServiceImpl funcionarioServiceImpl;

    @Test
    public void insereDeveInserirUmFuncionarioComSucesso() throws RegrasDeNegocioException {

        FuncionarioDTO funcionarioDTO = this.FuncionarioDTOFactory();
        Optional<Salao> salao = Optional.of(this.criaSalao());

        Mockito.when(funcionarioRepository.existsByCpf(funcionarioDTO.getCpf())).thenReturn(false);
        Mockito.when(salaoRepository.existsById(funcionarioDTO.getIdSalao())).thenReturn(true);
        Mockito.when(salaoRepository.findById(funcionarioDTO.getIdSalao())).thenReturn(salao);

        Funcionario funcionarioRetornado = funcionarioServiceImpl.insereFuncionario(funcionarioDTO);
        Funcionario funcionarioEsperado = new Funcionario();

        BeanUtils.copyProperties(funcionarioDTO, funcionarioEsperado);
        funcionarioEsperado.setIdFuncionario(funcionarioRetornado.getIdFuncionario());
        funcionarioEsperado.setAgendamentos(Collections.emptyList());
        funcionarioEsperado.setServicos(Collections.emptyList());
        funcionarioEsperado.setAtivo(true);
        funcionarioEsperado.setSalao(salao.get());

        Assert.assertEquals(funcionarioRetornado, funcionarioEsperado);

    }

    @Test
    public void insereNaoDeveInserirUmFuncionarioComSucesso() {

        FuncionarioDTO funcionarioDTO = this.FuncionarioDTOFactory();

        Mockito.when(funcionarioRepository.existsByCpf(funcionarioDTO.getCpf())).thenReturn(true);

        String erroEsperado = FUNCIONARIO_JA_CADASTRADO;
        String erroRetornado = null;

        try {
            funcionarioServiceImpl.insereFuncionario(funcionarioDTO);
        } catch (RegrasDeNegocioException e) {

            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroRetornado, erroEsperado);

    }

    @Test
    public void alteraDeveAlterarFuncionarioComSucesso() throws RegrasDeNegocioException {

        FuncionarioDTO funcionarioDTO = this.FuncionarioDTOFactory();
        Optional<Funcionario> funcionario = Optional.of(this.FuncionarioFactory());
        Optional<Salao> salao = Optional.of(this.criaSalao());

        Assert.assertFalse(funcionarioDTO.getCpf().equals(funcionario.get().getCpf()));

        Mockito.when(funcionarioRepository.existsByCpf(funcionarioDTO.getCpf())).thenReturn(false);

        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(funcionario);

        Mockito.when(salaoRepository.existsById(1L)).thenReturn(true);

        Mockito.when(salaoRepository.findById(1L)).thenReturn(salao);

        Funcionario funcionarioRetornado =
                funcionarioServiceImpl.alteraFuncionario(1L, funcionarioDTO);
        Funcionario funcionarioEsperado = new Funcionario();

        BeanUtils.copyProperties(funcionarioDTO, funcionarioEsperado);
        funcionarioEsperado.setIdFuncionario(1L);
        funcionarioEsperado.setSalao(salao.get());
        funcionarioEsperado.setAtivo(true);

        Assert.assertEquals(funcionarioRetornado, funcionarioEsperado);

    }

    @Test
    public void alteraNaoDeveAlterarFuncionarioComSucesso() {

        FuncionarioDTO funcionarioDTO = this.FuncionarioDTOFactory();

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
    public void alteraNaoDeveAlterarFuncionarioComSucessoCasoCpfExista() {

        FuncionarioDTO funcionarioDTO = this.FuncionarioDTOFactory();
        Optional<Funcionario> funcionario = Optional.of(this.FuncionarioFactory());

        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(funcionario);
        Assert.assertFalse(funcionarioDTO.getCpf().equals(funcionario.get().getCpf()));

        Mockito.when(funcionarioRepository.existsByCpf(funcionarioDTO.getCpf())).thenReturn(true);

        String erroEsperado = CPF_JÁ_EXISTE;
        String erroRetornado = null;

        try {
            funcionarioServiceImpl.alteraFuncionario(1L, funcionarioDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);

    }

    @Test
    public void alteraNaoDeveAlterarFuncionarioComSucessoCasoSalaoNaoExista() {

        FuncionarioDTO funcionarioDTO = this.FuncionarioDTOFactory();
        Optional<Funcionario> funcionario = Optional.of(this.FuncionarioFactory());

        Mockito.when(funcionarioRepository.existsByCpf(funcionarioDTO.getCpf())).thenReturn(false);

        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(funcionario);

        Mockito.when(salaoRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = O_SALÃO_NÃO_EXISTE;
        String erroRetornado = null;

        try {
            funcionarioServiceImpl.alteraFuncionario(1L, funcionarioDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroRetornado, erroEsperado);

    }

    @Test
    public void inativaDeveInativarFuncionarioComSucesso() throws RegrasDeNegocioException {

        InativaFuncionarioDTO inativaFuncionarioDTO = new InativaFuncionarioDTO();
        inativaFuncionarioDTO.setAtivo(false);

        Optional<Funcionario> funcionario = Optional.of(this.FuncionarioFactory());

        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(funcionario);

        Funcionario funcionarioRetornado =
                funcionarioServiceImpl.inativaFuncionario(1L, inativaFuncionarioDTO);
        Funcionario funcionarioEsperado = new Funcionario();

        BeanUtils.copyProperties(funcionario.get(), funcionarioEsperado);
        funcionarioEsperado.setAtivo(inativaFuncionarioDTO.isAtivo());
        funcionarioEsperado.setIdFuncionario(funcionarioRetornado.getIdFuncionario());

        Assert.assertEquals(funcionarioRetornado, funcionarioEsperado);

    }

    @Test
    public void inativaNaoDeveInativarFuncionarioComSucessoCasoNaoExistaFuncionario() {

        InativaFuncionarioDTO inativaFuncionarioDTO = new InativaFuncionarioDTO();
        inativaFuncionarioDTO.setAtivo(false);

        String erroEsperado = FUNCIONARIO_INEXISTENTE;
        String erroRetornado = null;

        try {
            funcionarioServiceImpl.inativaFuncionario(1L, inativaFuncionarioDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroRetornado, erroEsperado);

    }

    @Test
    public void adicionaServicoDeveAdicionarServicoAoFuncionarioComSucesso()
            throws RegrasDeNegocioException {

        AdicionaServicoDTO adicionaServicoDTO = new AdicionaServicoDTO();
        adicionaServicoDTO.setIdServico(1L);

        Optional<Funcionario> funcionario = Optional.of(this.FuncionarioFactory());
        Optional<Servico> servico = Optional.of(this.criaServico());

        Mockito.when(funcionarioRepository.existsById(1L)).thenReturn(true);
        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(funcionario);
        Mockito.when(servicoRepository.existsById(1L)).thenReturn(true);
        Mockito.when(servicoRepository.findById(1L)).thenReturn(servico);
        Mockito.when(servicos.add(servico.get())).thenReturn(true);
        Mockito.when(servicos.contains(servico.get())).thenReturn(false);

        funcionario.get().setServicos(servicos);

        Funcionario funcionarioRetornado =
                funcionarioServiceImpl.adicionaServicoFuncionario(1L, adicionaServicoDTO);
        Funcionario funcionarioEsperado = new Funcionario();

        servicos.add(servico.get());
        BeanUtils.copyProperties(funcionario.get(), funcionarioEsperado);

        Assert.assertEquals(funcionarioEsperado, funcionarioRetornado);

    }

    @Test
    public void adicionaServicoNaoDeveAdicionarServicoAoFuncionarioComSucessoCasoNaoExistaFuncionario() {

        AdicionaServicoDTO adicionaServicoDTO = new AdicionaServicoDTO();
        adicionaServicoDTO.setIdServico(1L);

        Mockito.when(funcionarioRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = FUNCIONARIO_INEXISTENTE;
        String erroRetornado = null;

        try {
            funcionarioServiceImpl.adicionaServicoFuncionario(1L, adicionaServicoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);

    }

    @Test
    public void adicionaServicoNaoDeveAdicionarServicoAoFuncionarioComSucessoCasoNaoExistaServico() {

        AdicionaServicoDTO adicionaServicoDTO = new AdicionaServicoDTO();
        adicionaServicoDTO.setIdServico(1L);

        Mockito.when(funcionarioRepository.existsById(1L)).thenReturn(true);

        String erroEsperado = SERVICO_INEXISTENTE;
        String erroRetornado = null;

        try {
            funcionarioServiceImpl.adicionaServicoFuncionario(1L, adicionaServicoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);

    }

    private FuncionarioDTO FuncionarioDTOFactory() {

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();


        funcionarioDTO.setNome("Dayana");
        funcionarioDTO.setCpf("34567865421");
        funcionarioDTO.setTelefone("982255600");
        funcionarioDTO.setHorarioAlmoco("12:00");
        funcionarioDTO.setHoraInicioExpediente(LocalTime.of(8, 00));
        funcionarioDTO.setHoraFimExpediente(LocalTime.of(18, 00));
        funcionarioDTO.setIdSalao(1L);

        return funcionarioDTO;
    }

    private Funcionario FuncionarioFactory() {

        Funcionario funcionario = new Funcionario();

        funcionario.setNome("Dayana");
        funcionario.setCpf("026.917.260-29");
        funcionario.setTelefone("982255600");
        funcionario.setHorarioAlmoco("12:00");
        funcionario.setHoraInicioExpediente(LocalTime.of(8, 00));
        funcionario.setHoraFimExpediente(LocalTime.of(18, 00));
        funcionario.setIdFuncionario(1L);
        funcionario.setAtivo(true);
        funcionario.setSalao(null);


        return funcionario;
    }

    private Salao criaSalao() {

        Salao novoSalao = new Salao();

        novoSalao.setAtivo(true);
        novoSalao.setBairro("Jardim das Acácias");
        novoSalao.setCep("09766-098");
        novoSalao.setCidade("São Paulo");
        novoSalao.setCnpj("23.394.603/0001-10");
        novoSalao.setEmail("salao@mail.com");
        novoSalao.setEndereco("Avenida Dos Abacatees, 1250");
        novoSalao.setEstado("SP");
        novoSalao.setFormasPagamento(null);
        novoSalao.setFuncionarios(null);
        novoSalao.setLogin("salao@mail.com");
        novoSalao.setNome("Beleza Natural");
        novoSalao.setNomeFantasia("Star Beauty");
        novoSalao.setSenha("Timao1910");
        novoSalao.setTelefone("987647374");
        novoSalao.setTipoUsuario(TipoUsuario.SALAO);
        novoSalao.setIdUsuario(1L);

        return novoSalao;

    }

    private Servico criaServico() {

        Servico servico = new Servico();

        servico.setDuracao("120 minutos");
        servico.setIdServico(1L);
        servico.setNomeServico("Corte de cabelo");
        servico.setTipoServico(TipoServico.CABELEIREIRO);
        servico.setValorServico(50D);

        return servico;

    }
}
