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

    private TipoPagamento tipoPagmento;

    @NotBlank(message = "O campo nome do cliente precisa ser preenchido.")
    private String nomeCliente;

    private String nomeSalao;

    private String nomeAutonomo;

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

    public TipoPagamento getTipoPagamento() {
        return tipoPagmento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagmento) {
        this.tipoPagmento = tipoPagmento;
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

    public TipoPagamento getTipoPagmento() {
        return tipoPagmento;
    }

    public void setTipoPagmento(TipoPagamento tipoPagmento) {
        this.tipoPagmento = tipoPagmento;
    }

    public String getNomeAutonomo() {
        return nomeAutonomo;
    }

    public void setNomeAutonomo(String nomeAutonomo) {
        this.nomeAutonomo = nomeAutonomo;
    }

}
