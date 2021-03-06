package br.com.zup.estrelas.sb.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.FormaPagamentoRepository;
import br.com.zup.estrelas.sb.service.FormaPagamentoService;

@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {

    private static final String FORMA_DE_PAGAMENTO_JA_CADASTRADA =
            "FORMA DE PAGAMENTO JA CADASTRADA!";
    private static final String FORMA_DE_PAGAMENTO_INEXISTENTE = "FORMA DE PAGAMENTO INEXISTENTE!";
    private static final String FORMA_DE_PAGAMENTO_REMOVIDA_COM_SUCESSO =
            "FORMA DE PAGAMENTO REMOVIDA COM SUCESSO!";
    private static final String FORMA_DE_PAGAMENTO_JA_EXISTENTE =
            "FORMA DE PAGAMENTO JÁ EXISTENTE!";
    private static final String FORMA_DE_PAGAMENTO_NAO_ENCONTRADA_PELO_ID =
            "FORMA DE PAGAMENTO NÃO ENCONTRADA PELO ID ";

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @Override
    public FormaPagamento buscaFormaPagamento(Long idFormaPagamento)
            throws RegrasDeNegocioException {
        return formaPagamentoRepository.findById(idFormaPagamento)
                .orElseThrow(() -> new RegrasDeNegocioException(
                        FORMA_DE_PAGAMENTO_NAO_ENCONTRADA_PELO_ID + idFormaPagamento));
    }

    @Override
    public List<FormaPagamento> listaFormaPagamentos() {
        return (List<FormaPagamento>) formaPagamentoRepository.findAll();
    }

    @Override
    public FormaPagamento adicionaFormaPagamento(FormaPagamentoDTO formaPagamentoDTO)
            throws RegrasDeNegocioException {

        // como verificar se o valor do enum é valido?

        if (formaPagamentoRepository.existsByTipoPagamento(formaPagamentoDTO.getTipoPagamento())) {
            throw new RegrasDeNegocioException(FORMA_DE_PAGAMENTO_JA_CADASTRADA);
        }

        return this.insereFormaPagamento(formaPagamentoDTO);
    }

    @Override
    public FormaPagamento alteraFormaPagamento(Long idFormaPagamento,
            FormaPagamentoDTO alteraFormaPagamentoDTO) throws RegrasDeNegocioException {

        if (!formaPagamentoRepository.existsById(idFormaPagamento)) {
            throw new RegrasDeNegocioException(FORMA_DE_PAGAMENTO_INEXISTENTE);
        }

        if (formaPagamentoRepository
                .existsByTipoPagamento(alteraFormaPagamentoDTO.getTipoPagamento())) {
            throw new RegrasDeNegocioException(FORMA_DE_PAGAMENTO_JA_EXISTENTE);
        }

        return this.modificaFormaPagamento(idFormaPagamento, alteraFormaPagamentoDTO);
    }

    @Override
    public MensagemDTO removeFormaPagamento(Long idFormaPagamento) throws RegrasDeNegocioException {

        if (!formaPagamentoRepository.existsById(idFormaPagamento)) {
            throw new RegrasDeNegocioException(FORMA_DE_PAGAMENTO_INEXISTENTE);
        }

        formaPagamentoRepository.deleteById(idFormaPagamento);

        return new MensagemDTO (FORMA_DE_PAGAMENTO_REMOVIDA_COM_SUCESSO);
    }

    private FormaPagamento insereFormaPagamento(FormaPagamentoDTO formaPagamentoDTO) {

        FormaPagamento formaPagamento = new FormaPagamento();

        BeanUtils.copyProperties(formaPagamentoDTO, formaPagamento);

        formaPagamentoRepository.save(formaPagamento);

        return formaPagamento;
    }

    private FormaPagamento modificaFormaPagamento(Long idFormaPagamento,
            FormaPagamentoDTO alteraFormaPagamentoDTO) {

        FormaPagamento formaPagamento = formaPagamentoRepository.findById(idFormaPagamento).get();

        BeanUtils.copyProperties(alteraFormaPagamentoDTO, formaPagamento);

        formaPagamentoRepository.save(formaPagamento);

        return formaPagamento;
    }
}


