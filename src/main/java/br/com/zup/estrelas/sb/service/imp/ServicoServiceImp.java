package br.com.zup.estrelas.sb.service.imp;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ServicoDTO;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.repository.ServicoRepository;
import br.com.zup.estrelas.sb.service.ServicoService;

@Service
public class ServicoServiceImp implements ServicoService {

    private static final String SERVICO_ALTERADO_COM_SUCESSO = "Serviço alterado com sucesso.";
    private static final String SERVICO_REMOVIDO_COM_SUCESSO = "Serviço removido com sucesso!";
    private static final String SERVICO_JA_CADASTRADO = "O cadastro não ocorreu, Serviço já está cadastrado";
    private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso.";
    private static final String SERVICO_INEXISTENTE = "Serviço inexistente.";

    @Autowired
    ServicoRepository servicoRepository;

    @Override
    public MensagemDTO adicionaServico(ServicoDTO servicoDTO) {

        Servico servico = new Servico();

        BeanUtils.copyProperties(servicoDTO, servico);

        // como fazer a verificação da existencia do serviço??
        if (servicoRepository.existsById(servico.getIdServico())) {
            return new MensagemDTO(SERVICO_JA_CADASTRADO);
        }
        servicoRepository.save(servico);
        
        return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
    }

    public Servico buscaServico(Long idServico) {
        if (servicoRepository.existsById(idServico)) {
            return servicoRepository.findById(idServico).get();
        }
        return null;
    }

    public List<Servico> listaServicos() {

        return (List<Servico>) servicoRepository.findAll();
    }

    public MensagemDTO removeServico(Long idServico) {
        if (servicoRepository.existsById(idServico)) {
            return new MensagemDTO(SERVICO_REMOVIDO_COM_SUCESSO);
        }
        return new MensagemDTO(SERVICO_INEXISTENTE);
    }

    public MensagemDTO alteraServico(Long idServico, ServicoDTO servicoDTO) {
        Servico servico = new Servico();

        BeanUtils.copyProperties(servicoDTO, servico);

        if (servicoRepository.existsById(servico.getIdServico())) {
            servicoRepository.save(servico);

            return new MensagemDTO(SERVICO_ALTERADO_COM_SUCESSO);
        }

        return new MensagemDTO(SERVICO_INEXISTENTE);
    }



}
