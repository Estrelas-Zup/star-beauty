package br.com.zup.estrelas.sb.enums;

public enum TipoUsuario {

    SALAO("salao"), 
    CLIENTE("cliente");

    private String value;

    TipoUsuario(String value) {
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
