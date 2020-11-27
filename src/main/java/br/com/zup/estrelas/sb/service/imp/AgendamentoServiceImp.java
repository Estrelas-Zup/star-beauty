package br.com.zup.estrelas.sb.service.imp;

import java.time.LocalDate;
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
import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.repository.AgendamentoRepository;
import br.com.zup.estrelas.sb.service.AgendamentoService;

@Service
public class AgendamentoServiceImp implements AgendamentoService {

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

        // Como capturar o idFuncionario sem o cliente preenche-lo?

        if (agendamentoRepository.existsByFuncionarioIdFuncionarioAndHoraAgendamento(
                agendamentoDTO.getIdFuncionario(), agendamentoDTO.getHora())) {
            return new MensagemDTO("HORÁRIO DE AGENDAMENTO INDISPONÍVEL!");
        }

        // verificar a disponibilidade na agenda para o tempo de execução do servico, se não
        // inflinge outro agendamento!

        Agendamento agendamento = new Agendamento();

        BeanUtils.copyProperties(agendamentoDTO, agendamento);

        agendamentoRepository.save(agendamento);

        return new MensagemDTO("AGENDAMENTO REALIZADO COM SUCESSO!");
    }

    @Override
    public MensagemDTO alteraAgendamento(Long idAgendamento, AgendamentoDTO agendamentoDTO) {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            return new MensagemDTO("AGENDAMENTO NÃO ENCONTRADO PARA ALTERAR!");
        }

        if (agendamentoDTO.getData().isBefore(LocalDate.now())) {
            return new MensagemDTO("NÃO É POSSÍVEL ALTERAR A DATA PARA UM DIA PASSADO!");
        }

        Agendamento agendamento = agendamentoRepository.findById(idAgendamento).get();

        LocalDateTime dataAgendadaSemAlteracao =
                LocalDateTime.of(agendamento.getData(), agendamento.getHora());

        LocalDateTime dataAgendadaComAlteracao =
                LocalDateTime.of(agendamentoDTO.getData(), agendamentoDTO.getHora());

        Long diferencaHoras =
                ChronoUnit.HOURS.between(dataAgendadaSemAlteracao, dataAgendadaComAlteracao);

        if (diferencaHoras < HORAS_MINIMAS_PARA_REAGENDAMENTO) {
            return new MensagemDTO(
                    "NÃO É PISSÍVEL ALTERAR A DATA COM MENOS DE 24 HORAS DO AGENDAMENTO!");
        }

        return null;
    }

    @Override
    public MensagemDTO finalizaAgendamento(Long idAgendamento,
            FinalizaAgendamentoDTO finalizaAgendamentoDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MensagemDTO cancelaAgendamento(Long idAgendamento,
            CancelaAgendamentoDTO cancelaAgendamentoDTO) {
        // TODO Auto-generated method stub
        return null;
    }

}
