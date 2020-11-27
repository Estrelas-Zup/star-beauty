package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SalaoDTO {

    @NotBlank
    @Size(min = 6, max = 100, message = "O login deve conter no mínimo 6 caracteres")
    private String login;

    @NotBlank
    @Size(min = 6,  max = 100, message = "A senha deve conter no mínimo 6 caracteres")
    private String senha;

    @NotBlank(message = "O nome não pode ser nulo")
    private String nome;

    @NotBlank(message = "O endereço não pode ser nulo")
    private String endereco;

    @NotBlank(message = "O CEP não pode ser nulo")
    private String cep;

    @NotBlank(message = "O Estado não pode ser nulo")
    private String estado;

    @NotBlank(message = "A cidade não pode ser nulo")
    private String cidade;

    @NotBlank(message = "O bairro não pode ser nulo")
    private String bairro;

    @NotBlank(message = "O telefone não pode ser nulo")
    private String telefone;
    
    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "O email tem que ser válido")
    private String email;

    @NotBlank(message = "O CNPJ não pode ser nulo")
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
