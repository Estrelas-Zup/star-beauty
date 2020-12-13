package br.com.zup.estrelas.sb.service.impl.test;

import java.time.LocalDate;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.dto.ProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.enums.TipoPagamento;
import br.com.zup.estrelas.sb.enums.TipoServico;
import br.com.zup.estrelas.sb.enums.TipoUsuario;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.FormaPagamentoRepository;
import br.com.zup.estrelas.sb.repository.ProfissionalAutonomoRepository;
import br.com.zup.estrelas.sb.repository.ServicoRepository;
import br.com.zup.estrelas.sb.service.impl.ProfissionalAutonomoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AutonomoServiceImplTest {

    private static final String SERVICO_INEXISTENTE = "SERVIÇO A SER ADICIONADO É INEXISTENTE!";
    private static final String CPF_JA_CADASTRADO_NO_BANCO_DE_DADOS =
            "CPF JÁ CADASTRADO NO BANCO DE DADOS!";
    private static final String PROFISSIONAL_NAO_ENCONTRADO =
            "O PROFISSIONAL NÃO FOI ENCONTRADO! VERIFIQUE AS INFORMAÇÕES INSERIDAS.";
    private static final String PROFISSIONAL_JA_EXISTE_NO_BANCO_DE_DADOS =
            "ESTE PROFISSIONAL JÁ EXISTE NO BANCO DE DADOS!";

    @Mock
    ProfissionalAutonomoRepository autonomoRepository;

    @Mock
    ServicoRepository servicoRepository;

    @Mock
    FormaPagamentoRepository pagamentoRepository;

    @Mock
    List<Servico> servicos;

    @Mock
    List<FormaPagamento> formasPagamento;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    ProfissionalAutonomoServiceImpl autonomoServiceImpl;

    @Test
    public void insereAutonomoDeveInserirComSucesso() throws RegrasDeNegocioException {

        ProfissionalAutonomoDTO autonomoDTO = this.autonomoDTOFactory();

        Mockito.when(autonomoRepository.existsByCpf(autonomoDTO.getCpf())).thenReturn(false);

        ProfissionalAutonomo autonomoRetornado =
                autonomoServiceImpl.insereProfissionalAutonomo(autonomoDTO);
        ProfissionalAutonomo autonomoEsperado = new ProfissionalAutonomo();

        BeanUtils.copyProperties(autonomoDTO, autonomoEsperado);
        autonomoEsperado.setIdUsuario(autonomoRetornado.getIdUsuario());
        autonomoEsperado.setAgendamentos(Collections.emptyList());
        autonomoEsperado.setFormasPagamento(Collections.emptyList());
        autonomoEsperado.setServicos(Collections.emptyList());
        autonomoEsperado.setAtivo(true);

        Assert.assertEquals(autonomoEsperado, autonomoRetornado);
    }

    @Test
    public void insereAutonomoNaoDeveInserirComSucesso() {

        ProfissionalAutonomoDTO autonomoDTO = this.autonomoDTOFactory();

        Mockito.when(autonomoRepository.existsByCpf(autonomoDTO.getCpf())).thenReturn(true);

        String erroEsperado = PROFISSIONAL_JA_EXISTE_NO_BANCO_DE_DADOS;
        String erroRetornado = null;

        try {
            autonomoServiceImpl.insereProfissionalAutonomo(autonomoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);

    }

    @Test
    public void alteraAutonomoDeveAlterarComSucesso() throws RegrasDeNegocioException {

        ProfissionalAutonomoDTO autonomoDTO = this.autonomoDTOFactory();
        Optional<ProfissionalAutonomo> autonomo = Optional.of(this.autonomoFactory());

        Mockito.when(autonomoRepository.existsById(1L)).thenReturn(true);

        Mockito.when(autonomoRepository.findById(1L)).thenReturn(autonomo);

        Assert.assertFalse(!autonomoDTO.getCpf().equals(autonomo.get().getCpf()));

        Mockito.when(autonomoRepository.existsByCpf(autonomoDTO.getCpf())).thenReturn(false);

        ProfissionalAutonomo autonomoRetornado =
                autonomoServiceImpl.alteraProfissionalAutonomo(1L, autonomoDTO);

        ProfissionalAutonomo autonomoEsperado = new ProfissionalAutonomo();

        BeanUtils.copyProperties(autonomoDTO, autonomoEsperado);
        autonomoEsperado.setIdUsuario(autonomoRetornado.getIdUsuario());
        autonomoEsperado.setAgendamentos(Collections.emptyList());
        autonomoEsperado.setFormasPagamento(Collections.emptyList());
        autonomoEsperado.setServicos(Collections.emptyList());
        autonomoEsperado.setAtivo(true);

        Assert.assertEquals(autonomoEsperado, autonomoRetornado);
    }

    @Test
    public void alteraAutonomoNaoDeveAlterarComSucessoCasoExista() {

        ProfissionalAutonomoDTO autonomoDTO = this.autonomoDTOFactory();

        Mockito.when(autonomoRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = PROFISSIONAL_NAO_ENCONTRADO;
        String erroRetornado = null;

        try {
            autonomoServiceImpl.alteraProfissionalAutonomo(1L, autonomoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);
    }

    @Test
    public void alteraAutonomoNaoDeveAlterarComSucessoCasoCpfAlteradoExista() {

        ProfissionalAutonomoDTO autonomoDTO = this.autonomoDTOFactory();
        Optional<ProfissionalAutonomo> autonomo = Optional.of(this.autonomoFactory());
        autonomo.get().setCpf("603.125.970-29");

        Mockito.when(autonomoRepository.existsById(1L)).thenReturn(true);

        Mockito.when(autonomoRepository.findById(1L)).thenReturn(autonomo);

        Assert.assertTrue(!autonomoDTO.getCpf().equals(autonomo.get().getCpf()));

        Mockito.when(autonomoRepository.existsByCpf(autonomoDTO.getCpf())).thenReturn(true);

        String erroEsperado = CPF_JA_CADASTRADO_NO_BANCO_DE_DADOS;
        String erroRetornado = null;

        try {
            autonomoServiceImpl.alteraProfissionalAutonomo(1L, autonomoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);
    }

    @Test
    public void inativaAutonomoDeveInativarAutonomoComSucesso() throws RegrasDeNegocioException {

        InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO =
                new InativaProfissionalAutonomoDTO();
        inativaProfissionalAutonomoDTO.setAtivo(false);

        Optional<ProfissionalAutonomo> autonomo = Optional.of(this.autonomoFactory());

        Mockito.when(autonomoRepository.existsById(1L)).thenReturn(true);

        Mockito.when(autonomoRepository.findById(1L)).thenReturn(autonomo);

        ProfissionalAutonomo autonomoRetornado =
                autonomoServiceImpl.inativaProfissionalAutonomo(1L, inativaProfissionalAutonomoDTO);
        ProfissionalAutonomo autonomoEsperado = new ProfissionalAutonomo();

        BeanUtils.copyProperties(autonomo.get(), autonomoEsperado);

        Assert.assertEquals(autonomoEsperado, autonomoRetornado);
    }

    @Test
    public void inativaAutonomoNaoDeveInativarAutonomoComSucesso() {

        InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO =
                new InativaProfissionalAutonomoDTO();
        inativaProfissionalAutonomoDTO.setAtivo(false);

        Mockito.when(autonomoRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = PROFISSIONAL_NAO_ENCONTRADO;
        String erroRetornado = null;

        try {
            autonomoServiceImpl.inativaProfissionalAutonomo(1L, inativaProfissionalAutonomoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);
    }

    @Test
    public void adicionaServicoDeveAdicionarServicoAoProfissional()
            throws RegrasDeNegocioException {

        AdicionaServicoDTO adicionaServicoDTO = new AdicionaServicoDTO();
        adicionaServicoDTO.setIdServico(1L);

        Optional<Servico> servico = Optional.of(this.criaServico());

        Optional<ProfissionalAutonomo> autonomo = Optional.of(this.autonomoFactory());

        Mockito.when(autonomoRepository.existsById(1L)).thenReturn(true);

        Mockito.when(servicoRepository.existsById(1L)).thenReturn(true);

        Mockito.when(autonomoRepository.findById(1L)).thenReturn(autonomo);

        Mockito.when(servicoRepository.findById(1L)).thenReturn(servico);

        Mockito.when(servicos.add(servico.get())).thenReturn(true);

        Mockito.when(servicos.contains(servico.get())).thenReturn(false);

        autonomo.get().setServicos(servicos);

        ProfissionalAutonomo autonomoRetornado =
                autonomoServiceImpl.adicionaServicoProfissionalAutonomo(1L, adicionaServicoDTO);

        ProfissionalAutonomo autonomoEsperado = new ProfissionalAutonomo();

        BeanUtils.copyProperties(autonomo.get(), autonomoEsperado);

        Assert.assertEquals(autonomoEsperado, autonomoRetornado);
    }

    @Test
    public void adicionaServicoNaoDeveAdicionarServicoAoProfissionalCasoNaoExista() {

        AdicionaServicoDTO adicionaServicoDTO = new AdicionaServicoDTO();
        adicionaServicoDTO.setIdServico(1L);

        Mockito.when(autonomoRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = PROFISSIONAL_NAO_ENCONTRADO;
        String erroRetornado = null;

        try {
            autonomoServiceImpl.adicionaServicoProfissionalAutonomo(1L, adicionaServicoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);
    }

    @Test
    public void adicionaServicoNaoDeveAdicionarServicoAoProfissionalCasoServicoNaoExista() {

        AdicionaServicoDTO adicionaServicoDTO = new AdicionaServicoDTO();
        adicionaServicoDTO.setIdServico(1L);

        Mockito.when(autonomoRepository.existsById(1L)).thenReturn(true);

        Mockito.when(servicoRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = SERVICO_INEXISTENTE;
        String erroRetornado = null;

        try {
            autonomoServiceImpl.adicionaServicoProfissionalAutonomo(1L, adicionaServicoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);
    }

    @Test
    public void adicionaFormaPagamentoDeveAdicionarAoProfissionalComSucesso()
            throws RegrasDeNegocioException {

        FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();
        formaPagamentoDTO.setTipoPagamento(TipoPagamento.DINHEIRO);

        Optional<ProfissionalAutonomo> autonomo = Optional.of(this.autonomoFactory());
        Optional<FormaPagamento> formaPagamento = Optional.of(this.criaFormaPagamento());

        Mockito.when(autonomoRepository.existsById(1L)).thenReturn(true);

        Mockito.when(autonomoRepository.findById(1L)).thenReturn(autonomo);

        Assert.assertFalse(formasPagamento.contains(formaPagamento.get()));

        autonomo.get().setFormasPagamento(formasPagamento);

        ProfissionalAutonomo autonomoRetornado =
                autonomoServiceImpl.adicionaFormaPagamento(1L, formaPagamentoDTO);

        ProfissionalAutonomo autonomoEsperado = new ProfissionalAutonomo();

        BeanUtils.copyProperties(autonomo.get(), autonomoEsperado);

        Assert.assertEquals(autonomoEsperado, autonomoRetornado);
    }

    @Test
    public void adicionaFormaPagamentoNaoDeveAdicionarAoProfissionalComSucessoCasoNaoExista() {

        FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();
        formaPagamentoDTO.setTipoPagamento(TipoPagamento.DINHEIRO);

        Mockito.when(autonomoRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = PROFISSIONAL_NAO_ENCONTRADO;
        String erroRetornado = null;

        try {
            autonomoServiceImpl.adicionaFormaPagamento(1L, formaPagamentoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }


        Assert.assertEquals(erroEsperado, erroRetornado);
    }

    private ProfissionalAutonomoDTO autonomoDTOFactory() {

        ProfissionalAutonomoDTO autonomoDTO = new ProfissionalAutonomoDTO();

        autonomoDTO.setLogin("elias@mail.com");
        autonomoDTO.setSenha("doctorwho");
        autonomoDTO.setNome("Elias Pereira De lima");
        autonomoDTO.setCpf("460.636.108-55");
        autonomoDTO.setDataNascimento(LocalDate.of(1999, 5, 1));
        autonomoDTO.setEndereco("Rua 8, 40");
        autonomoDTO.setCep("13058-168");
        autonomoDTO.setEstado("SP");
        autonomoDTO.setBairro("Jardim Bassoli");
        autonomoDTO.setTelefone("19983792017");
        autonomoDTO.setEmail("elias@mail.com");
        autonomoDTO.setHoraInicioExpediente(LocalTime.of(8, 00));
        autonomoDTO.setHoraFimExpediente(LocalTime.of(18, 00));
        autonomoDTO.setTipoUsuario(TipoUsuario.AUTONOMO);

        return autonomoDTO;
    }

    private ProfissionalAutonomo autonomoFactory() {

        ProfissionalAutonomo autonomo = new ProfissionalAutonomo();

        autonomo.setIdUsuario(1L);
        autonomo.setLogin("elias@mail.com");
        autonomo.setSenha("doctorwho");
        autonomo.setNome("Elias Pereira De lima");
        autonomo.setCpf("460.636.108-55");
        autonomo.setDataNascimento(LocalDate.of(1999, 5, 1));
        autonomo.setEndereco("Rua 8, 40");
        autonomo.setCep("13058-168");
        autonomo.setEstado("SP");
        autonomo.setBairro("Jardim Bassoli");
        autonomo.setTelefone("19983792017");
        autonomo.setEmail("elias@mail.com");
        autonomo.setHoraInicioExpediente(LocalTime.of(8, 00));
        autonomo.setHoraFimExpediente(LocalTime.of(18, 00));
        autonomo.setAgendamentos(Collections.emptyList());
        autonomo.setFormasPagamento(Collections.emptyList());
        autonomo.setServicos(Collections.emptyList());
        autonomo.setTipoUsuario(TipoUsuario.AUTONOMO);

        return autonomo;
    }

    private Servico criaServico() {

        Servico servico = new Servico();

        servico.setIdServico(1L);
        servico.setNomeServico("Corte De Cabelo");
        servico.setValorServico(100.00D);
        servico.setDuracao("50 Min");
        servico.setTipoServico(TipoServico.CABELEIREIRO);

        return servico;
    }

    private FormaPagamento criaFormaPagamento() {

        FormaPagamento formaPagamento = new FormaPagamento();

        formaPagamento.setIdFormaPagamento(1L);
        formaPagamento.setTipoPagamento(TipoPagamento.DINHEIRO);

        return formaPagamento;
    }
}
