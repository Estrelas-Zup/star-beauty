package br.com.zup.estrelas.sb.abstrata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import br.com.zup.estrelas.sb.enums.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = true)
    private String nome;

    @Column(nullable = true)
    private String endereco;

    @Column(nullable = true)
    private String cep;

    @Column(nullable = true)
    private String estado;

    @Column(nullable = true)
    private String cidade;

    @Column(nullable = true)
    private String bairro;

    @Column(nullable = true)
    private String telefone;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private boolean ativo;

    @Column(name = "tipo_usuario")
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

}
