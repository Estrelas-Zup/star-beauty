package br.com.zup.estrelas.sb.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import br.com.zup.estrelas.sb.enums.TipoPagamento;

public class AgendamentoDTO {


    @NumberFormat(style = Style.NUMBER)
    private Long idFuncionario;

    @NumberFormat(style = Style.NUMBER)
    private Long idProfissionalAutonomo;

    @NotNull(message = "O campo nome precisa ser preenchido.")
    @Size(max = 255, message = "O nome não pode ter menos de 3 ou mais de 255 caracteres.")
    @NumberFormat(style = Style.NUMBER)
    private Long idCliente;

    @NotNull(message = "O campo id do serviço precisa ser preenchido.")
    private Long IdSevico;

    @NotNull(message = "O campo nome do cliente precisa ser preenchido.")
    private String nomeCliente;

    @NotNull(message = "O campo nome do serviço precisa ser preenchido.")
    private String nomeServico;

    @NotNull(message = "O data e hora do agendamento precisa ser preenchido.")
    @Future(message = "A data do agendamento não pode ser anterior a hoje.")
    private LocalDateTime dataHora;

    @NotNull(message = "O data e hora do agendamento precisa ser preenchido.")
    @Future(message = "A data do agendamento não pode ser anterior a hoje.")
    private LocalDateTime dataHoraFim;


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
