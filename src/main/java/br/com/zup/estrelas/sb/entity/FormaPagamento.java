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
    private TipoPagamento tipoPagamento;

    public Long getIdFormaPagamento() {
        return idFormaPagamento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idFormaPagamento == null) ? 0 : idFormaPagamento.hashCode());
        result = prime * result + ((tipoPagamento == null) ? 0 : tipoPagamento.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FormaPagamento other = (FormaPagamento) obj;
        if (idFormaPagamento == null) {
            if (other.idFormaPagamento != null)
                return false;
        } else if (!idFormaPagamento.equals(other.idFormaPagamento))
            return false;
        if (tipoPagamento != other.tipoPagamento)
            return false;
        return true;
    }

    public void setIdFormaPagamento(Long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    
    

}
