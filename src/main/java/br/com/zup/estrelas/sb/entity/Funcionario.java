package br.com.zup.estrelas.sb.entity;

import java.time.LocalTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Funcionario {

    @Id
    @Column(name = "id_funcionario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(name = "horario_almoco", nullable = false)
    private String horarioAlmoco;

    @Column(name = "hora_inicio_expediente", nullable = false)
    private LocalTime horaInicioExpediente;

    @Column(name = "hora_fim_expediente", nullable = false)
    private LocalTime horaFimExpediente;

    private boolean ativo;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "funcionarios_fk"))
    private Salao salao;

    @ManyToMany
    private List<Servico> servicos;

    @JsonManagedReference
    @OneToMany(mappedBy = "funcionario")
    private List<Agendamento> agendamentos;

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getHorarioAlmoco() {
        return horarioAlmoco;
    }

    public void setHorarioAlmoco(String horarioAlmoco) {
        this.horarioAlmoco = horarioAlmoco;
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


    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Salao getSalao() {
        return salao;
    }

    public void setSalao(Salao salao) {
        this.salao = salao;
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
    
    @Override
    public String toString() {
        return "Funcionario [idFuncionario=" + idFuncionario + ", nome=" + nome + ", cpf=" + cpf
                + ", telefone=" + telefone + ", horarioAlmoco=" + horarioAlmoco
                + ", horaInicioExpediente=" + horaInicioExpediente + ", horaFimExpediente="
                + horaFimExpediente + ", ativo=" + ativo + ", salao=" + salao + ", servicos="
                + servicos + ", agendamentos=" + agendamentos + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((agendamentos == null) ? 0 : agendamentos.hashCode());
        result = prime * result + (ativo ? 1231 : 1237);
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((horaFimExpediente == null) ? 0 : horaFimExpediente.hashCode());
        result = prime * result
                + ((horaInicioExpediente == null) ? 0 : horaInicioExpediente.hashCode());
        result = prime * result + ((horarioAlmoco == null) ? 0 : horarioAlmoco.hashCode());
        result = prime * result + ((idFuncionario == null) ? 0 : idFuncionario.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((salao == null) ? 0 : salao.hashCode());
        result = prime * result + ((servicos == null) ? 0 : servicos.hashCode());
        result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
        Funcionario other = (Funcionario) obj;
        if (agendamentos == null) {
            if (other.agendamentos != null)
                return false;
        } else if (!agendamentos.equals(other.agendamentos))
            return false;
        if (ativo != other.ativo)
            return false;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
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
        if (horarioAlmoco == null) {
            if (other.horarioAlmoco != null)
                return false;
        } else if (!horarioAlmoco.equals(other.horarioAlmoco))
            return false;
        if (idFuncionario == null) {
            if (other.idFuncionario != null)
                return false;
        } else if (!idFuncionario.equals(other.idFuncionario))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (salao == null) {
            if (other.salao != null)
                return false;
        } else if (!salao.equals(other.salao))
            return false;
        if (servicos == null) {
            if (other.servicos != null)
                return false;
        } else if (!servicos.equals(other.servicos))
            return false;
        if (telefone == null) {
            if (other.telefone != null)
                return false;
        } else if (!telefone.equals(other.telefone))
            return false;
        return true;
    }

    
}
