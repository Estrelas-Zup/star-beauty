package br.com.zup.estrelas.sb.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.AgendamentoDTO;
import br.com.zup.estrelas.sb.dto.CancelaAgendamentoDTO;
import br.com.zup.estrelas.sb.dto.FinalizaAgendamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.TransacaoDTO;
import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.repository.AgendamentoRepository;
import br.com.zup.estrelas.sb.service.AgendamentoService;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private static final Long HORAS_MINIMAS_PARA_REAGENDAMENTO = 24L;

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Override
    public Agendamento buscaAgendamento(Long idAgendamento) {
        return agendamentoRepository.findById(idAgendamento).orElse(null);
    }

    @Override
    public List<Agendamento> listaAgendamento() {
        return (List<Agendamento>) agendamentoRepository.findAll();
    }

    @Override
    public MensagemDTO criaAgendamento(AgendamentoDTO agendamentoDTO) {

        if (agendamentoRepository.existsByFuncionarioIdFuncionarioAndDataHora(
                agendamentoDTO.getIdFuncionario(), agendamentoDTO.getDataHora())) {
            return new MensagemDTO("HORÁRIO DE AGENDAMENTO INDISPONÍVEL!");
        }

        // verificar a disponibilidade na agenda para o tempo de execução do servico, se o tempo de
        // exucução não inflinge outro agendamento!

        return this.criaAgendamentoComSucesso(agendamentoDTO);
    }

    @Override
    public MensagemDTO alteraAgendamento(Long idAgendamento, AgendamentoDTO agendamentoDTO) {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            return new MensagemDTO("AGENDAMENTO NÃO ENCONTRADO PARA ALTERAÇÃO!");
        }

        if (agendamentoDTO.getDataHora().isBefore(LocalDateTime.now())) {
            return new MensagemDTO("NÃO É POSSÍVEL ALTERAR A DATA PARA UM DIA PASSADO!");
        }

        if (agendamentoRepository.existsByFuncionarioIdFuncionarioAndDataHora(
                agendamentoDTO.getIdFuncionario(), agendamentoDTO.getDataHora())) {
            return new MensagemDTO("HORÁRIO DE AGENDAMENTO INDISPONÍVEL!");
        }

        Agendamento agendamento = agendamentoRepository.findById(idAgendamento).get();

        Long diferencaHoras =
                ChronoUnit.HOURS.between(agendamento.getDataHora(), agendamentoDTO.getDataHora());

        if (diferencaHoras < HORAS_MINIMAS_PARA_REAGENDAMENTO) {
            return new MensagemDTO(
                    "NÃO É POSSÍVEL ALTERAR A DATA COM MENOS DE 24 HORAS DO AGENDAMENTO!");
        }

        return this.alteraInformacoesAgendamento(agendamento, agendamentoDTO);
    }

    @Override
    public MensagemDTO finalizaAgendamento(Long idAgendamento,
            FinalizaAgendamentoDTO finalizaAgendamentoDTO) {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            return new MensagemDTO("AGENDAMENTO NÃO ENCONTRADO!");
        }

        return this.finalizaAgendamentoComSucesso(idAgendamento, finalizaAgendamentoDTO);
    }

    @Override
    public MensagemDTO cancelaAgendamento(Long idAgendamento,
            CancelaAgendamentoDTO cancelaAgendamentoDTO) {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            return new MensagemDTO("AGENDAMENTO NÃO ENCONTRADO!");
        }

        return this.cancelaAgendamentoComSucesso(idAgendamento, cancelaAgendamentoDTO);
    }

    private MensagemDTO alteraInformacoesAgendamento(Agendamento agendamento,
            AgendamentoDTO agendamentoDTO) {

        BeanUtils.copyProperties(agendamentoDTO, agendamento);

        agendamentoRepository.save(agendamento);

        return new MensagemDTO("AGENDAMENTO ALTERADO COM SEUCCESO!");
    }

    private MensagemDTO criaAgendamentoComSucesso(AgendamentoDTO agendamentoDTO) {

        Agendamento agendamento = new Agendamento();

        BeanUtils.copyProperties(agendamentoDTO, agendamento);

        agendamentoRepository.save(agendamento);

        return new MensagemDTO("AGENDAMENTO REALIZADO COM SUCESSO!");
    }

    private MensagemDTO finalizaAgendamentoComSucesso(Long idAgendamento,
            FinalizaAgendamentoDTO finalizaAgendamentoDTO) {

        Agendamento agendamento = agendamentoRepository.findById(idAgendamento).get();

        BeanUtils.copyProperties(finalizaAgendamentoDTO, agendamento);

        this.criaTransacao(agendamento);

        agendamentoRepository.save(agendamento);

        return new MensagemDTO("AGENDAMENTO FINALIZADO COM SUCESSO!");
    }

    private void criaTransacao(Agendamento agendamento) {

        TransacaoDTO transacaoDTO = new TransacaoDTO();

        transacaoDTO.setAgendamento(agendamento);
        transacaoDTO.setFormaPagmento(agendamento.getFormaPagamento());
        transacaoDTO.setNomeCliente(agendamento.getNomeCliente());

        String nomeSalao = agendamento.getFuncionario().getSalao().getNomeFantasia();

        transacaoDTO.setNomeSalao(nomeSalao);
        transacaoDTO.setValor(agendamento.getServico().getValorServico());

        TransacaoServiceImpl transacaoServiceImpl = new TransacaoServiceImpl();;

        transacaoServiceImpl.criaTransacao(transacaoDTO);
    }

    private MensagemDTO cancelaAgendamentoComSucesso(Long idAgendamento,
            CancelaAgendamentoDTO cancelaAgendamentoDTO) {

        Agendamento agendamento = agendamentoRepository.findById(idAgendamento).get();

        BeanUtils.copyProperties(cancelaAgendamentoDTO, agendamento);

        agendamentoRepository.save(agendamento);

        return new MensagemDTO("O AGENDAMENTO FOI CANCELADO COM SUCESSO!");
    }


}
