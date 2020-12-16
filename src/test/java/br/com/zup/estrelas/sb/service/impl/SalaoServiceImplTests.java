package br.com.zup.estrelas.sb.service.impl;

import java.util.Collections;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.zup.estrelas.sb.dto.SalaoDTO;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.SalaoRepository;

@RunWith(MockitoJUnitRunner.class)
public class SalaoServiceImplTests {
    
    private static final String SALAO_JA_POSSUI_CADASTRO = "JÁ EXISTE UM CADASTRO PARA ESSE SALÃO.";

    @Mock
    SalaoRepository salaoRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    SalaoServiceImpl salaoServiceImpl;

    @Test
    public void deveAdicionarUmSalao() throws RegrasDeNegocioException {

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
    public void naoDeveAdicionarUmSalao(){

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
  
}
