package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ServicoDTO;
import br.com.zup.estrelas.sb.entity.Servico;

public interface ServicoService {
    
    public MensagemDTO adicionaServico (ServicoDTO servicoDTO);

    public Servico buscaServico (Long idServico);
    
    public List<Servico> listaServicos();
    
    public MensagemDTO removeServico (Long idServicoDTO);
    
    public MensagemDTO alteraServico (Long idServico, ServicoDTO servicoDTO);
    
}
