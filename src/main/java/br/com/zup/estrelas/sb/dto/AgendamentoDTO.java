package br.com.zup.estrelas.sb.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import br.com.zup.estrelas.sb.enums.TipoPagamento;

public class AgendamentoDTO {

    private static final String APENAS_LETRAS_ALFABETO = "[a-zA-Z ]+";

    @NumberFormat(style = Style.NUMBER)
    private Long idFuncionario;

    @NumberFormat(style = Style.NUMBER)
    private Long idProfissionalAutonomo;

    @NotBlank(message = "O campo nome precisa ser preenchido.")
    @Max(value = 255, message = "O nome não pode ter menos de 3 ou mais de 255 caracteres.")
    @NumberFormat(style = Style.NUMBER)
    private Long idCliente;

    @NotBlank(message = "O campo nome do serviço precisa ser preenchido.")
    private Long IdSevico;
    
    @Pattern(regexp = APENAS_LETRAS_ALFABETO)
    private String nomeCliente;
    
    @Pattern(regexp = APENAS_LETRAS_ALFABETO)
    private String nomeServico;

    @NotBlank(message = "O data e hora do agendamento precisa ser preenchido.")
    @Future(message = "A data do agendamento não pode ser anterior a hoje.")
    private LocalDateTime dataHora;

    @NotBlank(message = "O a forma de pagamento precisa ser preenchido.")
    private TipoPagamento formaPagamento;

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Long getIdProfissionalAutonomo() {
        return idProfissionalAutonomo;
    }

    public void setIdProfissionalAutonomo(Long idProfissionalAutonomo) {
        this.idProfissionalAutonomo = idProfissionalAutonomo;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdSevico() {
        return IdSevico;
    }

    public void setIdSevico(Long idSevico) {
        IdSevico = idSevico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public TipoPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(TipoPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}
