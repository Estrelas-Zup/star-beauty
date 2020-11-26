package br.com.zup.estrelas.sb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import br.com.zup.estrelas.sb.enums.FormaPagamento;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Transacao {

    @Id
    @Column(name = "id_transacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;

    @OneToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "transacao_fk"))
    private Agendamento agendamento;

    @Column(nullable = false)
    private Double valor;

    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagmento;

    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    @Column(name = "nome_salao", nullable = false)
    private String nomeSalao;

    public Long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public FormaPagamento getFormaPagmento() {
        return formaPagmento;
    }

    public void setFormaPagmento(FormaPagamento formaPagmento) {
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
