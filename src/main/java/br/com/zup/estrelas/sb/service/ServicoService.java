package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.InativaServicoDTO;
import br.com.zup.estrelas.sb.dto.ServicoDTO;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface ServicoService {

    public Servico insereServico(ServicoDTO servicoDTO) throws RegrasDeNegocioException;

    public Servico buscaServico(Long idServico) throws RegrasDeNegocioException;

    public List<Servico> listaServicos();

    public Servico alteraServico(Long idServico, ServicoDTO servicoDTO)
            throws RegrasDeNegocioException;

    public Servico inativaServico(Long idServico, InativaServicoDTO inativaServicoDTO)
            throws RegrasDeNegocioException;

}
