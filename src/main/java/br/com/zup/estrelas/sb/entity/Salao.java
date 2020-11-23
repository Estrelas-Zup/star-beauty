package br.com.zup.estrelas.sb.entity;

import java.sql.Blob;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.zup.estrelas.sb.abstrata.Usuario;
import br.com.zup.estrelas.sb.enums.FormaPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Salao extends Usuario {

    private Blob foto;

    @Id
    @Column(name = "id_salao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalao;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, name = "nome_fantasia")
    private String nomeFantasia;

    // relação chave estrangeira
    private List<FormaPagamento> formaPagamento;

    // relação chave estrangeira
    private List<Funcionario> funcionario;


}