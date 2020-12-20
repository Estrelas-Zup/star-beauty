package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface FormaPagamentoService {

    public FormaPagamento adicionaFormaPagamento(FormaPagamentoDTO formaPagamentoDTO)
            throws RegrasDeNegocioException;

    public FormaPagamento buscaFormaPagamento(Long idFormaPagamento)
            throws RegrasDeNegocioException;

    public List<FormaPagamento> listaFormaPagamentos();

    public MensagemDTO removeFormaPagamento(Long idFormaPagamento) throws RegrasDeNegocioException;

    public FormaPagamento alteraFormaPagamento(Long idFormaPagamento,
            FormaPagamentoDTO alteraFormaPagamentoDTO) throws RegrasDeNegocioException;

}
