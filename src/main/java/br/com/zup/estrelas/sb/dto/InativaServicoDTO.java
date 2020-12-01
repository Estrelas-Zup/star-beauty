package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.AssertFalse;

public class InativaServicoDTO {
    
    @AssertFalse
    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
