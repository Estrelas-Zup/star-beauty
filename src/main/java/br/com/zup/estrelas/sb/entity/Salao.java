package br.com.zup.estrelas.sb.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import br.com.zup.estrelas.sb.abstrata.Usuario;
import br.com.zup.estrelas.sb.enums.FormaPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Salao extends Usuario {

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false, name = "nome_fantasia")
    private String nomeFantasia;

    // confirmar com o joão relação.
    // enum formaPagamento é entidade?
    @OneToMany
    private List<FormaPagamento> formaPagamento;

    @OneToMany
    private List<Funcionario> funcionario;
}
