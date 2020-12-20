package br.com.zup.estrelas.sb.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Salao extends Usuario {

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false, name = "nome_fantasia")
    private String nomeFantasia;

    @ManyToMany
    private List<FormaPagamento> formasPagamento;

    @JsonManagedReference
    @OneToMany(mappedBy = "salao")
    private List<Funcionario> funcionarios;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
        result = prime * result + ((formasPagamento == null) ? 0 : formasPagamento.hashCode());
        result = prime * result + ((funcionarios == null) ? 0 : funcionarios.hashCode());
        result = prime * result + ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
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
        Salao other = (Salao) obj;
        if (cnpj == null) {
            if (other.cnpj != null)
                return false;
        } else if (!cnpj.equals(other.cnpj))
            return false;
        if (formasPagamento == null) {
            if (other.formasPagamento != null)
                return false;
        } else if (!formasPagamento.equals(other.formasPagamento))
            return false;
        if (funcionarios == null) {
            if (other.funcionarios != null)
                return false;
        } else if (!funcionarios.equals(other.funcionarios))
            return false;
        if (nomeFantasia == null) {
            if (other.nomeFantasia != null)
                return false;
        } else if (!nomeFantasia.equals(other.nomeFantasia))
            return false;
        return true;
    }
    
    

}
