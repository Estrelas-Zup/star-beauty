package br.com.zup.estrelas.sb.seed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.entity.Cliente;
import br.com.zup.estrelas.sb.entity.FormaPagamento;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.enums.TipoPagamento;
import br.com.zup.estrelas.sb.enums.TipoServico;
import br.com.zup.estrelas.sb.enums.TipoUsuario;
import br.com.zup.estrelas.sb.repository.AgendamentoRepository;
import br.com.zup.estrelas.sb.repository.ClienteRepository;
import br.com.zup.estrelas.sb.repository.FormaPagamentoRepository;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;
import br.com.zup.estrelas.sb.repository.ProfissionalAutonomoRepository;
import br.com.zup.estrelas.sb.repository.SalaoRepository;
import br.com.zup.estrelas.sb.repository.ServicoRepository;

@Component
public class DatabaseSeeder {

    private Servico servico;
    private FormaPagamento formaPagamento;
    private Cliente cliente;
    private Salao salao;
    private ProfissionalAutonomo profissionalAutonomo;
    private Funcionario funcionario;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    SalaoRepository salaoRepository;

    @Autowired
    ProfissionalAutonomoRepository profissionalAutonomoRepository;

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    PasswordEncoder encoder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        this.seedServico();
        this.seedFormaPagamento();
        this.seedCliente();
        this.seedSalao();
        this.seedFuncionario();
        this.seedProfissionalAutonomo();
        this.seedAgendamentoSalao();
        this.seedAgendamentoProfissional();
    }
    
    public void seedServico() {

        servico = new Servico();

        servico.setAtivo(true);
        servico.setDuracao("120");
        servico.setNomeServico("Depilação");
        servico.setTipoServico(TipoServico.DEPILACAO);
        servico.setValorServico(80.00);

        servicoRepository.save(servico);
    }

    public void seedFormaPagamento() {

        formaPagamento = new FormaPagamento();
        formaPagamento.setTipoPagamento(TipoPagamento.DINHEIRO);

        formaPagamentoRepository.save(formaPagamento);
    }

    public void seedCliente() {

        cliente = new Cliente();
        cliente.setNome("Dayana");
        cliente.setAtivo(true);
        cliente.setBairro("Jardim Europa");
        cliente.setCep("9876664");
        cliente.setCidade("São Paulo");
        cliente.setCpf("35122671818");
        cliente.setDataNascimento(LocalDate.of(1987, 04, 02));
        cliente.setEmail("dayana@mail.com");
        cliente.setEndereco("Rua das ameixas, 30");
        cliente.setEstado("SP");
        cliente.setLogin("dayana@mail.com");
        cliente.setSenha(encoder.encode("luz12345"));
        cliente.setTelefone("987659876");
        cliente.setTipoUsuario(TipoUsuario.CLIENTE);

        clienteRepository.save(cliente);
    }

    public void seedSalao() {

        salao = new Salao();

        salao.setAtivo(true);
        salao.setBairro("Jardim das Acácias");
        salao.setCep("09766-098");
        salao.setCidade("São Paulo");
        salao.setCnpj("23.394.603/0001-10");
        salao.setEmail("salao@mail.com");
        salao.setEndereco("Avenida Dos Abacatees, 1250");
        salao.setEstado("SP");
        salao.setFormasPagamento(Collections.singletonList(formaPagamento));
        salao.setLogin("salao@mail.com");
        salao.setNome("Beleza Natural");
        salao.setNomeFantasia("Star Beauty");
        salao.setSenha(encoder.encode("Timao1910"));
        salao.setTelefone("987647374");
        salao.setTipoUsuario(TipoUsuario.SALAO);

        salaoRepository.save(salao);
    }

    public void seedFuncionario() {

        funcionario = new Funcionario();

        funcionario.setAtivo(true);
        funcionario.setCpf("35122671818");
        funcionario.setHoraInicioExpediente(LocalTime.of(8, 00));
        funcionario.setHoraFimExpediente(LocalTime.of(18, 00));
        funcionario.setHorarioAlmoco("12h às 13h");
        funcionario.setNome("Dayana");
        funcionario.setSalao(salao);
        funcionario.setServicos(Collections.singletonList(servico));
        funcionario.setTelefone("9876354788");

        funcionarioRepository.save(funcionario);
    }

    public void seedProfissionalAutonomo() {

        profissionalAutonomo = new ProfissionalAutonomo();

        profissionalAutonomo.setAtivo(true);
        profissionalAutonomo.setBairro("Vila Nova Conceição");
        profissionalAutonomo.setCep("09876776");
        profissionalAutonomo.setCidade("São Paulo");
        profissionalAutonomo.setCpf("709.509.380-68");
        profissionalAutonomo.setDataNascimento(LocalDate.of(1983, 9, 9));
        profissionalAutonomo.setEmail("profissa@mail.com");
        profissionalAutonomo.setEndereco("Rua das Noivas");
        profissionalAutonomo.setEstado("SP");
        profissionalAutonomo.setHoraInicioExpediente(LocalTime.of(9, 00));
        profissionalAutonomo.setHoraFimExpediente(LocalTime.of(19, 00));
        profissionalAutonomo.setLogin("profissa@mail.com");
        profissionalAutonomo.setNome("Fabiana");
        profissionalAutonomo.setSenha(encoder.encode("iupi98574"));
        profissionalAutonomo.setServicos(Collections.singletonList(servico));
        profissionalAutonomo.setTelefone("987469857");
        profissionalAutonomo.setTipoUsuario(TipoUsuario.AUTONOMO);
        profissionalAutonomo.setFormasPagamento(Collections.singletonList(formaPagamento));

        profissionalAutonomoRepository.save(profissionalAutonomo);
    }

    public Agendamento seedAgendamentoProfissional() {

        Agendamento novoAgendamento = new Agendamento();

        novoAgendamento.setAutonomo(profissionalAutonomo);
        novoAgendamento.setCancelado(false);
        novoAgendamento.setCliente(cliente);
        novoAgendamento.setDataHora(LocalDateTime.of(2020, 12, 14, 14, 00));
        novoAgendamento.setDataHoraFim(LocalDateTime.of(2020, 12, 14, 15, 00));
        novoAgendamento.setRealizado(true);
        novoAgendamento.setServico(servico);
        novoAgendamento.setTipoPagamento(TipoPagamento.DINHEIRO);

        return agendamentoRepository.save(novoAgendamento);
    }
    
    public Agendamento seedAgendamentoSalao() {

        Agendamento novoAgendamento = new Agendamento();

        novoAgendamento.setFuncionario(funcionario);
        novoAgendamento.setCancelado(false);
        novoAgendamento.setCliente(cliente);
        novoAgendamento.setDataHora(LocalDateTime.of(2020, 12, 14, 15, 00));
        novoAgendamento.setDataHoraFim(LocalDateTime.of(2020, 12, 14, 16, 00));
        novoAgendamento.setRealizado(true);
        novoAgendamento.setServico(servico);
        novoAgendamento.setTipoPagamento(TipoPagamento.DINHEIRO);

        return agendamentoRepository.save(novoAgendamento);
    }

}
