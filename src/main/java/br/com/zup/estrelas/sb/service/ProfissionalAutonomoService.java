package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.dto.ProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface ProfissionalAutonomoService {

    public ProfissionalAutonomo buscaProfissionalAutonomo(Long idUsuario) throws RegrasDeNegocioException;

    public List<ProfissionalAutonomo> listaProfissionaisAutonomos();

    public ProfissionalAutonomo insereProfissionalAutonomo(
            ProfissionalAutonomoDTO profissionalAutonomoDTO) throws RegrasDeNegocioException;

    public ProfissionalAutonomo alteraProfissionalAutonomo(Long idUsuario,
            ProfissionalAutonomoDTO profissionalAutonomoDTO) throws RegrasDeNegocioException;

    public ProfissionalAutonomo inativaProfissionalAutonomo(Long idUsuario,
            InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO) throws RegrasDeNegocioException;

    public ProfissionalAutonomo adicionaServicoProfissionalAutonomo(Long idUsuario,
            AdicionaServicoDTO adicionaServicoDTO) throws RegrasDeNegocioException;

    public ProfissionalAutonomo adicionaFormaPagamento(Long idUsuario, FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException;

}
