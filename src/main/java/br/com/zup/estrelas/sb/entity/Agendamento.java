package br.com.zup.estrelas.sb.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.zup.estrelas.sb.enums.FormaPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
//@Entity
public class Agendamento {

    @Id
    @Column(name = "id_agendamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgendamento;

    //ManyToOne
    private Funcionario funcionario; // chave estrangeira

    //ManyToOne
    private Cliente cliente; // chave estrangeira

    //ManyToOne
    private Servico servico; // chave estrangeiraS

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
