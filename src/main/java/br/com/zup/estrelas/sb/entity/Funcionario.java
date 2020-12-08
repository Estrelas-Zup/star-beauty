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

}
