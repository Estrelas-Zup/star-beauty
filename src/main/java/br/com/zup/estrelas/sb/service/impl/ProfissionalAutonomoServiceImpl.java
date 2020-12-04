package br.com.zup.estrelas.sb.service.impl;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FormaPagamentoDTO;
import br.com.zup.estrelas.sb.dto.InativaProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.dto.ProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.FormaPagamentoRepository;
import br.com.zup.estrelas.sb.repository.ProfissionalAutonomoRepository;
import br.com.zup.estrelas.sb.repository.ServicoRepository;
import br.com.zup.estrelas.sb.service.ProfissionalAutonomoService;

@Service
public class ProfissionalAutonomoServiceImpl implements ProfissionalAutonomoService {

    private static final String FORMA_DE_PAGAMENTO_JA_EXISTE_NO_CADASTRO = "FORMA DE PAGAMENTO JÁ EXISTE NO CADASTRO!";

    private static final String SERVIÇO_JA_EXISTE_NO_PERFIL_DO_PROFISSIONAL = "O SERVIÇO JÁ EXISTE NO PERFIL DO PROFISSIONAL AUTÔNOMO!";

    private static final String SERVICO_JA_ESTA_COMO_INATIVO = "O SERVICO ESTÁ MARCADO COMO INATIVO, ENTRE EM CONTATO COM SUPORTE!";

    private static final String SERVICO_INEXISTENTE = "SERVIÇO A SER ADICIONADO É INEXISTENTE!";

    private static final String CPF_JA_CADASTRADO_NO_BANCO_DE_DADOS = "CPF JÁ CADASTRADO NO BANCO DE DADOS!";

    private static final String PROFISSIONAL_NAO_ENCONTRADO = "O PROFISSIONAL NÃO FOI ENCONTRADO! VERIFIQUE AS INFORMAÇÕES INSERIDAS.";

    private static final String PROFISSIONAL_JA_EXISTE_NO_BANCO_DE_DADOS = "ESTE PROFISSIONAL JÁ EXISTE NO BANCO DE DADOS!";

    @Autowired
    ProfissionalAutonomoRepository profissionalAutonomoRepository;

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    FormaPagamentoRepository pagamentoRepository;

    @Override

    public ProfissionalAutonomo buscaProfissionalAutonomo(Long idUsuario) throws RegrasDeNegocioException {
        return profissionalAutonomoRepository.findById(idUsuario).orElseThrow(() -> new RegrasDeNegocioException(
                "NÃO FOI POSSÍVEL ENCONTRAR O PROFISSIONAL PELO ID: " + idUsuario));
    }

    @Override
    public List<ProfissionalAutonomo> listaProfissionaisAutonomos() {
        return (List<ProfissionalAutonomo>) profissionalAutonomoRepository.findAll();
    }

    @Override
    public ProfissionalAutonomo adicionaProfissionalAutonomo (
            ProfissionalAutonomoDTO profissionalAutonomoDTO) throws RegrasDeNegocioException {
        if (profissionalAutonomoRepository.existsByCpf(profissionalAutonomoDTO.getCpf())) {
            throw new RegrasDeNegocioException(PROFISSIONAL_JA_EXISTE_NO_BANCO_DE_DADOS);
        }

        return this.adicionaAutonomo(profissionalAutonomoDTO);
    }

    @Override
    public ProfissionalAutonomo alteraProfissionalAutonomo(Long idUsuario,
            ProfissionalAutonomoDTO profissionalAutonomoDTO) throws RegrasDeNegocioException {
        if (!profissionalAutonomoRepository.existsById(idUsuario)) {
            throw new RegrasDeNegocioException(PROFISSIONAL_NAO_ENCONTRADO);
        }

        ProfissionalAutonomo profissionalAutonomo =
                profissionalAutonomoRepository.findById(idUsuario).get();

        boolean verificaCpfAutonomo =
                profissionalAutonomoRepository.existsByCpf(profissionalAutonomoDTO.getCpf());

        if (!profissionalAutonomoDTO.getCpf().equals(profissionalAutonomo.getCpf())
                && verificaCpfAutonomo) {
            throw new RegrasDeNegocioException(CPF_JA_CADASTRADO_NO_BANCO_DE_DADOS);
        }

        return this.alteraInformacoesAutonomo(profissionalAutonomo, profissionalAutonomoDTO);
    }

