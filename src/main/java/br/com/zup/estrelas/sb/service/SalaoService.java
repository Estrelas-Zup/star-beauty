package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.InativaSalaoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.SalaoDTO;
import br.com.zup.estrelas.sb.entity.Salao;

public interface SalaoService {

    public Salao buscaSalao(Long idSalao);

    public List<Salao> listaSalao();

    public MensagemDTO adicionaSalao(SalaoDTO salaoDTO);

    public MensagemDTO alteraSalao(Long idSalao, SalaoDTO salaoDTO);

    public MensagemDTO inativaSalao(Long idSalao, InativaSalaoDTO inativaSalaoDTO);

}
