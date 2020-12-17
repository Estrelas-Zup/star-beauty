//package br.com.zup.estrelas.sb.seed;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.List;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import br.com.zup.estrelas.sb.entity.Agendamento;
//import br.com.zup.estrelas.sb.entity.Cliente;
//import br.com.zup.estrelas.sb.entity.FormaPagamento;
//import br.com.zup.estrelas.sb.entity.Funcionario;
//import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;
//import br.com.zup.estrelas.sb.entity.Salao;
//import br.com.zup.estrelas.sb.entity.Servico;
//import br.com.zup.estrelas.sb.enums.TipoPagamento;
//import br.com.zup.estrelas.sb.enums.TipoServico;
//import br.com.zup.estrelas.sb.enums.TipoUsuario;
//import br.com.zup.estrelas.sb.repository.AgendamentoRepository;
//import br.com.zup.estrelas.sb.repository.ClienteRepository;
//import br.com.zup.estrelas.sb.repository.FormaPagamentoRepository;
//import br.com.zup.estrelas.sb.repository.FuncionarioRepository;
//import br.com.zup.estrelas.sb.repository.ProfissionalAutonomoRepository;
//import br.com.zup.estrelas.sb.repository.SalaoRepository;
//import br.com.zup.estrelas.sb.repository.ServicoRepository;
//
//@Component
//public class DatabaseSeeder {
//
//    private Cliente cliente;
//    private List<Funcionario> funcionarios;
//    private Salao salao;
//    private ProfissionalAutonomo profissionalAutonomo;
//    private List<Agendamento> agendamentos;
//    private List<FormaPagamento> formasPagamento;
//    private List<Servico> servicos;
//    private Servico servico;
//
//    @Autowired
//    ClienteRepository clienteRepository;
//
//    @Autowired
//    FuncionarioRepository funcionarioRepository;
//
//    @Autowired
//    SalaoRepository salaoRepository;
//
//    @Autowired
//    ProfissionalAutonomoRepository profissionalAutonomoRepository;
//
//    @Autowired
//    AgendamentoRepository agendamentoRepository;
//
//    @Autowired
//    FormaPagamentoRepository formaPagamentoRepository;
//
//    @Autowired
//    ServicoRepository servicoRepository;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @EventListener
//    public void seed(ContextRefreshedEvent event) {
//
//        this.seedClientes();
//        this.seedFuncionarios();
//        this.seedSalao();
//        this.seedProfissionalAutonomo();
//        this.seedAgendamento();
//        this.seedFormaPagamento();
//        this.seedServico();
//    }
//
//    public void seedClientes() {
//
//        Cliente novoCliente = new Cliente();
//
//        novoCliente.setNome("Dayana");
//        novoCliente.setAgendamentos(agendamentos);
//        novoCliente.setAtivo(true);
//        novoCliente.setBairro("Jardim Europa");
//        novoCliente.setCep("9876664");
//        novoCliente.setCidade("São Paulo");
//        novoCliente.setCpf("35122671818");
//        novoCliente.setDataNascimento(LocalDate.of(1987, 04, 02));
//        novoCliente.setEmail("dayana@mail.com");
//        novoCliente.setEndereco("Rua das ameixas, 30");
//        novoCliente.setEstado("SP");
//        novoCliente.setLogin("dayana@mail.com");
//        novoCliente.setSenha(encoder.encode("luz12345"));
//        novoCliente.setTelefone("987659876");
//        novoCliente.setTipoUsuario(TipoUsuario.CLIENTE);
//
//        BeanUtils.copyProperties(novoCliente, cliente);
//
//        clienteRepository.save(novoCliente);
//
//    }
//
//    public void seedSalao() {
//
//        Salao novoSalao = new Salao();
//
//        novoSalao.setAtivo(true);
//        novoSalao.setBairro("Jardim das Acácias");
//        novoSalao.setCep("09766-098");
//        novoSalao.setCidade("São Paulo");
//        novoSalao.setCnpj("23.394.603/0001-10");
//        novoSalao.setEmail("salao@mail.com");
//        novoSalao.setEndereco("Avenida Dos Abacatees, 1250");
//        novoSalao.setEstado("SP");
//        novoSalao.setFormasPagamento(formasPagamento);
//        novoSalao.setFuncionarios(funcionarios);
//        novoSalao.setLogin("salao@mail.com");
//        novoSalao.setNome("Beleza Natural");
//        novoSalao.setNomeFantasia("Star Beauty");
//        novoSalao.setSenha(encoder.encode("Timao1910"));
//        novoSalao.setTelefone("987647374");
//        novoSalao.setTipoUsuario(TipoUsuario.SALAO);
//
//        BeanUtils.copyProperties(novoSalao, salao);
//
//        salaoRepository.save(novoSalao);
//
//    }
//
//    public void seedFuncionarios() {
//
//        Funcionario novoFuncionario = new Funcionario();
//
//        novoFuncionario.setAgendamentos(agendamentos);
//        novoFuncionario.setAtivo(true);
//        novoFuncionario.setCpf("35122671818");
//        novoFuncionario.setHoraInicioExpediente(LocalTime.of(8, 00));
//        novoFuncionario.setHoraFimExpediente(LocalTime.of(18, 00));
//        novoFuncionario.setHorarioAlmoco("12h às 13h");
//        novoFuncionario.setNome("Dayana");
//        novoFuncionario.setSalao(salao);
//        novoFuncionario.setServicos(servicos);
//        novoFuncionario.setTelefone("9876354788");
//
//        funcionarios.add(novoFuncionario);
//
//        funcionarioRepository.save(novoFuncionario);
//
//    }
//
//    public void seedProfissionalAutonomo() {
//
//        ProfissionalAutonomo novoAutonomo = new ProfissionalAutonomo();
//
//        novoAutonomo.setAgendamentos(agendamentos);
//        novoAutonomo.setAtivo(true);
//        novoAutonomo.setBairro("Vila Nova Conceição");
//        novoAutonomo.setCep("09876776");
//        novoAutonomo.setCidade("São Paulo");
//        novoAutonomo.setCpf("709.509.380-68");
//        novoAutonomo.setDataNascimento(LocalDate.of(1983, 9, 9));
//        novoAutonomo.setEmail("profissa@mail.com");
//        novoAutonomo.setEndereco("Rua das Noivas");
//        novoAutonomo.setEstado("SP");
//        novoAutonomo.setHoraInicioExpediente(LocalTime.of(9, 00));
//        novoAutonomo.setHoraFimExpediente(LocalTime.of(19, 00));
//        novoAutonomo.setLogin("profissa@mail.com");
//        novoAutonomo.setNome("Fabiana");
//        novoAutonomo.setSenha(encoder.encode("iupi98574"));
//        novoAutonomo.setServicos(servicos);
//        novoAutonomo.setTelefone("987469857");
//        novoAutonomo.setTipoUsuario(TipoUsuario.AUTONOMO);
//        novoAutonomo.setFormasPagamento(formasPagamento);
//
//        BeanUtils.copyProperties(novoAutonomo, profissionalAutonomo);
//
//        profissionalAutonomoRepository.save(novoAutonomo);
//
//    }
//
//    public void seedAgendamento() {
//
//        Agendamento novoAgendamento = new Agendamento();
//
//        novoAgendamento.setAutonomo(profissionalAutonomo);
//        novoAgendamento.setCancelado(false);
//        novoAgendamento.setCliente(cliente);
//        novoAgendamento.setDataHora(LocalDateTime.of(2020, 12, 14, 14, 00));
//        novoAgendamento.setDataHoraFim(LocalDateTime.of(2020, 12, 14, 15, 00));
//        novoAgendamento.setFuncionario(null);
//        novoAgendamento.setNomeCliente("Elias");
//        novoAgendamento.setNomeServico("Corte");
//        novoAgendamento.setRealizado(true);
//        novoAgendamento.setServico(servico);
//        novoAgendamento.setTipoPagamento(TipoPagamento.DINHEIRO);
//
//        agendamentos.add(novoAgendamento);
//
//        agendamentoRepository.save(novoAgendamento);
//
//    }
//
//    public void seedFormaPagamento() {
//
//        FormaPagamento novaFormaPagamento = new FormaPagamento();
//
//        novaFormaPagamento.setTipoPagamento(TipoPagamento.DINHEIRO);
//
//        formasPagamento.add(novaFormaPagamento);
//
//        formaPagamentoRepository.save(novaFormaPagamento);
//
//    }
//
//    public void seedServico() {
//
//        Servico novoServico = new Servico();
//
//        novoServico.setAtivo(true);
//        novoServico.setDuracao("2 horas");
//        novoServico.setNomeServico("Depilação");
//        novoServico.setTipoServico(TipoServico.DEPILACAO);
//        novoServico.setValorServico(80.00);
//
//        servicos.add(novoServico);
//
//        BeanUtils.copyProperties(novoServico, servico);
//
//        servicoRepository.save(novoServico);
//
//    }
//
//}
