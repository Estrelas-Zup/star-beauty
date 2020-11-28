package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.NotBlank;

public class InativaSalaoDTO {

    @NotBlank(message = "O campo ativo não deve ser nulo")
    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
