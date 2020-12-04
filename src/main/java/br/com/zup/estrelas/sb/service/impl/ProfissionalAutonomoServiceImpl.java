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
            throw new RegrasDeNegocioException("ESTE PROFISSIONAL JÁ ESTÁ CADASTRADO!");
        }

        return this.adicionaAutonomo(profissionalAutonomoDTO);
    }

    @Override
    public ProfissionalAutonomo alteraProfissionalAutonomo(Long idUsuario,
            ProfissionalAutonomoDTO profissionalAutonomoDTO) throws RegrasDeNegocioException {
        if (!profissionalAutonomoRepository.existsById(idUsuario)) {
            throw new RegrasDeNegocioException("O PROFISSIONAL NÃO FOI ENCONTRADO! VERIFIQUE AS INFORMAÇÕES INSERIDAS.");
        }

        ProfissionalAutonomo profissionalAutonomo =
                profissionalAutonomoRepository.findById(idUsuario).get();

        boolean verificaCpfAutonomo =
                profissionalAutonomoRepository.existsByCpf(profissionalAutonomoDTO.getCpf());

        if (!profissionalAutonomoDTO.getCpf().equals(profissionalAutonomo.getCpf())
                && verificaCpfAutonomo) {
            throw new RegrasDeNegocioException("CPF JÁ CADASTRADO NO BANCO DE DADOS!");
        }

        return this.alteraInformacoesAutonomo(profissionalAutonomo, profissionalAutonomoDTO);
    }

    @Override
    public ProfissionalAutonomo inativaProfissionalAutonomo(Long idUsuario,
            InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO) throws RegrasDeNegocioException {
        
        if (!profissionalAutonomoRepository.existsById(idUsuario)) {
            throw new RegrasDeNegocioException("O PROFISSIONAL NÃO FOI ENCONTRADO! VERIFIQUE AS INFORMAÇÕES INSERIDAS.");
        }

        return this.inativaAutonomo(idUsuario, inativaProfissionalAutonomoDTO);
    }

    @Override
    public ProfissionalAutonomo adicionaServicoProfissionalAutonomo(Long idUsuario,
            AdicionaServicoDTO adicionaServicoDTO) throws RegrasDeNegocioException {

        if (!profissionalAutonomoRepository.existsById(idUsuario)) {
            throw new RegrasDeNegocioException("O PROFISSIONAL NÃO FOI ENCONTRADO! VERIFIQUE AS INFORMAÇÕES INSERIDAS.");
        }

        if (!servicoRepository.existsById(adicionaServicoDTO.getIdServico())) {
            throw new RegrasDeNegocioException("SERVIÇO A SER ADICIONADO É INEXISTENTE!");
        }

        return this.adicionaServico(idUsuario, adicionaServicoDTO);
    }

    @Override
    public ProfissionalAutonomo adicionaFormaPagamento(Long idUsuario, FormaPagamentoDTO formaPagamentoDTO) throws RegrasDeNegocioException {

        if (!profissionalAutonomoRepository.existsById(idUsuario)) {
            throw new RegrasDeNegocioException("O PROFISSIONAL NÃO FOI ENCONTRADO! VERIFIQUE AS INFORMAÇÕES INSERIDAS.");
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
                    "O SERVICO ESTÁ MARCADO COMO INATIVO, ENTRE EM CONTATO COM SUPORTE!");
        }

        if (servicos.contains(servico)) {
            throw new RegrasDeNegocioException("O SERVIÇO JÁ EXISTENTE NO PERFIL DO PROFISSIONAL AUTÔNOMO!");
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
            throw new RegrasDeNegocioException("FORMA DE PAGAMENTO EXISTENTE NO CADASTRO!");
        }

        formasPagamento.add(formaPagamento);

        profissionalAutonomoRepository.save(autonomo);

        return autonomo;
    }

}
