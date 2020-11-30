package br.com.zup.estrelas.sb.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.AgendamentoDTO;
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

        // verificar a disponibilidade na agenda para o tempo de execução do servico, se não
        // inflinge outro agendamento!

        return this.criaAgendamdentoComSucesso(agendamentoDTO);
    }

    @Override
    public MensagemDTO alteraAgendamento(Long idAgendamento, AgendamentoDTO agendamentoDTO) {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            return new MensagemDTO("AGENDAMENTO NÃO ENCONTRADO PARA ALTERAR!");
        }

        if (agendamentoDTO.getDataHora().isBefore(LocalDateTime.now())) {
            return new MensagemDTO("NÃO É POSSÍVEL ALTERAR A DATA PARA UM DIA PASSADO!");
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
            return new MensagemDTO("AGENDAMENTO NÃO ENCONTRADO PARA FINALIZAR!");
        }

        return this.finalizaAgendamentoComSucesso(idAgendamento, finalizaAgendamentoDTO);
    }

    @Override
    public MensagemDTO cancelaAgendamento(Long idAgendamento) {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            return new MensagemDTO("AGENDAMENTO NÃO ENCONTRADO PARA CANCELAR!");
        }

        agendamentoRepository.deleteById(idAgendamento);

        return new MensagemDTO("AGENDAMENTO CANCELADO E EXCLUÍDO COM SUCESSO!");
    }

    private MensagemDTO criaAgendamdentoComSucesso(AgendamentoDTO agendamentoDTO) {

        Agendamento agendamento = new Agendamento();

        BeanUtils.copyProperties(agendamentoDTO, agendamento);

        agendamentoRepository.save(agendamento);

        return new MensagemDTO("AGENDAMENTO REALIZADO COM SUCESSO!");
    }

    private MensagemDTO alteraInformacoesAgendamento(Agendamento agendamento,
            AgendamentoDTO agendamentoDTO) {

        BeanUtils.copyProperties(agendamentoDTO, agendamento);

        agendamentoRepository.save(agendamento);

        return new MensagemDTO("AGENDAMENTO ALTERADO COM SUCESSO!");
    }

    private MensagemDTO finalizaAgendamentoComSucesso(Long idAgendamento,
            FinalizaAgendamentoDTO finalizaAgendamentoDTO) {

        Agendamento agendamento = agendamentoRepository.findById(idAgendamento).get();

        agendamento.setRealizado(finalizaAgendamentoDTO.isRealizado());

        this.criaTransacao(idAgendamento, agendamento);

        agendamentoRepository.save(agendamento);

        return new MensagemDTO("AGENDAMENTO FINALIZADO COM SUCESSO!");
    }

    private void criaTransacao(Long idAgendamento, Agendamento agendamento) {

        TransacaoServiceImpl transacaoServiceImpl = new TransacaoServiceImpl();

        TransacaoDTO transacaoDTO = new TransacaoDTO();

        transacaoDTO.setIdAgendamento(idAgendamento);
        transacaoDTO.setNomeCliente(agendamento.getNomeCliente());
        transacaoDTO.setNomeSalao(agendamento.getFuncionario().getSalao().getNomeFantasia());
        transacaoDTO.setValor(agendamento.getServico().getValorServico());
        transacaoDTO.setFormaPagmento(agendamento.getFormaPagamento());

        transacaoServiceImpl.criaTransacao(transacaoDTO);
    }
}
