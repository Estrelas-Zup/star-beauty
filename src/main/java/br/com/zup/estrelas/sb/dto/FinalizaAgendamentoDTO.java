package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.AssertTrue;

public class FinalizaAgendamentoDTO {

    @AssertTrue
    private boolean realizado;

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

}
