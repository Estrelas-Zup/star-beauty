package br.com.zup.estrelas.sb.dto;


public class ErrorDTO {

    private String mensagem;

    public ErrorDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
