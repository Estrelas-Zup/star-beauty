package br.com.zup.estrelas.sb.dto;

import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.enums.TipoServico;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServicoDTO {


    private Funcionario funcionario;

    private String nomeServico;

    private Double duracao;

    private Double valorServico;

    private TipoServico tipoServico;



}
