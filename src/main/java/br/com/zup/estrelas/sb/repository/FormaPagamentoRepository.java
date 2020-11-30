package br.com.zup.estrelas.sb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.enums.TipoPagamento;

@Repository
public interface FormaPagamentoRepository extends CrudRepository<FormaPagamento, Long> {

    boolean existsByTipoPagamento(TipoPagamento tipoPagamento);

}

