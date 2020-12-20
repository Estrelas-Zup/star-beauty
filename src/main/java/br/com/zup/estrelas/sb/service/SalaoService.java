package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaSalaoDTO;
import br.com.zup.estrelas.sb.dto.SalaoDTO;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface SalaoService {

    public Salao buscaSalao(Long idUsuario) throws RegrasDeNegocioException;

    public List<Salao> listaSalao() throws RegrasDeNegocioException;

    public Salao adicionaSalao(SalaoDTO salaoDTO) throws RegrasDeNegocioException;

    public Salao alteraSalao(Long idUsuario, SalaoDTO salaoDTO) throws RegrasDeNegocioException;

    public Salao inativaSalao(Long idUsuario, InativaSalaoDTO inativaSalaoDTO) throws RegrasDeNegocioException;

    public Salao adicionaFormaPagamento(Long idUsuario, FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException;

}
