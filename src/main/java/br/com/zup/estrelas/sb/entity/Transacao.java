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

@Getter
@Setter
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

}