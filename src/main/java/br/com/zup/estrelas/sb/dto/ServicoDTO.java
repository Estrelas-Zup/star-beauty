package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.enums.TipoServico;


public class ServicoDTO {

    @NotBlank(message = "O funcionário não pode ser nulo")
    private Funcionario funcionario;

    @NotBlank(message = "O nome do serviço não pode ser nulo")
    private String nomeServico;

    @NotBlank(message = "A duração não pode ser nulo")
    @Positive(message = "A duração tem que ser maior que zero")
    @NumberFormat(style = Style.NUMBER)
    private Double duracao;

    @NotBlank(message = "O valor do serviço não pode ser nulo")
    @Positive(message = "O valor do serviço tem que ser maior que zero")
    @NumberFormat(style = Style.NUMBER)
    private Double valorServico; // double mesmo?

    @NotBlank(message = "O tipo de serviço não pode ser nulo")
    private TipoServico tipoServico;

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
