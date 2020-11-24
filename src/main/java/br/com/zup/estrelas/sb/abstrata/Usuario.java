package br.com.zup.estrelas.sb.abstrata;

import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.zup.estrelas.sb.enums.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Usuario {

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;
    
    private Blob foto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean ativo;

    @Column(name = "tipo_usuario")
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;


}
