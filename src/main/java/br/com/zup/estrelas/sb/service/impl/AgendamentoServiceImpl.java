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

    private static final String NÃO_FOI_POSSÍVEL_CONCLUIR_O_AGENDAMENTO_POIS_FALTA_A_REFERENCIA_AO_PRESTADOR_DE_SERVIÇO =
            "NÃO FOI POSSÍVEL CONCLUIR O AGENDAMENTO POIS FALTA A REFERENCIA AO PRESTADOR DE SERVIÇO!";

    private static final String O_AGENDAMENTO_DEVE_FAZER_REFERENCIA_A_APENAS_UM_PROFISSIONAL =
            "O AGENDAMENTO DEVE FAZER REFERENCIA A APENAS UM PROFISSIONAL!";

    private static final String AGENDAMENTO_CANCELADO_E_EXCLUÍDO_COM_SUCESSO =
            "AGENDAMENTO CANCELADO E EXCLUÍDO COM SUCESSO!";

    private static final String AGENDAMENTO_NÃO_ENCONTRADO_PARA_CANCELAR =
            "AGENDAMENTO NÃO ENCONTRADO PARA CANCELAR!";

    private static final String AGENDAMENTO_NÃO_ENCONTRADO_PARA_FINALIZAR =
            "AGENDAMENTO NÃO ENCONTRADO PARA FINALIZAR!";

    private static final String NÃO_É_POSSÍVEL_ALTERAR_A_DATA_COM_MENOS_DE_24_HORAS_DO_AGENDAMENTO =
            "NÃO É POSSÍVEL ALTERAR A DATA COM MENOS DE 24 HORAS DO AGENDAMENTO!";

    private static final String NÃO_É_POSSÍVEL_ALTERAR_A_DATA_PARA_UM_DIA_PASSADO =
            "NÃO É POSSÍVEL ALTERAR A DATA PARA UM DIA PASSADO!";

    private static final String AGENDAMENTO_NÃO_ENCONTRADO_PARA_ALTERAR =
            "AGENDAMENTO NÃO ENCONTRADO PARA ALTERAR!";

    private static final String A_DATA_HORA_TERMINO_POSTERIOR_A_DATA_HORA_INICÍO =
            "A DATA/HORA PREVISTA PARA O TERMINO DO SERVIÇO DEVE SER POSTERIOR A DATA/HORA INICÍO!";

    private static final String HORÁRIO_DE_AGENDAMENTO_INDISPONÍVEL =
            "HORÁRIO DE AGENDAMENTO INDISPONÍVEL!";

    private static final String NÃO_É_POSSÍVEL_CRIAR_UM_AGENDAMENTO_PARA_UM_DIA_PASSADO =
            "NÃO É POSSÍVEL CRIAR UM AGENDAMENTO PARA UM DIA PASSADO!";

    private static final String AGENDAMENTO_NÃO_ENCONTRADO_PELO_ID =
            "AGENDAMENTO NÃO ENCONTRADO PELO ID: ";


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
                        AGENDAMENTO_NÃO_ENCONTRADO_PELO_ID + idAgendamento));
    }

    @Override
    public List<Agendamento> listaAgendamento() {
        return (List<Agendamento>) agendamentoRepository.findAll();
    }

    @Override
    public Agendamento criaAgendamento(AgendamentoDTO agendamentoDTO)
            throws RegrasDeNegocioException {

        if (agendamentoDTO.getDataHora().isBefore(LocalDateTime.now())) {
            throw new RegrasDeNegocioException(
                    NÃO_É_POSSÍVEL_CRIAR_UM_AGENDAMENTO_PARA_UM_DIA_PASSADO);
        }

        boolean verificaDisponibilidadeFuncionario =
                agendamentoRepository.existsByFuncionarioIdFuncionarioAndDataHora(
                        agendamentoDTO.getIdFuncionario(), agendamentoDTO.getDataHora());

        boolean verificaDisponibilidadeAutonomo =
                agendamentoRepository.existsByAutonomoIdUsuarioAndDataHora(
                        agendamentoDTO.getIdProfissionalAutonomo(), agendamentoDTO.getDataHora());

        if (verificaDisponibilidadeFuncionario || verificaDisponibilidadeAutonomo) {
            throw new RegrasDeNegocioException(HORÁRIO_DE_AGENDAMENTO_INDISPONÍVEL);
        }

        if (agendamentoDTO.getDataHora().isAfter(agendamentoDTO.getDataHoraFim())) {
            throw new RegrasDeNegocioException(A_DATA_HORA_TERMINO_POSTERIOR_A_DATA_HORA_INICÍO);
        }

        // verificar a disponibilidade na agenda para o tempo de execução do servico, se não
        // inflinge outro agendamento!

        return this.criaAgendamdentoComSucesso(agendamentoDTO);
    }

    @Override
    public Agendamento alteraAgendamento(Long idAgendamento, AgendamentoDTO agendamentoDTO)
            throws RegrasDeNegocioException {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            throw new RegrasDeNegocioException(AGENDAMENTO_NÃO_ENCONTRADO_PARA_ALTERAR);
        }

        if (agendamentoDTO.getDataHora().isBefore(LocalDateTime.now())) {
            throw new RegrasDeNegocioException(NÃO_É_POSSÍVEL_ALTERAR_A_DATA_PARA_UM_DIA_PASSADO);
        }

        Agendamento agendamento = agendamentoRepository.findById(idAgendamento).get();

        Long diferencaHoras =
                ChronoUnit.HOURS.between(LocalDateTime.now(), agendamento.getDataHora());

        if (diferencaHoras < HORAS_MINIMAS_PARA_REAGENDAMENTO) {
            throw new RegrasDeNegocioException(
                    NÃO_É_POSSÍVEL_ALTERAR_A_DATA_COM_MENOS_DE_24_HORAS_DO_AGENDAMENTO);
        }

        return this.alteraInformacoesAgendamento(agendamento, agendamentoDTO);
    }

    @Override
    public Agendamento finalizaAgendamento(Long idAgendamento,
            FinalizaAgendamentoDTO finalizaAgendamentoDTO) throws RegrasDeNegocioException {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            throw new RegrasDeNegocioException(AGENDAMENTO_NÃO_ENCONTRADO_PARA_FINALIZAR);
        }

        return this.finalizaAgendamentoComSucesso(idAgendamento, finalizaAgendamentoDTO);

    }

    @Override
    public MensagemDTO deletaAgendamento(Long idAgendamento) throws RegrasDeNegocioException {

        if (!agendamentoRepository.existsById(idAgendamento)) {
            throw new RegrasDeNegocioException(AGENDAMENTO_NÃO_ENCONTRADO_PARA_CANCELAR);
        }

        agendamentoRepository.deleteById(idAgendamento);

        return new MensagemDTO(AGENDAMENTO_CANCELADO_E_EXCLUÍDO_COM_SUCESSO);
    }

    private Agendamento criaAgendamdentoComSucesso(AgendamentoDTO agendamentoDTO)
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
                    O_AGENDAMENTO_DEVE_FAZER_REFERENCIA_A_APENAS_UM_PROFISSIONAL);

        } else if (agendamentoDTO.getIdFuncionario() != null) {

            funcionario = funcionarioRepository.findById(agendamentoDTO.getIdFuncionario());

            agendamento.setFuncionario(funcionario.get());

        } else if (agendamentoDTO.getIdProfissionalAutonomo() != null) {

            autonomo = autonomoRepository.findById(agendamentoDTO.getIdProfissionalAutonomo());

            agendamento.setAutonomo(autonomo.get());

        } else {
            throw new RegrasDeNegocioException(
                    NÃO_FOI_POSSÍVEL_CONCLUIR_O_AGENDAMENTO_POIS_FALTA_A_REFERENCIA_AO_PRESTADOR_DE_SERVIÇO);
        }

        agendamentoRepository.save(agendamento);

        return agendamento;
    }

    private Agendamento alteraInformacoesAgendamento(Agendamento agendamento,
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

        return agendamento;
    }

    private Agendamento finalizaAgendamentoComSucesso(Long idAgendamento,
            FinalizaAgendamentoDTO finalizaAgendamentoDTO) {

        Agendamento agendamento = agendamentoRepository.findById(idAgendamento).get();

        agendamento.setRealizado(finalizaAgendamentoDTO.isRealizado());

        this.criaTransacao(idAgendamento, agendamento);

        agendamentoRepository.save(agendamento);

        return agendamento;
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
