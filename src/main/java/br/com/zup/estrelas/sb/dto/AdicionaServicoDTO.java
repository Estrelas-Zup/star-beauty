package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class AdicionaServicoDTO {

    @NotNull(message = "O campo id servico não pode estar vazio")
    @NumberFormat(style = Style.NUMBER)
    private Long idServico;

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idSevico) {
        this.idServico = idSevico;
    }

}
