package br.com.zup.estrelas.sb.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ServicoDTO;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    ServicoService servicoService;
    
    @PostMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO adicionaServico (@RequestBody ServicoDTO servicoDTO) {
        
        return servicoService.adicionaServico(servicoDTO);
        
    }
    
    @PutMapping (path = "/{idServico}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO alteraServico (@PathVariable Long idServico, @RequestBody ServicoDTO servicoDTO) {
        
        return servicoService.alteraServico(idServico, servicoDTO);
        
    }
    
    @GetMapping (path = "/{idServico}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Optional<Servico> consultaServico(@PathVariable Long idServico) {
        return servicoService.buscaServico(idServico);
    }
    
    @GetMapping (produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Servico> listarServicos() {
        return servicoService.listaServicos();
    }
    
    @DeleteMapping (path = "/{idServico}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public MensagemDTO removerServico(@PathVariable Long idServico) {
        return servicoService.removeServico(idServico);
    }
    
}
