package br.com.zup.estrelas.sb.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import br.com.zup.estrelas.sb.abstrata.Usuario;

@Entity
public class ProfissionalAutonomo extends Usuario {

    @Column(nullable = false, unique = false)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @OneToMany
    private List<Servico> servico;

    @OneToMany
    private List<Agendamento> agendamento;

    @ManyToMany
    private List<FormaPagamento> formasPagamentos;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Servico> getServico() {
        return servico;
    }

    public void setServico(List<Servico> servico) {
        this.servico = servico;
    }

    public List<Agendamento> getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(List<Agendamento> agendamento) {
        this.agendamento = agendamento;
    }

    public List<FormaPagamento> getFormasPagamentos() {
        return formasPagamentos;
    }

    public void setFormasPagamentos(List<FormaPagamento> formasPagamentos) {
        this.formasPagamentos = formasPagamentos;
    }

}
