package br.com.zup.estrelas.sb.enums;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum TipoUsuario {
    
    SALAO("salao"), CLIENTE("cliente");
    
    private String value;
    
    TipoUsuario(String value) {
        this.value = value;
    }

}
