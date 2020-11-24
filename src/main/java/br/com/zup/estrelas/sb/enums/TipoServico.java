package br.com.zup.estrelas.sb.enums;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum TipoServico {

    CABELEIREIRO("cabeleireiro"), MANUCURE("manicure"), ESTETICA("estética"), DEPILACAO(
            "depilação"), OUTROS("outros");

    private String value;


    TipoServico(String value) {
        this.value = value;
    }
}
