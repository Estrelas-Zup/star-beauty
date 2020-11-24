package br.com.zup.estrelas.sb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.zup.estrelas.sb.enums.FormaPagamento;

@Entity
public class Transacao {

    @Id
    @Column(name = "id_transacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;

    private Agendamento agendamento; // chave estrangeira

    @Column(nullable = false)
    private Double valor;

    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagmento;

    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    @Column(name = "nome_salao", nullable = false)
    private String nomeSalao;

}