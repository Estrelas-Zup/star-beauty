package br.com.zup.estrelas.sb.dto;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ClienteDTO {
    
    private static final String PADRAO_CPF = "[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}";
    private static final String PADRAO_DATA = "(1|2)[0-9]{3}-(0[1-9]|1[0-2])-[0-9][0-9]";
    private static final String PADRAO_TELEFONE_CELULAR = "^[1-9][0-9]-(9)[1-9][0-9]{3}-[0-9]{4}$";
    private static final String PADRAO_CEP = "^\\d{5}-\\d{3}$";
    private static final String PADRAO_SENHA = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
    private static final String APENAS_LETRAS_ALFABETO = "[a-zA-Z ]+";

    @NotBlank (message = "O campo login precisa ser preenchido.")
    @Email (message = "O e-mail informado precisa ser válido.")
    private String login;
    
    @NotBlank (message = "O campo senha precisa ser preenchido.")
    @Size (min = 8, max = 255, message = "A senha precisa ter no mínimo 8 dígitos.")
    @Pattern(regexp = PADRAO_SENHA)
    private String senha;
    
    @NotBlank (message = "O campo nome precisa ser preenchido.")
    @Max (value = 255, message = "O nome não pode ter de 255 caracteres.")
    @Pattern(regexp = APENAS_LETRAS_ALFABETO)
    private String nome;
    
    @NotBlank (message = "O campo endereço precisa ser preenchido.")
    @Max (value = 255, message = "O endereço não pode ter mais de 255 caracteres.")
    private String endereco;
    
    @NotBlank (message = "O campo CEP precisa ser preenchido.")
    @Max (value = 8, message = "O CEP pode ter no máximo 8 dígitos.")
    @Pattern(regexp = PADRAO_CEP)
    private String cep;
    
    @NotBlank (message = "O campo estado precisa ser preenchido.")
    @Max (value = 255, message = "O estado não pode ter mais de 255 caracteres.")
    @Pattern(regexp = APENAS_LETRAS_ALFABETO)
    private String estado;
    
    @NotBlank (message = "O campo cidade precisa ser preenchido.")
    @Max (value = 255, message = "A cidade não pode ter mais de 255 caracteres.")
    @Pattern(regexp = APENAS_LETRAS_ALFABETO)
    private String cidade;
    
    @NotBlank (message = "O campo bairro precisa ser preenchido.")
    @Max (value = 255, message = "O bairro não pode ter mais de 255 caracteres.")
    private String bairro;
    
    @NotBlank (message = "O campo telefone precisa ser preenchido.")
    @Max (value = 11, message = "O telefone pode ter no máximo 11 dígitos.")
    @Pattern(regexp = PADRAO_TELEFONE_CELULAR)
    private String telefone;
    
    @NotBlank (message = "O campo email precisa ser preenchido.")
    @Email (message = "O e-mail informado precisa ser válido.")
    private String email;
    
    @NotBlank (message = "O campo CPF precisa ser preenchido.")
    @Max (value = 11, message = "O campo CPF não pode ter mais de 11 dígitos")
    @Pattern(regexp = PADRAO_CPF)
    private String cpf;
    
    @NotBlank (message = "O campo data de nascimento precisa ser preenchido.")
    @Past (message = "A data de nascimento precisa ser anterior ao dia de hoje.")
    @Pattern(regexp = PADRAO_DATA)
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
