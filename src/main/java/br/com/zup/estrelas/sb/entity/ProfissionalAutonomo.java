package br.com.zup.estrelas.sb.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import br.com.zup.estrelas.sb.abstrata.Usuario;

@Entity
public class ProfissionalAutonomo extends Usuario {

    @Column(nullable = false, unique = true)
    private String cpfCnpj;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @ManyToMany
    private List<Servico> servicos;

    @JsonManagedReference
    @OneToMany(mappedBy = "autonomo")
    private List<Agendamento> agendamentos;

    @ManyToMany
    private List<FormaPagamento> formasPagamentos;

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<FormaPagamento> getFormasPagamentos() {
        return formasPagamentos;
    }

    public void setFormasPagamentos(List<FormaPagamento> formasPagamentos) {
        this.formasPagamentos = formasPagamentos;
    }

}
