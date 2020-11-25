package br.com.zup.estrelas.sb.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import br.com.zup.estrelas.sb.abstrata.Usuario;

@Entity
public class Cliente extends Usuario {
    
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(name= "data_nascimento", nullable = false)
    private LocalDate dataNascimento; 

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
