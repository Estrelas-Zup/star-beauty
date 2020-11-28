package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.NotBlank;

public class CancelaAgendamentoDTO {
    
    @NotBlank(message = "Esse campo não deve ser nulo.")
    private boolean cancelado;

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
}
