package br.com.zup.estrelas.sb.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import br.com.zup.estrelas.sb.enums.TipoPagamento;

public class TransacaoDTO {

    private static final String APENAS_LETRAS_ALFABETO = "[a-zA-Z ]+";

    @NotBlank(message = "O campo login precisa ser preenchido.")
    private Long idAgendamento;

    @NotBlank(message = "O campo valor precisa ser preenchido.")
    @NumberFormat(style = Style.NUMBER)
    private Double valor;

    @NotBlank(message = "O campo forma de pagamento precisa ser preenchido.")
    private TipoPagamento formaPagmento;

    @NotBlank(message = "O campo nome do cliente precisa ser preenchido.")
    @Pattern(regexp = APENAS_LETRAS_ALFABETO)
    private String nomeCliente;

    @NotBlank(message = "O campo nome do salão precisa ser preenchido.")
    @Pattern(regexp = APENAS_LETRAS_ALFABETO)
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
