package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SalaoDTO {

    private static final String PADRAO_CNPJ = "^\\d{3}.?\\d{3}.?\\d{3}/?\\d{3}-?\\d{2}$";
    private static final String PADRAO_CEP = "^\\d{5}-\\d{3}$";
    private static final String PADRAO_TELEFONE_CELULAR = "^[1-9][0-9]-(9)[1-9][0-9]{3}-[0-9]{4}$";
    private static final String PADRAO_SENHA = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
    private static final String APENAS_LETRAS_ALFABETO = "[a-zA-Z ]+";

    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "E-mail inválido")
    private String login;

    @NotBlank(message = "A senha não pode ser nulo")
    @Size(min = 8,  max = 255, message = "A senha deve conter no mínimo 8 caracteres")
    @Pattern(regexp = PADRAO_SENHA)
    private String senha;

    @NotBlank(message = "O nome não pode ser nulo")
    @Pattern(regexp = APENAS_LETRAS_ALFABETO)
    private String nome;

    @NotBlank(message = "O endereço não pode ser nulo")
    private String endereco;

    @NotBlank(message = "O CEP não pode ser nulo")
    @Max(value = 8, message = "O CEP deve conter no máximo 8 dígitos")
    @Pattern(regexp = PADRAO_CEP)
    private String cep;

    @NotBlank(message = "O Estado não pode ser nulo")
    @Pattern(regexp = APENAS_LETRAS_ALFABETO)
    private String estado;

    @NotBlank(message = "A cidade não pode ser nulo")
    @Pattern(regexp = APENAS_LETRAS_ALFABETO)
    private String cidade;

    @NotBlank(message = "O bairro não pode ser nulo")
    private String bairro;

    @NotBlank(message = "O telefone não pode ser nulo")
    @Pattern(regexp = PADRAO_TELEFONE_CELULAR)
    private String telefone;
    
    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "O email tem que ser válido")
    private String email;

    @NotBlank(message = "O CNPJ não pode ser nulo")
    @Pattern(regexp = PADRAO_CNPJ)
    private String cnpj;

    @NotBlank(message = "O Nome Fantasia não pode ser nulo")
    private String nomeFantasia;

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


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

}
