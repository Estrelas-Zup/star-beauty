package br.com.zup.estrelas.sb.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ServicoDTO;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.service.ServicoService;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin 
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    ServicoService servicoService;

    @ApiOperation(value = "Consulta serviço")
    @GetMapping(path = "/{idServico}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Servico consultaServico(@PathVariable Long idServico) throws RegrasDeNegocioException {
        return servicoService.buscaServico(idServico);
    }

    @ApiOperation(value = "Lista todos os serviços")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Servico> listarServicos() {
        return servicoService.listaServicos();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adiciona serviço")
    @PreAuthorize("hasAuthority('salao') or hasAuthority('autonomo')")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Servico insereServico(@Valid @RequestBody ServicoDTO servicoDTO)
            throws RegrasDeNegocioException {
        return servicoService.insereServico(servicoDTO);
    }

    @ApiOperation(value = "Altera serviço")
    @PreAuthorize("hasAuthority('salao') or hasAuthority('autonomo')")
    @PutMapping(path = "/{idServico}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Servico alteraServico(@PathVariable Long idServico,
            @Valid @RequestBody ServicoDTO servicoDTO) throws RegrasDeNegocioException {
        return servicoService.alteraServico(idServico, servicoDTO);
    }

    @ApiOperation(value = "Remove serviço")
    @PreAuthorize("hasAuthority('salao') or hasAuthority('autonomo')")
    @DeleteMapping(path = "/{idServico}/delete")
    public MensagemDTO removeServico(@PathVariable Long idServico)
            throws RegrasDeNegocioException {
        return servicoService.removeServico(idServico);
    }

}
