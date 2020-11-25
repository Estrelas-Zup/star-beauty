package br.com.zup.estrelas.sb.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
//@Entity
public class Funcionario {

    @Id
    @Column(name = "id_funcionario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

//    @Column(nullable = false)
//    private Blob foto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(name = "horario_almoco", nullable = false)
    private String horarioAlmoco;

    private boolean ativo;

    //@ManyToOne
    private Salao salao; // chave Estrangeira

    //OneToMany
    private List<Servico> servico;

    //OneToMany
    private List<Agendamento> agendamento;

}