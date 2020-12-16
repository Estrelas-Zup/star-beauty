package br.com.zup.estrelas.sb.service.impl;

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
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaSalaoDTO;
import br.com.zup.estrelas.sb.dto.SalaoDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.enums.TipoPagamento;
import br.com.zup.estrelas.sb.enums.TipoUsuario;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.FormaPagamentoRepository;
import br.com.zup.estrelas.sb.repository.SalaoRepository;

@RunWith(MockitoJUnitRunner.class)
public class SalaoServiceImplTests {

    private static final String SALAO_JA_POSSUI_CADASTRO = "JÁ EXISTE UM CADASTRO PARA ESSE SALÃO.";
    private static final String SALAO_NAO_ENCONTRADO =
            "O SALÃO NÃO FOI ENCONTRADO! VERIFIQUE AS INFORMAÇÕES INSERIDAS.";
    private static final String CNPJ_ALTERADO_JA_EXISTE_NO_BANCO_DE_DADOS =
            "CNPJ ALTERADO JÁ EXISTE NO BANCO DE DADOS!";
    private static final String SALAO_NAO_ENCONTRADO_PARA_SER_INATIVADO =
            "O SALÃO NÃO FOI ENCONTRADO PARA SER INATIVADO!";

    @Mock
    SalaoRepository salaoRepository;
    
    @Mock
    FormaPagamentoRepository formaPagamentoRepository;

    @Mock
    PasswordEncoder passwordEncoder;
    
    @Mock
    List<FormaPagamento> formasPagamento; 

    @InjectMocks
    SalaoServiceImpl salaoServiceImpl;

    @Test
    public void deveAdicionarUmSalaoComSucesso() throws RegrasDeNegocioException {

        SalaoDTO salaoDTO = this.SalaoDtoFactory();

        Mockito.when(salaoRepository.existsByCnpj(salaoDTO.getCnpj())).thenReturn(false);

        Salao salaoRetornado = salaoServiceImpl.adicionaSalao(salaoDTO);
        Salao salaoEsperado = new Salao();

        BeanUtils.copyProperties(salaoDTO, salaoEsperado);
        salaoEsperado.setSenha(passwordEncoder.encode(salaoDTO.getSenha()));
        salaoEsperado.setFormasPagamento(Collections.emptyList());
        salaoEsperado.setFuncionarios(Collections.emptyList());
        salaoEsperado.setAtivo(true);

        Assert.assertEquals(salaoEsperado, salaoRetornado);
    }

