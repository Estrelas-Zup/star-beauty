package br.com.zup.estrelas.sb.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import br.com.zup.estrelas.sb.abstrata.Usuario;

@Entity
public class Salao extends Usuario {

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false, name = "nome_fantasia")
    private String nomeFantasia;

    @ManyToMany
    private List<FormaPagamento> formasPagamentos;

    @JsonIdentityReference
    @OneToMany
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

    public List<FormaPagamento> getFormaPagamento() {
        return formasPagamentos;
    }

    public void setFormaPagamento(List<FormaPagamento> formasPagamentos) {
        this.formasPagamentos = formasPagamentos;
    }

    public List<Funcionario> getFuncionario() {
        return funcionarios;
    }

    public void setFuncionario(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
