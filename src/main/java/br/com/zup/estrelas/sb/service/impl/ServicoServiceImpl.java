package br.com.zup.estrelas.sb.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.InativaServicoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ServicoDTO;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.repository.ServicoRepository;
import br.com.zup.estrelas.sb.service.ServicoService;

@Service
public class ServicoServiceImpl implements ServicoService {

    private static final String SERVICO_INATIVADO_COM_SUCESSO = "Servico inativado com sucesso.";
    private static final String SERVICO_ALTERADO_COM_SUCESSO = "Serviço alterado com sucesso.";
    private static final String SERVICO_JA_CADASTRADO =
            "O cadastro não ocorreu, Serviço já está cadastrado";
    private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso.";
    private static final String SERVICO_INEXISTENTE = "Serviço inexistente.";

    @Autowired
    ServicoRepository servicoRepository;

    @Override
    public Servico buscaServico(Long idServico) {
        return servicoRepository.findById(idServico).orElse(null);
    }

    @Override
    public List<Servico> listaServicos() {
        return (List<Servico>) servicoRepository.findAll();
    }

    @Override
    public MensagemDTO adicionaServico(ServicoDTO servicoDTO) {

        if (servicoRepository.existsByNomeServico(servicoDTO.getNomeServico())) {
            return new MensagemDTO(SERVICO_JA_CADASTRADO);
        }

        return this.criaServico(servicoDTO);
    }

    @Override
    public MensagemDTO alteraServico(Long idServico, ServicoDTO servicoDTO) {

        if (!servicoRepository.existsById(idServico)) {
            return new MensagemDTO(SERVICO_INEXISTENTE);
        }

        return this.alteraInformacoesServico(idServico, servicoDTO);
    }

    @Override
    public MensagemDTO inativaServico(Long idServico, InativaServicoDTO inativaServicoDTO) {

        Optional<Servico> servicoConsultado = servicoRepository.findById(idServico);

        if (servicoConsultado.isEmpty()) {
            return new MensagemDTO(SERVICO_INEXISTENTE);
        }

        return inativaServicoComSucesso(servicoConsultado, inativaServicoDTO);
    }

    private MensagemDTO alteraInformacoesServico(Long idServico, ServicoDTO servicoDTO) {

        Servico servico = servicoRepository.findById(idServico).get();

        BeanUtils.copyProperties(servicoDTO, servico);

        servicoRepository.save(servico);

        return new MensagemDTO(SERVICO_ALTERADO_COM_SUCESSO);
    }

    private MensagemDTO criaServico(ServicoDTO servicoDTO) {

        Servico servico = new Servico();

        BeanUtils.copyProperties(servicoDTO, servico);
        servico.setAtivo(true);

        servicoRepository.save(servico);

        return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
    }

    private MensagemDTO inativaServicoComSucesso(Optional<Servico> servicoConsultado,
            InativaServicoDTO inativaServicoDTO) {

        Servico servico = servicoConsultado.get();

        servico.setAtivo(inativaServicoDTO.isAtivo());

        servicoRepository.save(servico);

        return new MensagemDTO(SERVICO_INATIVADO_COM_SUCESSO);
    }

}