    @Override
    public ProfissionalAutonomo inativaProfissionalAutonomo(Long idUsuario,
            InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO) throws RegrasDeNegocioException {
        
        if (!profissionalAutonomoRepository.existsById(idUsuario)) {
            throw new RegrasDeNegocioException(PROFISSIONAL_NAO_ENCONTRADO);
        }

        return this.inativaAutonomo(idUsuario, inativaProfissionalAutonomoDTO);
    }

    @Override
    public ProfissionalAutonomo adicionaServicoProfissionalAutonomo(Long idUsuario,
            AdicionaServicoDTO adicionaServicoDTO) throws RegrasDeNegocioException {

        if (!profissionalAutonomoRepository.existsById(idUsuario)) {
            throw new RegrasDeNegocioException(PROFISSIONAL_NAO_ENCONTRADO);
        }

        if (!servicoRepository.existsById(adicionaServicoDTO.getIdServico())) {
            throw new RegrasDeNegocioException(SERVICO_INEXISTENTE);
        }

        return this.adicionaServico(idUsuario, adicionaServicoDTO);
    }

    @Override
    public ProfissionalAutonomo adicionaFormaPagamento(Long idUsuario, FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException {

        if (!profissionalAutonomoRepository.existsById(idUsuario)) {
            throw new RegrasDeNegocioException(PROFISSIONAL_NAO_ENCONTRADO);
        }

        return this.adicionaFormaPagamentoComSucesso(idUsuario, formaPagamentoDTO);
    }

    private ProfissionalAutonomo adicionaAutonomo(ProfissionalAutonomoDTO profissionalAutonomoDTO) {

        ProfissionalAutonomo profissionalAutonomo = new ProfissionalAutonomo();

        BeanUtils.copyProperties(profissionalAutonomoDTO, profissionalAutonomo);
        profissionalAutonomo.setAgendamentos(Collections.emptyList());
        profissionalAutonomo.setFormasPagamento(Collections.emptyList());
        profissionalAutonomo.setServicos(Collections.emptyList());
        profissionalAutonomo.setAtivo(true);

        profissionalAutonomoRepository.save(profissionalAutonomo);

        return profissionalAutonomo;
    }

    private ProfissionalAutonomo alteraInformacoesAutonomo(ProfissionalAutonomo profissionalAutonomo,
            ProfissionalAutonomoDTO profissionalAutonomoDTO) {

        BeanUtils.copyProperties(profissionalAutonomoDTO, profissionalAutonomo);

        profissionalAutonomoRepository.save(profissionalAutonomo);

        return profissionalAutonomo;
    }

    private ProfissionalAutonomo inativaAutonomo(Long idUsuario,
            InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO) {

        ProfissionalAutonomo profissionalAutonomo =
                profissionalAutonomoRepository.findById(idUsuario).get();

        BeanUtils.copyProperties(inativaProfissionalAutonomoDTO, profissionalAutonomo);

        profissionalAutonomoRepository.save(profissionalAutonomo);

        return profissionalAutonomo;
    }

    private ProfissionalAutonomo adicionaServico(Long idUsuario, AdicionaServicoDTO adicionaServicoDTO) throws RegrasDeNegocioException {

        ProfissionalAutonomo autonomo = profissionalAutonomoRepository.findById(idUsuario).get();

        Servico servico = servicoRepository.findById(adicionaServicoDTO.getIdServico()).get();

        List<Servico> servicos = autonomo.getServicos();

        if (!servico.isAtivo()) {
            throw new RegrasDeNegocioException(
                    SERVICO_JA_ESTA_COMO_INATIVO);
        }

        if (servicos.contains(servico)) {
            throw new RegrasDeNegocioException(SERVIÇO_JA_EXISTE_NO_PERFIL_DO_PROFISSIONAL);
        }

        servicos.add(servico);

        autonomo.setServicos(servicos);

        profissionalAutonomoRepository.save(autonomo);

        return autonomo;
    }

    private ProfissionalAutonomo adicionaFormaPagamentoComSucesso(Long idUsuario,
            FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException {

        ProfissionalAutonomo autonomo = profissionalAutonomoRepository.findById(idUsuario).get();
        FormaPagamento formaPagamento =
                pagamentoRepository.findByTipoPagamento(formaPagamentoDTO.getTipoPagamento());
        List<FormaPagamento> formasPagamento = autonomo.getFormasPagamento();

        if (formasPagamento.contains(formaPagamento)) {
            throw new RegrasDeNegocioException(FORMA_DE_PAGAMENTO_JA_EXISTE_NO_CADASTRO);
        }

        formasPagamento.add(formaPagamento);

        profissionalAutonomoRepository.save(autonomo);

        return autonomo;
    }

}
