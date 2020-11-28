package br.com.zup.estrelas.sb.service.imp;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.FuncionarioDTO;
import br.com.zup.estrelas.sb.dto.InativaFuncionarioDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;
import br.com.zup.estrelas.sb.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private static final String CADASTRO_REALIZADO_COM_SUCESSO =
            "Cadastro de funcionário realizado com sucesso.";
    private static final String FUNCIONARIO_JA_CADASTRADO = "Funcionário já cadastrado.";
    private static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO =
            "Funcionário removido com sucesso.";
    private static final String FUNCIONARIO_INEXISTENTE = "Funcionário inexistente.";
    private static final String FUNCIONARIO_ALTERADO_COM_SUCESSO =
            "Funcionário alterado com sucesso.";
    private static final String FUNCIONARIO_INATIVO = "Funcionário inativo com sucesso";


    @Autowired
    FuncionarioRepository funcionarioRepository;

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
    public MensagemDTO removeFuncionario(Long idFuncionario) {

        if (!funcionarioRepository.existsById(idFuncionario)) {
            return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
        }

        funcionarioRepository.deleteById(idFuncionario);

        return new MensagemDTO(FUNCIONARIO_REMOVIDO_COM_SUCESSO);
    }

    @Override
    public MensagemDTO alteraFuncionario(Long idFuncionario, FuncionarioDTO alteraFuncionarioDTO) {

        Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);

        if (!funcionarioConsultado.isPresent()) {
            return new MensagemDTO(FUNCIONARIO_INEXISTENTE);

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
        funcionario.setAgendamento(Collections.emptyList());
        funcionario.setServico(Collections.emptyList());

        funcionarioRepository.save(funcionario);

        return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
    }

}


