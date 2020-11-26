package br.com.zup.estrelas.sb.service.imp;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.AlteraFuncionarioDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;
import br.com.zup.estrelas.sb.service.FuncionarioService;

@Service
public class FuncionarioServiceImp implements FuncionarioService {

    private static final String CADASTRO_REALIZADO_COM_SUCESSO =
            "Cadastro de funcionário realizado com sucesso.";
    private static final String FUNCIONARIO_JA_CADASTRADO = "Funcionário já cadastrado.";
    private static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO =
            "Funcionário removido com sucesso.";
    private static final String FUNCIONARIO_INEXISTENTE = "Funcionário inexistente.";
    private static final String FUNCIONARIO_ALTERADO_COM_SUCESSO =
            "Funcionário alterado com sucesso.";


    @Autowired
    FuncionarioRepository funcionarioRepository;

    public MensagemDTO adicionaFuncionario(Funcionario funcionario) {

        if (funcionarioRepository.existsById(funcionario.getIdFuncionario())) {
            return new MensagemDTO(FUNCIONARIO_JA_CADASTRADO);
        }

        funcionarioRepository.save(funcionario);
        return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
    }

    public Funcionario buscaFuncionario(Long idFuncionario) {

        return funcionarioRepository.findById(idFuncionario).orElse(null);
    }

    public List<Funcionario> listaFuncionarios() {
        return (List<Funcionario>) funcionarioRepository.findAll();
    }

    public MensagemDTO removeFuncionario(Long idFuncionario) {

        if (funcionarioRepository.existsById(idFuncionario)) {
            funcionarioRepository.deleteById(idFuncionario);
            return new MensagemDTO(FUNCIONARIO_REMOVIDO_COM_SUCESSO);
        }
        return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
    }


    public MensagemDTO alteraFuncionario(Long idFuncionario,
            AlteraFuncionarioDTO alteraFuncionarioDTO) {

        Optional<Funcionario> funcinarioConsultado = funcionarioRepository.findById(idFuncionario);

        if (funcinarioConsultado.isPresent()) {

            Funcionario funcionarioAlterado = funcinarioConsultado.get();

            funcionarioAlterado.setNome(alteraFuncionarioDTO.getNome());
            funcionarioAlterado.setCpf(alteraFuncionarioDTO.getCpf());
            funcionarioAlterado.setTelefone(alteraFuncionarioDTO.getTelefone());
            // funcionarioAlterado.setAtivo(alteraFuncionarioDTO.isAtivo());


            funcionarioRepository.save(funcionarioAlterado);
            return new MensagemDTO(FUNCIONARIO_ALTERADO_COM_SUCESSO);
        }

        return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
    }
}


