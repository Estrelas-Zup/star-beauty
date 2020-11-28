package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.NotBlank;

public class InativaFuncionarioDTO {
    
    @NotBlank (message = "O campo precisa ser preenchido.")
    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }



}
