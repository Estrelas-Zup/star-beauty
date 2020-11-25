package br.com.zup.estrelas.sb.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import br.com.zup.estrelas.sb.abstrata.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente extends Usuario {


    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @OneToMany
    private List<Agendamento> agendamento;

}
