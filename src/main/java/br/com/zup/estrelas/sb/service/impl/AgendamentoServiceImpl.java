package br.com.zup.estrelas.sb.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.AgendamentoDTO;
import br.com.zup.estrelas.sb.dto.FinalizaAgendamentoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.TransacaoDTO;
import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.entity.Cliente;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.AgendamentoRepository;
import br.com.zup.estrelas.sb.repository.ClienteRepository;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;
import br.com.zup.estrelas.sb.repository.ProfissionalAutonomoRepository;
import br.com.zup.estrelas.sb.repository.ServicoRepository;
import br.com.zup.estrelas.sb.service.AgendamentoService;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private static final Long HORAS_MINIMAS_PARA_REAGENDAMENTO = 24L;

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProfissionalAutonomoRepository autonomoRepository;

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    TransacaoServiceImpl transacaoServiceImpl;

    @Override
    public Agendamento buscaAgendamento(Long idAgendamento) throws RegrasDeNegocioException {
        return agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new RegrasDeNegocioException(
                        "Agendamento não encontrado pelo Id!: " + idAgendamento));
    }

    @Override
    public List<Agendamento> listaAgendamento() {
        return (List<Agendamento>) agendamentoRepository.findAll();
    }

    @Override
    public MensagemDTO criaAgendamento(AgendamentoDTO agendamentoDTO)
            throws RegrasDeNegocioException {

        if (agendamentoDTO.getDataHora().isBefore(LocalDateTime.now())) {
            throw new RegrasDeNegocioException(
                    "NÃO É POSSÍVEL CRIAR UM AGENDAMENTO PARA UM DIA PASSADO!");
        }

        boolean verificaDisponibilidadeFuncionario =
                agendamentoRepository.existsByFuncionarioIdFuncionarioAndDataHora(
                        agendamentoDTO.getIdFuncionario(), agendamentoDTO.getDataHora());

        boolean verificaDisponibilidadeAutonomo =
                agendamentoRepository.existsByAutonomoIdUsuarioAndDataHora(
                        agendamentoDTO.getIdProfissionalAutonomo(), agendamentoDTO.getDataHora());

        if (verificaDisponibilidadeFuncionario || verificaDisponibilidadeAutonomo) {
            throw new RegrasDeNegocioException("HORÁRIO DE AGENDAMENTO INDISPONÍVEL!");
        }

        if (agendamentoDTO.getDataHora().isAfter(agendamentoDTO.getDataHoraFim())) {
            throw new RegrasDeNegocioException(
                    "A DATA/HORA PREVISTA PARA O TERMINO DO SERVIÇO DEVE SER POSTERIOR A DATA/HORA INICÍO!");
        }

        // verificar a disponibilidade na agenda para o tempo de execução do servico, se não
        // inflinge outro agendamento!

        return this.criaAgendamdentoComSucesso(agendamentoDTO);
    }

    @Override
    public MensagemDTO alteraAgendamento(Long idAgendamento, AgendamentoDTO agendamentoDTO)
            throws RegrasDeNegocioException {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            throw new RegrasDeNegocioException("AGENDAMENTO NÃO ENCONTRADO PARA ALTERAR!");
        }

        if (agendamentoDTO.getDataHora().isBefore(LocalDateTime.now())) {
            throw new RegrasDeNegocioException(
                    "NÃO É POSSÍVEL ALTERAR A DATA PARA UM DIA PASSADO!");
        }

        Agendamento agendamento = agendamentoRepository.findById(idAgendamento).get();

        Long diferencaHoras =
                ChronoUnit.HOURS.between(LocalDateTime.now(), agendamento.getDataHora());

        if (diferencaHoras < HORAS_MINIMAS_PARA_REAGENDAMENTO) {
            throw new RegrasDeNegocioException(
                    "NÃO É POSSÍVEL ALTERAR A DATA COM MENOS DE 24 HORAS DO AGENDAMENTO!");
        }

        return this.alteraInformacoesAgendamento(agendamento, agendamentoDTO);
    }

    @Override
    public MensagemDTO finalizaAgendamento(Long idAgendamento,
            FinalizaAgendamentoDTO finalizaAgendamentoDTO) throws RegrasDeNegocioException {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            throw new RegrasDeNegocioException("AGENDAMENTO NÃO ENCONTRADO PARA FINALIZAR!");
        }

        return this.finalizaAgendamentoComSucesso(idAgendamento, finalizaAgendamentoDTO);

    }

    @Override
    public MensagemDTO deletaAgendamento(Long idAgendamento) throws RegrasDeNegocioException {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            throw new RegrasDeNegocioException("AGENDAMENTO NÃO ENCONTRADO PARA CANCELAR!");
        }

        agendamentoRepository.deleteById(idAgendamento);

        return new MensagemDTO("AGENDAMENTO CANCELADO E EXCLUÍDO COM SUCESSO!");
    }

    private MensagemDTO criaAgendamdentoComSucesso(AgendamentoDTO agendamentoDTO)
            throws RegrasDeNegocioException {

        Agendamento agendamento = new Agendamento();
        Cliente cliente = clienteRepository.findById(agendamentoDTO.getIdCliente()).get();
        Servico servico = servicoRepository.findById(agendamentoDTO.getIdServico()).get();

        Optional<Funcionario> funcionario;
        Optional<ProfissionalAutonomo> autonomo;

        BeanUtils.copyProperties(agendamentoDTO, agendamento);
        agendamento.setCliente(cliente);
        agendamento.setServico(servico);

        if (agendamentoDTO.getIdFuncionario() != null
                && agendamentoDTO.getIdProfissionalAutonomo() != null) {
            throw new RegrasDeNegocioException(
                    "O AGENDAMENTO DEVE FAZER REFERENCIA A APENAS UM PROFISSIONAL!");

        } else if (agendamentoDTO.getIdFuncionario() != null) {

            funcionario = funcionarioRepository.findById(agendamentoDTO.getIdFuncionario());

            agendamento.setFuncionario(funcionario.get());

        } else if (agendamentoDTO.getIdProfissionalAutonomo() != null) {

            autonomo = autonomoRepository.findById(agendamentoDTO.getIdProfissionalAutonomo());

            agendamento.setAutonomo(autonomo.get());

        } else {
            throw new RegrasDeNegocioException(
                    "NÃO FOI POSSÍVEL CONCLUIR O AGENDAMENTO POIS FALTA A REFERENCIA AO PRESTADOR DE SERVIÇO!");
        }

        agendamentoRepository.save(agendamento);

        return new MensagemDTO("AGENDAMENTO REALIZADO COM SUCESSO!");
    }

    private MensagemDTO alteraInformacoesAgendamento(Agendamento agendamento,
            AgendamentoDTO agendamentoDTO) {

        Cliente cliente = clienteRepository.findById(agendamentoDTO.getIdCliente()).get();
        Funcionario funcionario =
                funcionarioRepository.findById(agendamentoDTO.getIdFuncionario()).get();
        ProfissionalAutonomo autonomo =
                autonomoRepository.findById(agendamentoDTO.getIdProfissionalAutonomo()).get();
        Servico servico = servicoRepository.findById(agendamentoDTO.getIdServico()).get();

        BeanUtils.copyProperties(agendamentoDTO, agendamento);
        agendamento.setCliente(cliente);
        agendamento.setFuncionario(funcionario);
        agendamento.setAutonomo(autonomo);
        agendamento.setServico(servico);

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

        TransacaoDTO transacaoDTO = new TransacaoDTO();

        transacaoDTO.setIdAgendamento(idAgendamento);
        transacaoDTO.setNomeCliente(agendamento.getNomeCliente());
        transacaoDTO.setValor(agendamento.getServico().getValorServico());
        transacaoDTO.setTipoPagamento(agendamento.getTipoPagamento());

        Long idAutonomo = null;
        Long idFuncionario = null;

        if (agendamento.getAutonomo() != null) {
            idAutonomo = agendamento.getAutonomo().getIdUsuario();
        } else if (agendamento.getFuncionario() != null) {
            idFuncionario = agendamento.getFuncionario().getIdFuncionario();
        }

        Optional<ProfissionalAutonomo> autonomo;
        Optional<Funcionario> funcionario;

        if (idFuncionario != null) {

            funcionario = funcionarioRepository.findById(idFuncionario);

            transacaoDTO.setNomeSalao(funcionario.get().getSalao().getNomeFantasia());

        } else if (idAutonomo != null) {

            autonomo = autonomoRepository.findById(idAutonomo);

            transacaoDTO.setNomeAutonomo(autonomo.get().getNome());
        }

        transacaoServiceImpl.criaTransacao(transacaoDTO);
    }

}
