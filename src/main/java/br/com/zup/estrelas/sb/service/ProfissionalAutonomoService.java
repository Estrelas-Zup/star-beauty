package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;

public interface ProfissionalAutonomoService {

    public ProfissionalAutonomo buscaProfissionalAutonomo(Long idUsuario);

    public List<ProfissionalAutonomo> listaProfissionaisAutonomos();

    public MensagemDTO adicionaProfissionalAutonomo(
            ProfissionalAutonomoDTO profissionalAutonomoDTO);

    public MensagemDTO alteraProfissionalAutonomo(Long idUsuario,
            ProfissionalAutonomoDTO profissionalAutonomoDTO);

    public MensagemDTO inativaProfissionalAutonomo(Long idUsuario,
            InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO);

    public MensagemDTO adicionaServicoProfissionalAutonomo(Long idUsuario,
            AdicionaServicoDTO adicionaServicoDTO);

    public MensagemDTO adicionaFormaPagamento(Long idUsuario, FormaPagamentoDTO formaPagamentoDTO);

}
