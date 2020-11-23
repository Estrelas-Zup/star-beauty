package br.com.zup.estrelas.sb.enums;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum FormaPagamento {

    DINHEIRO("dinheiro"), PIX("pix"), CARTAO_DEBITO("cartao de débito"), CARTAO_CREDITO_HIPERCARD(
            "Cartão de crédito hipercard"), CARTAO_CREDITO_DINERSCLUB(
                    "Cartão de crédito Dinersclub"), CARTAO_CREDITO_ELO(
                            "Cartão de crédito Elo"), CARTAO_CREDITO_AMERICAN_EXPRESS(
                                    "Cartão de crédito American Express"), CARTAO_CREDITO_VISA(
                                            "Cartão de crédito Visa"), CARTAO_CREDITO_MASTERCARD(
                                                    "Cartão de crédito Mastercard");

    private String value;

    FormaPagamento(String value) {
        this.value = value;
    }

}
