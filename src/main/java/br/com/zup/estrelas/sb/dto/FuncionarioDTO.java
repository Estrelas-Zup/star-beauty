package br.com.zup.estrelas.sb.dto;

import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class FuncionarioDTO {

    @NotBlank(message = "O campo nome precisa ser preenchido.")
    @Size(max = 255, message = "O nome não pode ter mais de 255 caracteres.")
    private String nome;

    @NotBlank(message = "O campo CPF precisa ser preenchido.")
    @Size(max = 14,
            message = "O campo CPF não pode ter mais de 14 dígitos contando com pontos e traço.")
    @CPF
    private String cpf;

    @NotBlank(message = "O campo telefone precisa ser preenchido.")
    @Size(max = 11, message = "O telefone pode ter no máximo 11 dígitos.")
    private String telefone;

    @NotBlank(message = "O campo horário de almoço precisa ser preenchido.")
    private String horarioAlmoco;

    @NotNull(message = "O campo id do salão precisa ser preenchido.")
    private Long idUsuario;

    @NotNull(message = "O campo hora de início deve ser preenchido.")
    private LocalTime horaInicioExpediente;

    @NotNull(message = "O campo hora de término deve ser preenchido.")
    private LocalTime horaFimExpediente;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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


}
