package br.com.zup.estrelas.sb.service.impl;

import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.enums.TipoPagamento;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.FormaPagamentoRepository;

@RunWith(MockitoJUnitRunner.class)
public class FormaPagamentoServiceImplTests {
    
    private static final String FORMA_DE_PAGAMENTO_JA_CADASTRADA =
            "FORMA DE PAGAMENTO JA CADASTRADA!";
    private static final String FORMA_DE_PAGAMENTO_INEXISTENTE = "FORMA DE PAGAMENTO INEXISTENTE!";
    private static final String FORMA_DE_PAGAMENTO_JA_EXISTENTE =
            "FORMA DE PAGAMENTO JÁ EXISTENTE!";
    private static final String FORMA_DE_PAGAMENTO_REMOVIDA_COM_SUCESSO =
            "FORMA DE PAGAMENTO REMOVIDA COM SUCESSO!";

    @Mock
    FormaPagamentoRepository formaPagamentoRepository;

    @InjectMocks
    FormaPagamentoServiceImpl formaPagamentoServiceImpl;

    @Test
    public void deveAdicionarFormaPagamento() throws RegrasDeNegocioException {

        FormaPagamentoDTO formaPagamentoDTO = this.formaPagamentoDtoFactory();

        Mockito.when(formaPagamentoRepository
                .existsByTipoPagamento(formaPagamentoDTO.getTipoPagamento())).thenReturn(false);

        FormaPagamento formaPagamentoRetornado =
                formaPagamentoServiceImpl.adicionaFormaPagamento(formaPagamentoDTO);
        FormaPagamento formaPagamentoEsperado = new FormaPagamento();

        BeanUtils.copyProperties(formaPagamentoDTO, formaPagamentoEsperado);

        Assert.assertEquals(formaPagamentoRetornado, formaPagamentoEsperado);
    }

    @Test
    public void naoDeveAdicionarFormaPagamento() {
        
        FormaPagamentoDTO formaPagamentoDTO = this.formaPagamentoDtoFactory();

        Mockito.when(formaPagamentoRepository
                .existsByTipoPagamento(formaPagamentoDTO.getTipoPagamento())).thenReturn(true);
        
        String erroEsperado = FORMA_DE_PAGAMENTO_JA_CADASTRADA;
        String erroRetornado = null;

        try {
            formaPagamentoServiceImpl.adicionaFormaPagamento(formaPagamentoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }
        
        Assert.assertEquals(erroRetornado, erroEsperado);
    }
    
    @Test
    public void deveAlterarFormaPagamento() throws RegrasDeNegocioException {
        
        FormaPagamentoDTO alteraFormaPagamentoDTO = this.formaPagamentoDtoFactory();
        Optional<FormaPagamento> formaPagamento = Optional.of(this.formaPagamentoFactory());
        
        Mockito.when(formaPagamentoRepository.existsById(1L)).thenReturn(true);
        Mockito.when(formaPagamentoRepository.findById(1L)).thenReturn(formaPagamento);
        
        FormaPagamento formaPagamentoRetornado = formaPagamentoServiceImpl.alteraFormaPagamento(1L, alteraFormaPagamentoDTO);
        FormaPagamento formaPagamentoEsperado = new FormaPagamento();
        
        BeanUtils.copyProperties(alteraFormaPagamentoDTO, formaPagamentoEsperado);
        formaPagamentoEsperado.setIdFormaPagamento(1L);
        
        Assert.assertEquals(formaPagamentoEsperado, formaPagamentoRetornado);
    }
    
    @Test
    public void naoDeveAlterarFormaPagamentoInexistente() {
        
        FormaPagamentoDTO alteraFormaPagamentoDTO = this.formaPagamentoDtoFactory();
        
        Mockito.when(formaPagamentoRepository.existsById(1L)).thenReturn(false);
        
        String erroEsperado = FORMA_DE_PAGAMENTO_INEXISTENTE;
        String erroRetornado = null;
        
        try {
            formaPagamentoServiceImpl.alteraFormaPagamento(1L, alteraFormaPagamentoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }
        
        Assert.assertEquals(erroEsperado, erroRetornado);
    }
    
    @Test
    public void naoDeveAlterarFormaPagamentoJaExistente() {
        
        FormaPagamentoDTO alteraFormaPagamentoDTO = this.formaPagamentoDtoFactory();
        
        Optional<FormaPagamento> formaPagamento = Optional.of(this.formaPagamentoFactory());
        
        Mockito.when(formaPagamentoRepository.existsById(1L)).thenReturn(true);

        Mockito.when(formaPagamentoRepository.findById(1L)).thenReturn(formaPagamento);
        
        Assert.assertTrue(alteraFormaPagamentoDTO.getTipoPagamento().equals(formaPagamento.get().getTipoPagamento()));
        
        Mockito.when(formaPagamentoRepository.existsByTipoPagamento(alteraFormaPagamentoDTO.getTipoPagamento())).thenReturn(true);
        
        String erroEsperado = FORMA_DE_PAGAMENTO_JA_EXISTENTE;
        String erroRetornado = null;
        
        try {
            formaPagamentoServiceImpl.alteraFormaPagamento(1L, alteraFormaPagamentoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);
    }
    
    @Test
    public void deveRemoverFormaPagamentoComSucesso() throws RegrasDeNegocioException {
        
        Mockito.when(formaPagamentoRepository.existsById(1L)).thenReturn(true);

        MensagemDTO mensagemRecebida = formaPagamentoServiceImpl.removeFormaPagamento(1L);
        MensagemDTO mensagemEsperada = new MensagemDTO(FORMA_DE_PAGAMENTO_REMOVIDA_COM_SUCESSO);

        Assert.assertEquals(mensagemRecebida, mensagemEsperada);
    }
    
    @Test
    public void NaoDeveRemoverFormaPagamentoComSucesso(){
        
        Mockito.when(formaPagamentoRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = FORMA_DE_PAGAMENTO_INEXISTENTE;
        String erroRetornado = null;
        
        try {
            formaPagamentoServiceImpl.removeFormaPagamento(1L);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroRetornado, erroEsperado);
        
    }

    private FormaPagamentoDTO formaPagamentoDtoFactory() {

        FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();

        formaPagamentoDTO.setTipoPagamento(TipoPagamento.PIX);

        return formaPagamentoDTO;
    }
    
    private FormaPagamento formaPagamentoFactory() {
        
        FormaPagamento formaPagamento = new FormaPagamento();
        
        formaPagamento.setIdFormaPagamento(1L);
        formaPagamento.setTipoPagamento(TipoPagamento.PIX);
       
        return formaPagamento;
    }

}
