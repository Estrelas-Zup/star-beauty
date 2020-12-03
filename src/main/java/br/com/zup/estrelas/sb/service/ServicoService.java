package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.InativaServicoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ServicoDTO;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface ServicoService {
    
    public MensagemDTO adicionaServico (ServicoDTO servicoDTO) throws RegrasDeNegocioException;

    public Servico buscaServico (Long idServico) throws RegrasDeNegocioException;
    
    public List<Servico> listaServicos();
    
    public MensagemDTO alteraServico (Long idServico, ServicoDTO servicoDTO) throws RegrasDeNegocioException;
    
    public MensagemDTO inativaServico (Long idServico, InativaServicoDTO inativaServicoDTO) throws RegrasDeNegocioException;
    
}
