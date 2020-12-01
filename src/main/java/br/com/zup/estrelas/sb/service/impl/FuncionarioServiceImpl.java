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
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;
import br.com.zup.estrelas.sb.repository.ServicoRepository;
import br.com.zup.estrelas.sb.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

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

    @Override
    public MensagemDTO adicionaFuncionario(FuncionarioDTO funcionarioDTO) {

        if (funcionarioRepository.existsByCpf(funcionarioDTO.getCpf())) {
            return new MensagemDTO(FUNCIONARIO_JA_CADASTRADO);
        }

        return this.criaFuncionarioComSucesso(funcionarioDTO);
    }

    @Override
    public Funcionario buscaFuncionario(Long idFuncionario) {
        return funcionarioRepository.findById(idFuncionario).orElse(null);
    }

    @Override
    public List<Funcionario> listaFuncionarios() {
        return (List<Funcionario>) funcionarioRepository.findAll();
    }

    @Override
    public MensagemDTO alteraFuncionario(Long idFuncionario, FuncionarioDTO alteraFuncionarioDTO) {

        Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);

        if (!funcionarioConsultado.isPresent()) {
            return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
        }

        if (funcionarioConsultado.get().getCpf() == alteraFuncionarioDTO.getCpf()
                && funcionarioRepository.existsByCpf(alteraFuncionarioDTO.getCpf())) {
            return new MensagemDTO(CPF_JÁ_EXISTE);
        }

        return this.alteraInformacaoFuncionario(funcionarioConsultado, alteraFuncionarioDTO);
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

        if (!servicoRepository.existsById(adicionaServicoDTO.getIdSevico())) {
            return new MensagemDTO("SERVIÇO INEXISTENTE!");
        }

        return this.adicionaServico(idFuncionario, adicionaServicoDTO);
    }


    private MensagemDTO alteraInformacaoFuncionario(Optional<Funcionario> funcionarioConsultado,
            FuncionarioDTO alteraFuncionarioDTO) {

        Funcionario funcionarioAlterado = funcionarioConsultado.get();

        BeanUtils.copyProperties(alteraFuncionarioDTO, funcionarioAlterado);

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

    private MensagemDTO criaFuncionarioComSucesso(FuncionarioDTO funcionarioDTO) {

        Funcionario funcionario = new Funcionario();

        BeanUtils.copyProperties(funcionarioDTO, funcionario);
        funcionario.setAgendamentos(Collections.emptyList());
        funcionario.setServicos(Collections.emptyList());
        funcionario.setAtivo(true);

        funcionarioRepository.save(funcionario);

        return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
    }

    private MensagemDTO adicionaServico(Long idFuncionario, AdicionaServicoDTO adicionaServicoDTO) {

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();

        Servico servico = servicoRepository.findById(adicionaServicoDTO.getIdSevico()).get();

        List<Servico> servicos = funcionario.getServicos();

        for (Servico servicoFuncionario : servicos) {
            if (servicos.contains(servicoFuncionario)) {
                return new MensagemDTO("SERVIÇO JÁ EXISTENTE NO PERFIL DO FUNCIONARIO!");
            }
        }

        servicos.add(servico);

        funcionario.setServicos(servicos);

        return new MensagemDTO("SERVICO ADICIONADO COM SUCESSO!");
    }

}


