package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.NotNull;

public class AdicionaServicoFuncionarioDTO {
    
    @NotNull
    private Long idServico;

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }
    

}
