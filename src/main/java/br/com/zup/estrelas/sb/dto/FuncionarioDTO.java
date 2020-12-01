package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FuncionarioDTO {

    @NotBlank(message = "O campo nome precisa ser preenchido.")
    @Max(value = 255, message = "O nome não pode ter mais de 255 caracteres.")
    private String nome;

    @NotBlank(message = "O campo CPF precisa ser preenchido.")
    @Max(value = 11, message = "O campo CPF não pode ter mais de 11 dígitos")
    private String cpf;

    @NotBlank(message = "O campo telefone precisa ser preenchido.")
    @Max(value = 11, message = "O telefone pode ter no máximo 11 dígitos.")
    private String telefone;

    @NotBlank(message = "O campo horário de almoço precisa ser preenchido.")
    private String horarioAlmoco;

    @NotNull(message = "O campo id do salão precisa ser preenchido.")
    private Long idSalao;

    public Long getIdSalao() {
        return idSalao;
    }

    public void setIdSalao(Long idSalao) {
        this.idSalao = idSalao;
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



}
