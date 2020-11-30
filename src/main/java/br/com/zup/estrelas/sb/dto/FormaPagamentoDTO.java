package br.com.zup.estrelas.sb.dto;

import javax.validation.constraints.NotBlank;
import br.com.zup.estrelas.sb.enums.TipoPagamento;

public class FormaPagamentoDTO {
    
    @NotBlank (message = "O campo tipo de pagamento precisa ser preenchido.")    
    private TipoPagamento tipoPagamento;

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

   
}
