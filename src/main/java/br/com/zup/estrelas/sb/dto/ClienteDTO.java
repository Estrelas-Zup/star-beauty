package br.com.zup.estrelas.sb.dto;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class ClienteDTO {
    
    @NotBlank (message = "O campo login precisa ser preenchido.")
    @Email (message = "O e-mail informado precisa ser válido.")
    private String login;
    
    @NotBlank (message = "O campo senha precisa ser preenchido.")
    @Size (min = 6, max = 16, message = "A senha precisa ter no mínimo 6 dígitos.")
    private String senha;
    
    @NotBlank (message = "O campo nome precisa ser preenchido.")
    @Size (min = 3, max = 255, message = "O nome não pode ter mais de 255 caracteres.")
    private String nome;
    
    @NotBlank (message = "O campo endereço precisa ser preenchido.")
    @Max (value = 255, message = "O endereço não pode ter mais de 255 caracteres.")
    private String endereco;
    
    @NotBlank (message = "O campo CEP precisa ser preenchido.")
    @Max (value = 8, message = "O CEP pode ter no máximo 8 dígitos.")
    private String cep;
    
    @NotBlank (message = "O campo estado precisa ser preenchido.")
    @Max (value = 18, message = "O estado não pode ter mais de 18 caracteres.")
    private String estado;
    
    @NotBlank (message = "O campo cidade precisa ser preenchido.")
    @Max (value = 28, message = "A cidade não pode ter mais de 28 caracteres.")
    private String cidade;
    
    @NotBlank (message = "O campo bairro precisa ser preenchido.")
    @Max (value = 255, message = "O bairro não pode ter mais de 255 caracteres.")
    private String bairro;
    
    @NotBlank (message = "O campo telefone precisa ser preenchido.")
    @Max (value = 11, message = "O telefone pode ter no máximo 11 dígitos.")
    private String telefone;
    
    @NotBlank (message = "O campo email precisa ser preenchido.")
    @Email (message = "O e-mail informado precisa ser válido.")
    private String email;
    
    @NotBlank (message = "O campo CPF precisa ser preenchido.")
    @Max (value = 11, message = "O campo CPF não pode ter mais de 11 dígitos")
    private String cpf;
    
    @NotBlank (message = "O campo data de nascimento precisa ser preenchido.")
    @Past (message = "A data de nascimento precisa ser anterior ao dia de hoje.")
    private LocalDate dataNascimento;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

}
