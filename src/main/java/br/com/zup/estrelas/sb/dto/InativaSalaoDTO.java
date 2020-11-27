package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.AssertTrue;

public class InativaSalaoDTO {

    @AssertTrue
    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
