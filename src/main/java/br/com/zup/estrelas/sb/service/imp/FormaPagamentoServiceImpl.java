package br.com.zup.estrelas.sb.service.imp;

import java.util.Collections;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.repository.FormaPagamentoRepository;
import br.com.zup.estrelas.sb.service.FormaPagamentoService;

@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {
    
    private static final String FORMA_DE_PAGAMENTO_JA_CADASTRADA =
            "Forma de pagamento já cadastrada";
    
    
    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;
    
    public MensagemDTO adicionaFormaPagamento(FormaPagamentoDTO formaPagamentoDTO) {
        
        if(formaPagamentoRepository.existsById(formaPagamentoDTO.getTipoPagamento()) {
            
            return new MensagemDTO(FORMA_DE_PAGAMENTO_JA_CADASTRADA);
            
        }
        return this.criaFormaPagamentoComSucesso(formaPagamentoDTO);
    }

    private MensagemDTO criaFormaPagamentoComSucesso(FormaPagamentoDTO formaPagamentoDTO) {
        
        FormaPagamento formaPagamento = new FormaPagamento();

        BeanUtils.copyProperties(formaPagamentoDTO, formaPagamento);
        formaPagamento.setTipoPagamento((Collections.emptyList());

        formaPagamentoRepository.save(formaPagamento);

        return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
    }
    }

}
