package br.com.zup.estrelas.sb.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import br.com.zup.estrelas.sb.enums.TipoPagamento;

public class TransacaoDTO {

    @NotNull(message = "O campo login precisa ser preenchido.")
    private Long idAgendamento;

    @NotNull(message = "O campo valor precisa ser preenchido.")
    @NumberFormat(style = Style.NUMBER)
    private Double valor;

    private TipoPagamento formaPagmento;

    @NotBlank(message = "O campo nome do cliente precisa ser preenchido.")
    private String nomeCliente;

    @NotBlank(message = "O campo nome do salão precisa ser preenchido.")
    private String nomeSalao;

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoPagamento getFormaPagmento() {
        return formaPagmento;
    }

    public void setFormaPagmento(TipoPagamento formaPagmento) {
        this.formaPagmento = formaPagmento;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeSalao() {
        return nomeSalao;
    }

    public void setNomeSalao(String nomeSalao) {
        this.nomeSalao = nomeSalao;
    }



}
