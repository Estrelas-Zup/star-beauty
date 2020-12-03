package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface ProfissionalAutonomoService {

    public ProfissionalAutonomo buscaProfissionalAutonomo(Long idUsuario) throws RegrasDeNegocioException;

    public List<ProfissionalAutonomo> listaProfissionaisAutonomos();

    public MensagemDTO adicionaProfissionalAutonomo(
            ProfissionalAutonomoDTO profissionalAutonomoDTO) throws RegrasDeNegocioException;

    public MensagemDTO alteraProfissionalAutonomo(Long idUsuario,
            ProfissionalAutonomoDTO profissionalAutonomoDTO) throws RegrasDeNegocioException;

    public MensagemDTO inativaProfissionalAutonomo(Long idUsuario,
            InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO) throws RegrasDeNegocioException;

    public MensagemDTO adicionaServicoProfissionalAutonomo(Long idUsuario,
            AdicionaServicoDTO adicionaServicoDTO) throws RegrasDeNegocioException;

    public MensagemDTO adicionaFormaPagamento(Long idUsuario, FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException;

}
