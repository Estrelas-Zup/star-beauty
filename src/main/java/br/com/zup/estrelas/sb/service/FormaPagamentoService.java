package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;


public interface FormaPagamentoService {

    public MensagemDTO adicionaFormaPagamento(FormaPagamentoDTO formaPagamentoDTO);

    public FormaPagamento buscaFormaPagamento(Long idFormaPagamento);

    public List<FormaPagamento> listaFormaPagamentos();

    public MensagemDTO removeFormaPagamento(Long idFormaPagamento);

    public MensagemDTO alteraFormaPagamento(Long idFormaPagamento, FormaPagamentoDTO alteraFormaPagamentoDTO);




}
