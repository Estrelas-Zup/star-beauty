package br.com.zup.estrelas.sb.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import br.com.zup.estrelas.sb.enums.TipoUsuario;

public class ProfissionalAutonomoDTO {

    @NotBlank(message = "O campo login precisa ser preenchido.")
    @Email(message = "O e-mail informado precisa ser válido.")
    private String login;

    @NotBlank(message = "O campo senha precisa ser preenchido.")
    @Size(min = 8, max = 255, message = "A senha precisa ter no mínimo 8 dígitos.")
    private String senha;

    @NotBlank(message = "O campo nome precisa ser preenchido.")
    @Size(max = 255, message = "O nome não pode ter de 255 caracteres.")
    private String nome;

    @Size(max = 14,
            message = "O campo CPF não pode ter mais de 14 dígitos contando com pontos e traço.")
    @CPF
    private String cpf;

    @NotNull(message = "O campo data de nascimento precisa ser preenchido.")
    @Past(message = "A data de nascimento precisa ser anterior ao dia de hoje.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo endereço precisa ser preenchido.")
    @Size(max = 255, message = "O endereço não pode ter mais de 255 caracteres.")
    private String endereco;

    @NotBlank(message = "O campo CEP precisa ser preenchido.")
    @Size(max = 9, message = "O CEP pode ter no máximo 9 caracteres.")
    private String cep;

    @NotBlank(message = "O campo cidade precisa ser preenchido.")
    @Size(max = 255, message = "A cidade não pode ter mais de 255 caracteres.")
    private String cidade;

    @NotBlank(message = "O campo estado precisa ser preenchido.")
    @Size(max = 255, message = "O estado não pode ter mais de 255 caracteres.")
    private String estado;

    @NotBlank(message = "O campo bairro precisa ser preenchido.")
    @Size(max = 255, message = "O bairro não pode ter mais de 255 caracteres.")
    private String bairro;

    @NotBlank(message = "O campo telefone precisa ser preenchido.")
    @Size(max = 11, message = "O telefone pode ter no máximo 11 dígitos.")
    private String telefone;

    @NotBlank(message = "O campo email precisa ser preenchido.")
    @Email(message = "O e-mail informado precisa ser válido.")
    private String email;
    
    @NotBlank(message = "O campo inicio do expediente precisa ser preenchido.")
    private LocalTime horaInicioExpediente;

    @NotBlank(message = "O campo fim do expediente precisa ser preenchido.")
    private LocalTime horaFimExpediente;

    private TipoUsuario tipoUsuario;

    @NotNull(message = "O campo hora de início deve ser preenchido.")
    private LocalTime horaInicioExpediente;

    @NotNull(message = "O campo hora de término deve ser preenchido.")
    private LocalTime horaFimExpediente;

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
