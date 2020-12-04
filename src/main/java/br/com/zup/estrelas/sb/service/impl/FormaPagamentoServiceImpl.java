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
            "Forma de pagamento já cadastrada";
    private static final String CADASTRO_REALIZADO_COM_SUCESSO =
            "Cadastro de forma de pagamento realizado com sucesso.";
    private static final String FORMA_DE_PAGAMENTO_INEXISTENTE = "Forma de pagamento inexistente.";
    private static final String FORMA_DE_PAGAMENTO_REMOVIDA_COM_SUCESSO =
            "Forma de pagamento removida com sucesso.";
    private static final String FORMA_DE_PAGAMENTO_JA_EXISTENTE =
            "Forma de pagamento já existente.";
    private static final String FORMA_DE_PAGAMENTO_ALTERADA_COM_SUCESSO =
            "Forma de pagamento alterada com sucesso.";

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @Override
    public FormaPagamento buscaFormaPagamento(Long idFormaPagamento)
            throws RegrasDeNegocioException {
        return formaPagamentoRepository.findById(idFormaPagamento)
                .orElseThrow(() -> new RegrasDeNegocioException(
                        "Forma pagamento não encontrada pelo Id: " + idFormaPagamento));
    }

    @Override
    public List<FormaPagamento> listaFormaPagamentos() {
        return (List<FormaPagamento>) formaPagamentoRepository.findAll();
    }

    @Override
    public MensagemDTO adicionaFormaPagamento(FormaPagamentoDTO formaPagamentoDTO)
            throws RegrasDeNegocioException {

        // como verificar se o valor do enum é valido?

        if (formaPagamentoRepository.existsByTipoPagamento(formaPagamentoDTO.getTipoPagamento())) {
            throw new RegrasDeNegocioException(FORMA_DE_PAGAMENTO_JA_CADASTRADA);
        }

        return this.insereFormaPagamento(formaPagamentoDTO);
    }

    @Override
    public MensagemDTO alteraFormaPagamento(Long idFormaPagamento,
            FormaPagamentoDTO alteraFormaPagamentoDTO) throws RegrasDeNegocioException {

        if (!formaPagamentoRepository.existsById(idFormaPagamento)) {
            throw new RegrasDeNegocioException(FORMA_DE_PAGAMENTO_INEXISTENTE);
        }

        if (formaPagamentoRepository
                .existsByTipoPagamento(alteraFormaPagamentoDTO.getTipoPagamento())) {
            throw new RegrasDeNegocioException(FORMA_DE_PAGAMENTO_JA_EXISTENTE);
        }

        return this.midificaFormaPagamento(idFormaPagamento, alteraFormaPagamentoDTO);
    }

    @Override
    public MensagemDTO removeFormaPagamento(Long idFormaPagamento) throws RegrasDeNegocioException {

        if (!formaPagamentoRepository.existsById(idFormaPagamento)) {
            throw new RegrasDeNegocioException(FORMA_DE_PAGAMENTO_INEXISTENTE);
        }

        formaPagamentoRepository.deleteById(idFormaPagamento);

        throw new RegrasDeNegocioException(FORMA_DE_PAGAMENTO_REMOVIDA_COM_SUCESSO);
    }

    private MensagemDTO insereFormaPagamento(FormaPagamentoDTO formaPagamentoDTO) {

        FormaPagamento formaPagamento = new FormaPagamento();

        BeanUtils.copyProperties(formaPagamentoDTO, formaPagamento);

        formaPagamentoRepository.save(formaPagamento);

        return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
    }

    private MensagemDTO midificaFormaPagamento(Long idFormaPagamento,
            FormaPagamentoDTO alteraFormaPagamentoDTO) {

        FormaPagamento formaPagamento = formaPagamentoRepository.findById(idFormaPagamento).get();

        BeanUtils.copyProperties(alteraFormaPagamentoDTO, formaPagamento);

        formaPagamentoRepository.save(formaPagamento);

        return new MensagemDTO(FORMA_DE_PAGAMENTO_ALTERADA_COM_SUCESSO);
    }
}


