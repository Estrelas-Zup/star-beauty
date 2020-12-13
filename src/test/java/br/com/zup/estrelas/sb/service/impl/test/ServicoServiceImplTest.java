package br.com.zup.estrelas.sb.service.impl.test;

import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ServicoDTO;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.enums.TipoServico;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.ServicoRepository;
import br.com.zup.estrelas.sb.service.impl.ServicoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ServicoServiceImplTest {

    private static final String SERVICO_JA_CADASTRADO =
            "O CADASTRO NÃO OCORREU, POIS O SERVIÇO JÁ ESTÁ CADASTRADO!";
    private static final String SERVICO_INEXISTENTE = "SERVIÇO INEXISTENTE!";
    private static final String SERVIÇO_EXCLUÍDO_COM_SUCESSO = "SERVIÇO EXCLUÍDO COM SUCESSO!";


    @Mock
    ServicoRepository servicoRepository;

    @InjectMocks
    ServicoServiceImpl servicoServiceImpl;

    @Test
    public void insereServicoDeveAdicionarServicoComSucesso() throws RegrasDeNegocioException {

        ServicoDTO servicoDTO = this.ServicoDTOFactory();

        Mockito.when(servicoRepository.existsByNomeServico(servicoDTO.getNomeServico()))
                .thenReturn(false);

        Servico servicoRetornado = servicoServiceImpl.insereServico(servicoDTO);
        Servico servicoEsperado = new Servico();

        BeanUtils.copyProperties(servicoDTO, servicoEsperado);
        servicoEsperado.setIdServico(servicoRetornado.getIdServico());

        Assert.assertEquals(servicoRetornado, servicoEsperado);
    }

    @Test
    public void insereServicoNaoDeveAdicionarServicoComSucesso() {

        ServicoDTO servicoDTO = this.ServicoDTOFactory();

        Mockito.when(servicoRepository.existsByNomeServico(servicoDTO.getNomeServico()))
                .thenReturn(true);

        String erroEsperado = SERVICO_JA_CADASTRADO;
        String erroRetornado = null;

        try {
            servicoServiceImpl.insereServico(servicoDTO);
        } catch (RegrasDeNegocioException e) {

            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroRetornado, erroEsperado);;
    }

    @Test
    public void alteraServicoDeveAlterarComSucesso() throws RegrasDeNegocioException {

        ServicoDTO servicoDTO = this.ServicoDTOFactory();
        Optional<Servico> servico = Optional.of(this.ServicoFactory());

        Mockito.when(servicoRepository.existsById(1L)).thenReturn(true);
        Mockito.when(servicoRepository.findById(1L)).thenReturn(servico);

        Servico servicoRetornado = servicoServiceImpl.alteraServico(1L, servicoDTO);
        Servico servicoEsperado = new Servico();

        BeanUtils.copyProperties(servicoDTO, servicoEsperado);
        servicoEsperado.setIdServico(1L);

        Assert.assertEquals(servicoRetornado, servicoEsperado);;
    }

    @Test
    public void alteraServicoNaoDeveAlterarComSucesso() {

        ServicoDTO servicoDTO = this.ServicoDTOFactory();

        Mockito.when(servicoRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = SERVICO_INEXISTENTE;
        String erroRetornado = null;

        try {
            servicoServiceImpl.alteraServico(1L, servicoDTO);
        } catch (RegrasDeNegocioException e) {
            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroEsperado, erroRetornado);;
    }

    @Test
    public void removeServicoDeveRemoverComSucesso() throws RegrasDeNegocioException {

        Mockito.when(servicoRepository.existsById(1L)).thenReturn(true);

        MensagemDTO mensagemRecebida = servicoServiceImpl.removeServico(1L);
        MensagemDTO mensagemEsperada = new MensagemDTO(SERVIÇO_EXCLUÍDO_COM_SUCESSO);

        Assert.assertEquals(mensagemRecebida, mensagemEsperada);
    }

    @Test
    public void removeServicoNaoDeveRemoverComSucesso() {

        Mockito.when(servicoRepository.existsById(1L)).thenReturn(false);

        String erroEsperado = SERVICO_INEXISTENTE;
        String erroRetornado = null;

        try {
            servicoServiceImpl.removeServico(1L);

        } catch (RegrasDeNegocioException e) {

            erroRetornado = e.getMensagemErro();
        }

        Assert.assertEquals(erroRetornado, erroEsperado);
    }

    private ServicoDTO ServicoDTOFactory() {

        ServicoDTO servicoDTO = new ServicoDTO();

        servicoDTO.setNomeServico("Corte De Cabelo Feminino");
        servicoDTO.setDuracao("90 min");
        servicoDTO.setValorServico(80.00D);
        servicoDTO.setTipoServico(TipoServico.CABELEIREIRO);

        return servicoDTO;
    }

    private Servico ServicoFactory() {

        Servico servico = new Servico();

        servico.setIdServico(1L);
        servico.setNomeServico("Corte De Cabelo Feminino");
        servico.setDuracao("80 min");
        servico.setValorServico(100.00D);
        servico.setTipoServico(TipoServico.CABELEIREIRO);

        return servico;
    }
}
