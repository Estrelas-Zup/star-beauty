package br.com.zup.estrelas.sb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.zup.estrelas.sb.enums.TipoServico;

@Entity
public class Servico {

    @Id
    @Column(name = "id_servico", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServico;

    @Column(name = "nome_servico", nullable = false, unique = true)
    private String nomeServico;

    @Column(nullable = false)
    private String duracao;

    @Column(name = "valor_servico")
    private Double valorServico;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servico", nullable = false)
    private TipoServico tipoServico;
<<<<<<< HEAD
    
=======

>>>>>>> 20d21aa... testes unitarios servico/autonomo
    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Double getValorServico() {
        return valorServico;
    }

    public void setValorServico(Double valorServico) {
        this.valorServico = valorServico;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

<<<<<<< HEAD
=======
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((duracao == null) ? 0 : duracao.hashCode());
        result = prime * result + ((idServico == null) ? 0 : idServico.hashCode());
        result = prime * result + ((nomeServico == null) ? 0 : nomeServico.hashCode());
        result = prime * result + ((tipoServico == null) ? 0 : tipoServico.hashCode());
        result = prime * result + ((valorServico == null) ? 0 : valorServico.hashCode());
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
        Servico other = (Servico) obj;
        if (duracao == null) {
            if (other.duracao != null)
                return false;
        } else if (!duracao.equals(other.duracao))
            return false;
        if (idServico == null) {
            if (other.idServico != null)
                return false;
        } else if (!idServico.equals(other.idServico))
            return false;
        if (nomeServico == null) {
            if (other.nomeServico != null)
                return false;
        } else if (!nomeServico.equals(other.nomeServico))
            return false;
        if (tipoServico != other.tipoServico)
            return false;
        if (valorServico == null) {
            if (other.valorServico != null)
                return false;
        } else if (!valorServico.equals(other.valorServico))
            return false;
        return true;
    }

>>>>>>> 20d21aa... testes unitarios servico/autonomo
}
