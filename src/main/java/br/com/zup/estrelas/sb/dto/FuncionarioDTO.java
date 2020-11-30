package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class FuncionarioDTO {

    private static final String APENAS_LETRAS_ALFABETO = "[a-zA-Z ]+";
    private static final String PADRAO_TELEFONE_CELULAR = "^[1-9][0-9]-(9)[1-9][0-9]{3}-[0-9]{4}$";
    private static final String PADRAO_CPF = "[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}";

    @NotBlank(message = "O campo nome precisa ser preenchido.")
    @Max(value = 255, message = "O nome não pode ter mais de 255 caracteres.")
    @Pattern(regexp = APENAS_LETRAS_ALFABETO)
    private String nome;

    @NotBlank(message = "O campo CPF precisa ser preenchido.")
    @Max(value = 11, message = "O campo CPF não pode ter mais de 11 dígitos")
    @Pattern(regexp = PADRAO_CPF)
    private String cpf;

    @NotBlank(message = "O campo telefone precisa ser preenchido.")
    @Max(value = 11, message = "O telefone pode ter no máximo 11 dígitos.")
    @Pattern(regexp = PADRAO_TELEFONE_CELULAR)
    private String telefone;

    @NotBlank(message = "O campo horário de almoço precisa ser preenchido.")
    private String horarioAlmoco;

    @NotBlank(message = "O campo id do salão precisa ser preenchido.")
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
