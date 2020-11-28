package br.com.zup.estrelas.sb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.zup.estrelas.sb.enums.TipoPagamento;

@Entity
public class FormaPagamento {

    @Id
    @Column(name = "id_forma_pagamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormaPagamento;

    @Column(name = "tipo_pagamento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamnto;

    public Long getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public TipoPagamento getTipoPagamnto() {
        return tipoPagamnto;
    }

    public void setTipoPagamnto(TipoPagamento tipoPagamnto) {
        this.tipoPagamnto = tipoPagamnto;
    }

}
