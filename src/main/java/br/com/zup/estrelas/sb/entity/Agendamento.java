package br.com.zup.estrelas.sb.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import br.com.zup.estrelas.sb.enums.FormaPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Agendamento {

    @Id
    @Column(name = "id_agendamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgendamento;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", foreignKey = @ForeignKey(name = "agendamentos_fk"))
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "agendamentos_fk"))
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_servico", foreignKey = @ForeignKey(name = "agendamentos_fk"))
    private Servico servico;

    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    @Column(name = "nome_servico", nullable = false)
    private String nomeServico;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalDateTime hora;

    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    private boolean realizado;

    private boolean cancelado;

}
