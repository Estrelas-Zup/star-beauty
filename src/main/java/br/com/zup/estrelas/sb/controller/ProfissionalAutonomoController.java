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
import br.com.zup.estrelas.sb.dto.InativaProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;
import br.com.zup.estrelas.sb.service.ProfissionalAutonomoService;

@RestController
@RequestMapping("/autonomos")
public class ProfissionalAutonomoController {

    @Autowired
    ProfissionalAutonomoService profissionalAutonomoService;

    @GetMapping(path = "/{idUsuario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProfissionalAutonomo buscaProfissionalAutonomo(@PathVariable Long idUsuario) {
        return profissionalAutonomoService.buscaProfissionalAutonomo(idUsuario);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ProfissionalAutonomo> listaProfissionaisAutonomos() {
        return profissionalAutonomoService.listaProfissionaisAutonomos();
    }

    @PostMapping
    public MensagemDTO adicionaProfissionaisAutonomos(
            @Valid @RequestBody ProfissionalAutonomoDTO profissionalAutonomoDTO) {
        return profissionalAutonomoService.adicionaProfissionalAutonomo(profissionalAutonomoDTO);
    }

    @PutMapping("/{idUsuario}")
    public MensagemDTO alteraProfissionalAutonomo(@PathVariable Long idUsuario,
            @Valid @RequestBody ProfissionalAutonomoDTO profissionaoAutonomoDTO) {
        return profissionalAutonomoService.alteraProfissionalAutonomo(idUsuario,
                profissionaoAutonomoDTO);
    }

    @PutMapping("/{idUsuario}/inativa")
    public MensagemDTO inativaProfissionalAutonomo(@PathVariable Long idUsuario,
            @Valid @RequestBody InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO) {
        return profissionalAutonomoService.inativaProfissionalAutonomo(idUsuario,
                inativaProfissionalAutonomoDTO);
    }

}
