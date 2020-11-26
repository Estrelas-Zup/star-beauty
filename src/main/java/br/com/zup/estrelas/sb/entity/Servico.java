package br.com.zup.estrelas.sb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import br.com.zup.estrelas.sb.enums.TipoServico;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Servico {

    @Id
    @Column(name = "id_servico", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServico;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", foreignKey = @ForeignKey(name = "servicos_fk"))
    private Funcionario funcionario;

    @Column(name = "nome_servico", nullable = false)
    private String nomeServico;

    @Column(nullable = false)
    private Double duracao;

    @Column(name = "valor_servico")
    private Double valorServico;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servico", nullable = false)
    private TipoServico tipoServico;

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }

    public Double getValorServico() {
        return valorServico;
    }

    public void setValorServico(Double valorServico) {
        this.valorServico = valorServico;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

}
