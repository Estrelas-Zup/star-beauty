package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.zup.estrelas.sb.enums.TipoUsuario;

public class SalaoDTO {

    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "E-mail inválido")
    private String login;

    @NotBlank(message = "A senha não pode ser nulo")
    @Size(min = 8, max = 255, message = "A senha deve conter no mínimo 8 caracteres")
    private String senha;

    @NotBlank(message = "O nome não pode ser nulo")
    private String nome;

    @NotBlank(message = "O endereço não pode ser nulo")
    private String endereco;

    @NotBlank(message = "O CEP não pode ser nulo")
    @Max(value = 8, message = "O CEP deve conter no máximo 8 dígitos")
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

    private TipoUsuario tipoUsuario;

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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
