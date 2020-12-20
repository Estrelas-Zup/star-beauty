package br.com.zup.estrelas.sb.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import br.com.zup.estrelas.sb.enums.TipoPagamento;

@Entity
public class Agendamento {

    @Id
    @Column(name = "id_agendamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgendamento;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_funcionario",
            foreignKey = @ForeignKey(name = "agendamento_funcionario_fk"))
    private Funcionario funcionario;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_usuario_cliente",
            foreignKey = @ForeignKey(name = "agendamentos_cliente_fk"))
    private Cliente cliente;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_usuario_autonomo",
            foreignKey = @ForeignKey(name = "agendamentos_autonomo_fk"))
    private ProfissionalAutonomo autonomo;

    @OneToOne
    @JoinColumn(name = "id_servico", foreignKey = @ForeignKey(name = "agendamentos_servico_fk"))
    private Servico servico;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "data_hora_fim", nullable = false)
    private LocalDateTime dataHoraFim;

    @Column(name = "forma_pagamento", nullable = false)
    private TipoPagamento tipoPagamento;

    private boolean realizado;

    private boolean cancelado;

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public ProfissionalAutonomo getAutonomo() {
        return autonomo;
    }

    public void setAutonomo(ProfissionalAutonomo autonomo) {
        this.autonomo = autonomo;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

}
