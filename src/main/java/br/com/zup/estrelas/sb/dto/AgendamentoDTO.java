package br.com.zup.estrelas.sb.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import br.com.zup.estrelas.sb.enums.FormaPagamento;

public class AgendamentoDTO {

    private Long idFuncionario;

    private String nomeCliente;

    private String nomeFuncionario;

    private String nomeServico;

    private LocalDate data;

    private LocalTime horaAgendamento;

    private FormaPagamento formaPagamento;

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
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

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return horaAgendamento;
    }

    public void setHora(LocalTime hora) {
        this.horaAgendamento = hora;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}
