package br.com.zup.estrelas.sb.dto;


import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.enums.TipoPagamento;

public class TransacaoDTO {
    
    private Long idTransacao;

    private Agendamento agendamento;

    private Double valor;

    private TipoPagamento formaPagmento;

    private String nomeCliente;

    private String nomeSalao;

    public Long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoPagamento getFormaPagmento() {
        return formaPagmento;
    }

    public void setFormaPagmento(TipoPagamento formaPagmento) {
        this.formaPagmento = formaPagmento;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeSalao() {
        return nomeSalao;
    }

    public void setNomeSalao(String nomeSalao) {
        this.nomeSalao = nomeSalao;
    }
    
    

}
