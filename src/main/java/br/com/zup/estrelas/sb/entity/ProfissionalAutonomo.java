package br.com.zup.estrelas.sb.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ProfissionalAutonomo extends Usuario {

    @Column(unique = true)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "hora_inicio_expediente", nullable = false)
    private LocalTime horaInicioExpediente;

    @Column(name = "hora_fim_expediente", nullable = false)
    private LocalTime horaFimExpediente;

    @ManyToMany
    private List<Servico> servicos;

    @JsonManagedReference
    @OneToMany(mappedBy = "autonomo")
    private List<Agendamento> agendamentos;

    @ManyToMany
    private List<FormaPagamento> formasPagamento;

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

    public LocalTime getHoraInicioExpediente() {
        return horaInicioExpediente;
    }

    public void setHoraInicioExpediente(LocalTime horaInicioExpediente) {
        this.horaInicioExpediente = horaInicioExpediente;
    }

    public LocalTime getHoraFimExpediente() {
        return horaFimExpediente;
    }

    public void setHoraFimExpediente(LocalTime horaFimExpediente) {
        this.horaFimExpediente = horaFimExpediente;
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

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((agendamentos == null) ? 0 : agendamentos.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
        result = prime * result + ((formasPagamento == null) ? 0 : formasPagamento.hashCode());
        result = prime * result + ((horaFimExpediente == null) ? 0 : horaFimExpediente.hashCode());
        result = prime * result
                + ((horaInicioExpediente == null) ? 0 : horaInicioExpediente.hashCode());
        result = prime * result + ((servicos == null) ? 0 : servicos.hashCode());
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
        ProfissionalAutonomo other = (ProfissionalAutonomo) obj;
        if (agendamentos == null) {
            if (other.agendamentos != null)
                return false;
        } else if (!agendamentos.equals(other.agendamentos))
            return false;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (dataNascimento == null) {
            if (other.dataNascimento != null)
                return false;
        } else if (!dataNascimento.equals(other.dataNascimento))
            return false;
        if (formasPagamento == null) {
            if (other.formasPagamento != null)
                return false;
        } else if (!formasPagamento.equals(other.formasPagamento))
            return false;
        if (horaFimExpediente == null) {
            if (other.horaFimExpediente != null)
                return false;
        } else if (!horaFimExpediente.equals(other.horaFimExpediente))
            return false;
        if (horaInicioExpediente == null) {
            if (other.horaInicioExpediente != null)
                return false;
        } else if (!horaInicioExpediente.equals(other.horaInicioExpediente))
            return false;
        if (servicos == null) {
            if (other.servicos != null)
                return false;
        } else if (!servicos.equals(other.servicos))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProfissionalAutonomo [cpf=" + cpf + ", dataNascimento=" + dataNascimento
                + ", horaInicioExpediente=" + horaInicioExpediente + ", horaFimExpediente="
                + horaFimExpediente + ", servicos=" + servicos + ", agendamentos=" + agendamentos
                + ", formasPagamento=" + formasPagamento + "]";
    }

}
