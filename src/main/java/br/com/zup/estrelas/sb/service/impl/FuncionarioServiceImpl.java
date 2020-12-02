package br.com.zup.estrelas.sb.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FuncionarioDTO;
import br.com.zup.estrelas.sb.dto.InativaFuncionarioDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;
import br.com.zup.estrelas.sb.repository.SalaoRepository;
import br.com.zup.estrelas.sb.repository.ServicoRepository;
import br.com.zup.estrelas.sb.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private static final String O_SALÃO_NÃO_EXISTE =
            "O salão em que está sendo alocado o funcionario não existe!";
    private static final String CADASTRO_REALIZADO_COM_SUCESSO =
            "Cadastro de funcionário realizado com sucesso.";
    private static final String FUNCIONARIO_JA_CADASTRADO = "Funcionário já cadastrado.";
    private static final String FUNCIONARIO_INEXISTENTE = "Funcionário inexistente.";
    private static final String FUNCIONARIO_ALTERADO_COM_SUCESSO =
            "Funcionário alterado com sucesso.";
    private static final String FUNCIONARIO_INATIVO = "Funcionário inativo com sucesso";
    private static final String CPF_JÁ_EXISTE = "Cpf já existe no Banco de Dados!";

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    SalaoRepository salaoRepository;

    @Override
    public Funcionario buscaFuncionario(Long idFuncionario) {
        return funcionarioRepository.findById(idFuncionario).orElse(null);
    }

    @Override
    public List<Funcionario> listaFuncionarios() {
        return (List<Funcionario>) funcionarioRepository.findAll();
    }

    @Override
    public MensagemDTO adicionaFuncionario(FuncionarioDTO funcionarioDTO) {

        if (funcionarioRepository.existsByCpf(funcionarioDTO.getCpf())) {
            return new MensagemDTO(FUNCIONARIO_JA_CADASTRADO);
        }

        if (!salaoRepository.existsById(funcionarioDTO.getIdUsuario())) {
            return new MensagemDTO(O_SALÃO_NÃO_EXISTE);
        }

        Salao salao = salaoRepository.findById(funcionarioDTO.getIdUsuario()).get();

        return this.criaFuncionarioComSucesso(salao, funcionarioDTO);
    }

    @Override
    public MensagemDTO alteraFuncionario(Long idFuncionario, FuncionarioDTO alteraFuncionarioDTO) {

        Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);

        if (!funcionarioConsultado.isPresent()) {
            return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
        }

        if (!funcionarioConsultado.get().getCpf().equals(alteraFuncionarioDTO.getCpf())
                && funcionarioRepository.existsByCpf(alteraFuncionarioDTO.getCpf())) {
            return new MensagemDTO(CPF_JÁ_EXISTE);
        }

        if (!salaoRepository.existsById(alteraFuncionarioDTO.getIdUsuario())) {
            return new MensagemDTO(O_SALÃO_NÃO_EXISTE);
        }

        Salao salao = salaoRepository.findById(alteraFuncionarioDTO.getIdUsuario()).get();

        return this.alteraInformacaoFuncionario(salao, funcionarioConsultado, alteraFuncionarioDTO);
    }

    @Override
    public MensagemDTO inativaFuncionario(Long idFuncionario,
            InativaFuncionarioDTO inativaFuncionarioDTO) {

        Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);

        if (!funcionarioConsultado.isPresent()) {
            return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
        }

        return inativaFuncionarioComSucesso(funcionarioConsultado, inativaFuncionarioDTO);
    }

    @Override
    public MensagemDTO adicionaServicoFuncionario(Long idFuncionario,
            AdicionaServicoDTO adicionaServicoDTO) {

        if (!funcionarioRepository.existsById(idFuncionario)) {
            return new MensagemDTO("FUNCIONARIO INEXISTENTE!");
        }

        if (!servicoRepository.existsById(adicionaServicoDTO.getIdServico())) {
            return new MensagemDTO("SERVIÇO INEXISTENTE!");
        }

        return this.adicionaServico(idFuncionario, adicionaServicoDTO);
    }

    private MensagemDTO alteraInformacaoFuncionario(Salao salao,
            Optional<Funcionario> funcionarioConsultado, FuncionarioDTO alteraFuncionarioDTO) {

        Funcionario funcionarioAlterado = funcionarioConsultado.get();

        BeanUtils.copyProperties(alteraFuncionarioDTO, funcionarioAlterado);
        funcionarioAlterado.setSalao(salao);

        funcionarioRepository.save(funcionarioAlterado);

        return new MensagemDTO(FUNCIONARIO_ALTERADO_COM_SUCESSO);

    }

    private MensagemDTO inativaFuncionarioComSucesso(Optional<Funcionario> funcionarioConsultado,
            InativaFuncionarioDTO inativaFuncionarioDTO) {

        Funcionario funcionario = funcionarioConsultado.get();

        BeanUtils.copyProperties(inativaFuncionarioDTO, funcionario);

        funcionarioRepository.save(funcionario);

        return new MensagemDTO(FUNCIONARIO_INATIVO);
    }

    private MensagemDTO criaFuncionarioComSucesso(Salao salao, FuncionarioDTO funcionarioDTO) {

        Funcionario funcionario = new Funcionario();

        BeanUtils.copyProperties(funcionarioDTO, funcionario);
        funcionario.setAgendamentos(Collections.emptyList());
        funcionario.setServicos(Collections.emptyList());
        funcionario.setAtivo(true);
        funcionario.setSalao(salao);

        funcionarioRepository.save(funcionario);

        return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
    }

    private MensagemDTO adicionaServico(Long idFuncionario, AdicionaServicoDTO adicionaServicoDTO) {

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();

        Servico servico = servicoRepository.findById(adicionaServicoDTO.getIdServico()).get();

        List<Servico> servicos = funcionario.getServicos();

        if (!servico.isAtivo()) {
            return new MensagemDTO(
                    "O SERVICO ESTÁ MARCADO COMO INATIVO, ENTRE EM CONTATO COM SUPORTE!");
        }

        if (servicos.contains(servico)) {
            return new MensagemDTO("SERVIÇO JÁ EXISTENTE NO PERFIL DO FUNCIONARIO!");
        }

        servicos.add(servico);

        funcionario.setServicos(servicos);

        funcionarioRepository.save(funcionario);

        return new MensagemDTO("SERVICO ADICIONADO COM SUCESSO!");
    }

}


