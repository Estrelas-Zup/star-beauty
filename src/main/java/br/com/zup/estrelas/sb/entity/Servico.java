package br.com.zup.estrelas.sb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.zup.estrelas.sb.enums.TipoServico;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
public class Servico {
    
    @Id
    @Column(name = "id_servico", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServico;
    
    private Funcionario funcionario; // chave estrangeira 
    
    @Column(name = "nome_servico", nullable = false)
    private String nomeServico;
    
    @Column(nullable = false)
    private Double duracao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servico", nullable = false)
    private TipoServico tipoServico;
}


