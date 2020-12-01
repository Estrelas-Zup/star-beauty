package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class AdicionaServicoDTO {

    @NotNull(message = "O campo id servico não pode estar vazio")
    @NumberFormat(style = Style.NUMBER)
    private Long idSevico;

    public Long getIdSevico() {
        return idSevico;
    }

    public void setIdSevico(Long idSevico) {
        this.idSevico = idSevico;
    }

}
