package br.com.zup.estrelas.sb.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ServicoDTO;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.ServicoRepository;
import br.com.zup.estrelas.sb.service.ServicoService;

@Service
public class ServicoServiceImpl implements ServicoService {

    private static final String SERVIÇO_EXCLUÍDO_COM_SUCESSO = "SERVIÇO EXCLUÍDO COM SUCESSO!";
    private static final String SERVICO_NÃO_ENCONTRADO_PELO_ID = "SERVIÇO NÃO ENCONTRADO PELO ID: ";
    private static final String SERVICO_JA_CADASTRADO =
            "O CADASTRO NÃO OCORREU, POIS O SERVIÇO JÁ ESTÁ CADASTRADO!";
    private static final String SERVICO_INEXISTENTE = "SERVIÇO INEXISTENTE!";

    @Autowired
    ServicoRepository servicoRepository;

    @Override
    public Servico buscaServico(Long idServico) throws RegrasDeNegocioException {
        return servicoRepository.findById(idServico).orElseThrow(
                () -> new RegrasDeNegocioException(SERVICO_NÃO_ENCONTRADO_PELO_ID + idServico));
    }

    @Override
    public List<Servico> listaServicos() {
        return (List<Servico>) servicoRepository.findAll();
    }

    @Override
    public Servico insereServico(ServicoDTO servicoDTO) throws RegrasDeNegocioException {

        if (servicoRepository.existsByNomeServico(servicoDTO.getNomeServico())) {
            throw new RegrasDeNegocioException(SERVICO_JA_CADASTRADO);
        }

        return this.criaServico(servicoDTO);
    }

    @Override
    public Servico alteraServico(Long idServico, ServicoDTO servicoDTO)
            throws RegrasDeNegocioException {

        if (!servicoRepository.existsById(idServico)) {
            throw new RegrasDeNegocioException(SERVICO_INEXISTENTE);
        }

        return this.alteraInformacoesServico(idServico, servicoDTO);
    }

    @Override
    public MensagemDTO removeServico(Long idServico) throws RegrasDeNegocioException {

        if (!servicoRepository.existsById(idServico)) {
            throw new RegrasDeNegocioException(SERVICO_INEXISTENTE);
        }

        return inativaServicoComSucesso(idServico);
    }

    private Servico alteraInformacoesServico(Long idServico, ServicoDTO servicoDTO) {

        Servico servico = servicoRepository.findById(idServico).get();

        BeanUtils.copyProperties(servicoDTO, servico);

        servicoRepository.save(servico);

        return servico;
    }

    private Servico criaServico(ServicoDTO servicoDTO) {

        Servico servico = new Servico();

        BeanUtils.copyProperties(servicoDTO, servico);

        servicoRepository.save(servico);

        return servico;
    }

    private MensagemDTO inativaServicoComSucesso(Long idServico) {

        servicoRepository.deleteById(idServico);

        return new MensagemDTO(SERVIÇO_EXCLUÍDO_COM_SUCESSO);
    }

}
