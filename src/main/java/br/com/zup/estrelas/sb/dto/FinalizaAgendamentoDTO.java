package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.NotBlank;

public class FinalizaAgendamentoDTO {
    
    @NotBlank(message = "Esse campo não deve ser nulo.")
    private boolean realizado;

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

}
