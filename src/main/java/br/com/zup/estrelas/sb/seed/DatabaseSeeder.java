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
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.enums.TipoPagamento;
import br.com.zup.estrelas.sb.enums.TipoUsuario;
import br.com.zup.estrelas.sb.repository.AgendamentoRepository;
import br.com.zup.estrelas.sb.repository.ClienteRepository;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;
import br.com.zup.estrelas.sb.repository.ProfissionalAutonomoRepository;
import br.com.zup.estrelas.sb.repository.SalaoRepository;

@Component
public class DatabaseSeeder {

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
    PasswordEncoder encoder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {

        this.seedClientes();
        this.seedFuncionarios();
        this.seedSalao();
        this.seedProfissionalAutonomo();
        this.seedAgendamento();
    }

    public void seedClientes() {

        Cliente novoCliente = new Cliente();

        novoCliente.setNome("Dayana");
        novoCliente.setAgendamentos(Collections.emptyList());
        novoCliente.setAtivo(true);
        novoCliente.setBairro("Jardim Europa");
        novoCliente.setCep("9876664");
        novoCliente.setCidade("São Paulo");
        novoCliente.setCpf("35122671818");
        novoCliente.setDataNascimento(LocalDate.of(1987, 04, 02));
        novoCliente.setEmail("dayana@mail.com");
        novoCliente.setEndereco("Rua das ameixas, 30");
        novoCliente.setEstado("SP");
        novoCliente.setLogin("dayana@mail.com");
        novoCliente.setSenha(encoder.encode("luz12345"));
        novoCliente.setTelefone("987659876");
        novoCliente.setTipoUsuario(TipoUsuario.CLIENTE);

        clienteRepository.save(novoCliente);

    }

    public Salao seedSalao() {

        Salao novoSalao = new Salao();

        novoSalao.setAtivo(true);
        novoSalao.setBairro("Jardim das Acácias");
        novoSalao.setCep("09766-098");
        novoSalao.setCidade("São Paulo");
        novoSalao.setCnpj("23.394.603/0001-10");
        novoSalao.setEmail("salao@mail.com");
        novoSalao.setEndereco("Avenida Dos Abacatees, 1250");
        novoSalao.setEstado("SP");
        novoSalao.setFormasPagamento(Collections.emptyList());
        novoSalao.setFuncionarios(Collections.emptyList()); // preciso passar um funcionário?
        novoSalao.setLogin("salao@mail.com");
        novoSalao.setNome("Beleza Natural");
        novoSalao.setNomeFantasia("Star Beauty");
        novoSalao.setSenha(encoder.encode("Timao1910"));
        novoSalao.setTelefone("987647374");
        novoSalao.setTipoUsuario(TipoUsuario.SALAO);

        salaoRepository.save(novoSalao);

        return novoSalao;
    }

    public void seedFuncionarios() {

        Funcionario novoFuncionario = new Funcionario();

        novoFuncionario.setAgendamentos(Collections.emptyList());
        novoFuncionario.setAtivo(true);
        novoFuncionario.setCpf("35122671818");
        novoFuncionario.setHoraInicioExpediente(LocalTime.of(8, 00));
        novoFuncionario.setHoraFimExpediente(LocalTime.of(18, 00));
        novoFuncionario.setHorarioAlmoco("12h às 13h");
        novoFuncionario.setNome("Dayana");
        novoFuncionario.setSalao(this.seedSalao()); // preciso realmente mandar um salão aqui
        novoFuncionario.setServicos(Collections.emptyList());
        novoFuncionario.setTelefone("9876354788");

        funcionarioRepository.save(novoFuncionario);

    }

    public void seedProfissionalAutonomo() {

        ProfissionalAutonomo novoAutonomo = new ProfissionalAutonomo();

        novoAutonomo.setAgendamentos(Collections.emptyList());
        novoAutonomo.setAtivo(true);
        novoAutonomo.setBairro("Vila Nova Conceição");
        novoAutonomo.setCep("09876776");
        novoAutonomo.setCidade("São Paulo");
        novoAutonomo.setCpf("709.509.380-68");
        novoAutonomo.setDataNascimento(LocalDate.of(1983, 9, 9));
        novoAutonomo.setEmail("profissa@mail.com");
        novoAutonomo.setEndereco("Rua das Noivas");
        novoAutonomo.setEstado("SP");
        novoAutonomo.setHoraInicioExpediente(LocalTime.of(9, 00));
        novoAutonomo.setHoraFimExpediente(LocalTime.of(19, 00));
        novoAutonomo.setLogin("profissa@mail.com");
        novoAutonomo.setNome("Fabiana");
        novoAutonomo.setSenha(encoder.encode("iupi98574"));
        novoAutonomo.setServicos(Collections.emptyList());
        novoAutonomo.setTelefone("987469857");
        novoAutonomo.setTipoUsuario(TipoUsuario.AUTONOMO);
        novoAutonomo.setFormasPagamento(Collections.emptyList());

        profissionalAutonomoRepository.save(novoAutonomo);

    }

    public void seedAgendamento() {

        Agendamento novoAgendamento = new Agendamento();

        novoAgendamento.setAutonomo(null);
        novoAgendamento.setCancelado(false);
        novoAgendamento.setCliente(null);
        novoAgendamento.setDataHora(LocalDateTime.of(2020, 12, 14, 14, 00));
        novoAgendamento.setDataHoraFim(LocalDateTime.of(2020, 12, 14, 15, 00));
        novoAgendamento.setFuncionario(null);
        novoAgendamento.setNomeCliente("Elias");
        novoAgendamento.setNomeServico("Corte");
        novoAgendamento.setRealizado(true);
        novoAgendamento.setServico(null);
        novoAgendamento.setTipoPagamento(TipoPagamento.DINHEIRO);

        agendamentoRepository.save(novoAgendamento);

    }


}
