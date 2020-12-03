package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaSalaoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.SalaoDTO;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface SalaoService {

    public Salao buscaSalao(Long idUsuario) throws RegrasDeNegocioException;

    public List<Salao> listaSalao() throws RegrasDeNegocioException;

    public MensagemDTO adicionaSalao(SalaoDTO salaoDTO) throws RegrasDeNegocioException;

    public MensagemDTO alteraSalao(Long idUsuario, SalaoDTO salaoDTO) throws RegrasDeNegocioException;

    public MensagemDTO inativaSalao(Long idUsuario, InativaSalaoDTO inativaSalaoDTO) throws RegrasDeNegocioException;

    public MensagemDTO adicionaFormaPagamento(Long idUsuario, FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException;

}
