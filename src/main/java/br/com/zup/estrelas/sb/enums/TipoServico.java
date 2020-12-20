package br.com.zup.estrelas.sb.enums;

public enum TipoServico {

    CABELEIREIRO("cabeleireiro"), 
    MANICURE("manicure"), 
    ESTETICA("estética"), 
    DEPILACAO("depilação"), 
    OUTROS("outros");

    private String value;


    TipoServico(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
