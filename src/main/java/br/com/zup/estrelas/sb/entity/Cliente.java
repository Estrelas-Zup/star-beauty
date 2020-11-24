package br.com.zup.estrelas.sb.entity;

import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.zup.estrelas.sb.abstrata.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente extends Usuario {
    
    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(name= "data_nascimento", nullable = false)
    private String dataNascimento; 

}
