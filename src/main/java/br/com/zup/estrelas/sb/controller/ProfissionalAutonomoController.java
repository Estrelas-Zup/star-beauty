package br.com.zup.estrelas.sb.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.dto.ProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.service.ProfissionalAutonomoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/autonomos")
public class ProfissionalAutonomoController {

    @Autowired
    ProfissionalAutonomoService profissionalAutonomoService;

    @ApiOperation(value = "Busca profissional autônomo")
    @PreAuthorize("hasAuthority('cliente')")
    @GetMapping(path = "/{idUsuario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProfissionalAutonomo buscaProfissionalAutonomo(@PathVariable Long idUsuario) throws RegrasDeNegocioException {
        return profissionalAutonomoService.buscaProfissionalAutonomo(idUsuario);
    }

    @ApiOperation(value = "Lista todos os profissionais autônomos")
    @PreAuthorize("hasAuthority('cliente')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ProfissionalAutonomo> listaProfissionaisAutonomos() {
        return profissionalAutonomoService.listaProfissionaisAutonomos();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Adiciona profissional autônomo")
    @PreAuthorize("hasAuthority('autonomo')")
    @PostMapping
    public ProfissionalAutonomo insereProfissionaisAutonomos(
            @Valid @RequestBody ProfissionalAutonomoDTO profissionalAutonomoDTO) throws RegrasDeNegocioException {
        return profissionalAutonomoService.insereProfissionalAutonomo(profissionalAutonomoDTO);
    }

    @ApiOperation(value = "Altera profissional autônomo")
    @PreAuthorize("hasAuthority('autonomo')")
    @PutMapping("/{idUsuario}")
    public ProfissionalAutonomo alteraProfissionalAutonomo(@PathVariable Long idUsuario,
            @Valid @RequestBody ProfissionalAutonomoDTO profissionaoAutonomoDTO) throws RegrasDeNegocioException {
        return profissionalAutonomoService.alteraProfissionalAutonomo(idUsuario,
                profissionaoAutonomoDTO);
    }

    @ApiOperation(value = "Inativa profissional autônomo")
    @PreAuthorize("hasAuthority('autonomo')")
    @PutMapping("/{idUsuario}/inativa")
    public ProfissionalAutonomo inativaProfissionalAutonomo(@PathVariable Long idUsuario,
            @Valid @RequestBody InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO) throws RegrasDeNegocioException {
        return profissionalAutonomoService.inativaProfissionalAutonomo(idUsuario,
                inativaProfissionalAutonomoDTO);
    }

    @ApiOperation(value = "Adiciona serviço para o profissional autônomo")
    @PreAuthorize("hasAuthority('autonomo')")
    @PutMapping("/{idUsuario}/servicos")
    public ProfissionalAutonomo adicionaServicoProfissionalAutonomo(@PathVariable Long idUsuario,
            @Valid @RequestBody AdicionaServicoDTO adicionaServicoDTO) throws RegrasDeNegocioException {
        return profissionalAutonomoService.adicionaServicoProfissionalAutonomo(idUsuario,
                adicionaServicoDTO);
    }

    @ApiOperation(value = "Adiciona uma forma de pagamento para o profissional autônomo")
    @PreAuthorize("hasAuthority('autonomo')")
    @PutMapping("/{idUsuario}/pagamentos")
    public ProfissionalAutonomo adicionaFormaPagamento(@PathVariable Long idUsuario,
            @RequestBody FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException {
        return profissionalAutonomoService.adicionaFormaPagamento(idUsuario, formaPagamentoDTO);
    }

}