    @Test
    public void naoDeveAdicionarUmSalao() {

        SalaoDTO salaoDTO = this.SalaoDtoFactory();

        Mockito.when(salaoRepository.existsByCnpj(salaoDTO.getCnpj())).thenReturn(true);

        String erroEsperado = SALAO_JA_POSSUI_CADASTRO;
        String erroRetornado = null;
        try {
            salaoServiceImpl.adicionaSalao(salaoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);
    }

    @Test
    public void deveAlterarSalaoComSucesso() throws RegrasDeNegocioException {

        SalaoDTO salaoDTO = this.SalaoDtoFactory();
        Optional<Salao> salao = Optional.of(this.salaoFactory());

        Mockito.when(salaoRepository.existsById(1L)).thenReturn(true);

        Mockito.when(salaoRepository.findById(1L)).thenReturn(salao);

        Salao salaoRetornado = salaoServiceImpl.alteraSalao(1L, salaoDTO);
        Salao salaoEsperado = new Salao();

        BeanUtils.copyProperties(salao.get(), salaoEsperado);

        Assert.assertEquals(salaoEsperado, salaoRetornado);
    }

    @Test
    public void NaoDeveAlterarSalaoCasoNaoExista() {

        SalaoDTO salaoDTO = this.SalaoDtoFactory();

        String erroEsperado = SALAO_NAO_ENCONTRADO;
        String erroRetornado = null;


        try {
            salaoServiceImpl.alteraSalao(1L, salaoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);
    }

    @Test
    public void NaoDeveAlterarSalaoCasoCnpjJaExista() {

        SalaoDTO salaoDTO = this.SalaoDtoFactory();

        Optional<Salao> salao = Optional.of(this.salaoFactory());

        Mockito.when(salaoRepository.existsById(1L)).thenReturn(true);

        Mockito.when(salaoRepository.findById(1L)).thenReturn(salao);

        Assert.assertFalse(salaoDTO.getCnpj().equals(salao.get().getCnpj()));

        Mockito.when(salaoRepository.existsByCnpj(salaoDTO.getCnpj())).thenReturn(true);

        String erroEsperado = CNPJ_ALTERADO_JA_EXISTE_NO_BANCO_DE_DADOS;
        String erroRetornado = null;

        try {
            salaoServiceImpl.alteraSalao(1L, salaoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);

    }

    @Test
    public void deveInativarSalaoComSucesso() throws RegrasDeNegocioException {

        InativaSalaoDTO inativaSalaoDTO = new InativaSalaoDTO();
        inativaSalaoDTO.setAtivo(false);
        
        Optional<Salao> salao = Optional.of(this.salaoFactory());
        
        Mockito.when(salaoRepository.existsById(1L)).thenReturn(true);
        
        Mockito.when(salaoRepository.findById(1L)).thenReturn(salao);
        
        Salao salaoRetornado = salaoServiceImpl.inativaSalao(1L, inativaSalaoDTO);
        Salao salaoEsperado = new Salao();
        
        BeanUtils.copyProperties(salao.get(), salaoEsperado);
        salaoEsperado.setAtivo(inativaSalaoDTO.isAtivo());
        
        Assert.assertEquals(salaoEsperado, salaoRetornado);
    }
    
    @Test
    public void NaoDeveInativarSalao(){
        
        InativaSalaoDTO inativaSalaoDTO = new InativaSalaoDTO();
        inativaSalaoDTO.setAtivo(false);
        
        String erroEsperado = SALAO_NAO_ENCONTRADO_PARA_SER_INATIVADO;
        String erroRetornado = null;
        
        try {
            salaoServiceImpl.inativaSalao(1L, inativaSalaoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }
        
        Assert.assertEquals(erroEsperado, erroRetornado);
    }
    
    @Test
    public void deveAdicionarFormaDePagamentoComSucesso() throws RegrasDeNegocioException{
        
        FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();
        formaPagamentoDTO.setTipoPagamento(TipoPagamento.DINHEIRO);
                
        Optional<Salao> salao = Optional.of(this.salaoFactory());
        
        Mockito.when(salaoRepository.existsById(1L)).thenReturn(true);
                
        Mockito.when(salaoRepository.findById(1L)).thenReturn(salao);
                        
        salao.get().setFormasPagamento(formasPagamento);
                
        Salao salaoRetornado = salaoServiceImpl.adicionaFormaPagamento(1L, formaPagamentoDTO);
        Salao salaoEsperado = new Salao();
        
        BeanUtils.copyProperties(salao.get(), salaoEsperado);
        
        Assert.assertEquals(salaoEsperado, salaoRetornado);
        
    }
    
    @Test
    public void naoDeveAdicionarFormaDePagamentoComSucesso() {
        
        FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();
        formaPagamentoDTO.setTipoPagamento(TipoPagamento.DINHEIRO);
        
        Mockito.when(salaoRepository.existsById(1L)).thenReturn(false);
        
        String erroEsperado = SALAO_NAO_ENCONTRADO;
        String erroRetornado = null;
        
        try {
            salaoServiceImpl.adicionaFormaPagamento(1L, formaPagamentoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }
        
        Assert.assertEquals(erroEsperado, erroRetornado);
    }

    private SalaoDTO SalaoDtoFactory() {

        SalaoDTO salaoDTO = new SalaoDTO();

        salaoDTO.setLogin("graziela@mail.com");
        salaoDTO.setSenha("14GdfG98");
        salaoDTO.setNome("Grazi Beauty");
        salaoDTO.setEndereco("Rua do Protetor, 123");
        salaoDTO.setCep("38407509");
        salaoDTO.setEstado("Minas Gerais");
        salaoDTO.setCidade("Uberlândia");
        salaoDTO.setBairro("Loteamento Integração");
        salaoDTO.setTelefone("34994586521");
        salaoDTO.setEmail("graziela@mail.com");
        salaoDTO.setCnpj("27810546000164");
        salaoDTO.setNomeFantasia("Beauty Factory");

        return salaoDTO;
    }

    private Salao salaoFactory() {

        Salao salao = new Salao();

        salao.setIdUsuario(1L);
        salao.setLogin("graziela@mail.com");
        salao.setSenha("14GdfG98");
        salao.setNome("Grazi Beauty");
        salao.setEndereco("Rua do Protetor, 456");
        salao.setCep("38407509");
        salao.setEstado("Minas Gerais");
        salao.setCidade("Uberlândia");
        salao.setBairro("Loteamento Integração");
        salao.setTelefone("34994586521");
        salao.setEmail("graziela@mail.com");
        salao.setTipoUsuario(TipoUsuario.SALAO);
        salao.setCnpj("27810546000165");
        salao.setNomeFantasia("Beauty Factory");
        salao.setFormasPagamento(Collections.emptyList());
        salao.setFuncionarios(Collections.emptyList());
        salao.isAtivo();

        return salao;

    }

}
