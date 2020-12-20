package br.com.zup.estrelas.sb.exceptions;

public class RegrasDeNegocioException extends Exception {

    private static final long serialVersionUID = 1L;

    private String mensagemErro;

    public RegrasDeNegocioException(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

}
