package br.com.zup.estrelas.sb.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import br.com.zup.estrelas.sb.abstrata.Usuario;
import br.com.zup.estrelas.sb.enums.FormaPagamento;

@Entity
public class Salao extends Usuario {

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false, name = "nome_fantasia")
    private String nomeFantasia;

    // confirmar com o joão relação.
    // enum formaPagamento é entidade?
    @OneToMany
    private List<FormaPagamento> formasPagamentos;

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

    public List<FormaPagamento> getFormasPagamentoss() {
        return formasPagamentos;
    }

    public void setFormasPagamentos(List<FormaPagamento> formaPagamento) {
        this.formasPagamentos = formaPagamento;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionario) {
        this.funcionarios = funcionario;
    }

}
