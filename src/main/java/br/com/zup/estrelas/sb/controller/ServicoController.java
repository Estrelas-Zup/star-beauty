package br.com.zup.estrelas.sb.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.InativaServicoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ServicoDTO;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.service.ServicoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    ServicoService servicoService;

    @ApiOperation(value = "Consulta serviço")
    @GetMapping(path = "/{idServico}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Servico consultaServico(@PathVariable Long idServico) {
        return servicoService.buscaServico(idServico);
    }

    @ApiOperation(value = "Lista todos os serviços")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Servico> listarServicos() {
        return servicoService.listaServicos();
    }

    @ApiOperation(value = "Adiciona serviço")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO adicionaServico(@Valid @RequestBody ServicoDTO servicoDTO) {
        return servicoService.adicionaServico(servicoDTO);
    }

    @ApiOperation(value = "Altera serviço")
    @PutMapping(path = "/{idServico}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO alteraServico(@PathVariable Long idServico,
            @Valid @RequestBody ServicoDTO servicoDTO) {
        return servicoService.alteraServico(idServico, servicoDTO);
    }

    @ApiOperation(value = "Inativa serviço")
    @PutMapping(path = "/{idServico}/inativa")
    public MensagemDTO inativaServico(@PathVariable Long idServico,
            @Valid @RequestBody InativaServicoDTO inativaServicoDTO) {
        return servicoService.inativaServico(idServico, inativaServicoDTO);
    }

}
