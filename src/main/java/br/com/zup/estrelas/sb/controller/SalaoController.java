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
import br.com.zup.estrelas.sb.dto.InativaSalaoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.SalaoDTO;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.service.SalaoService;

@RestController
@RequestMapping("/saloes")
public class SalaoController {

    @Autowired
    SalaoService salaoService;

    @GetMapping(path = "/{idSalao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Salao buscaSalao(@PathVariable Long idSalao) {
        return salaoService.buscaSalao(idSalao);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Salao> listaSalao() {
        return salaoService.listaSalao();
    }

    @PostMapping
    public MensagemDTO adicionaSalao(@Valid @RequestBody SalaoDTO salaoDTO) {
        return salaoService.adicionaSalao(salaoDTO);
    }

    @PutMapping(path = "/{idSalao}")
    public MensagemDTO alteraSalao(@PathVariable Long idSalao,
            @Valid @RequestBody SalaoDTO salaoDTO) {
        return salaoService.alteraSalao(idSalao, salaoDTO);
    }

    @PutMapping(path = "/{idSalao}/inativa")
    public MensagemDTO inativaSalao(@PathVariable Long idSalao,
            @Valid @RequestBody InativaSalaoDTO inativaSalaoDTO) {
        return salaoService.inativaSalao(idSalao, inativaSalaoDTO);
    }

}
