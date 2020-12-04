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
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.entity.Salao;
import br.com.zup.estrelas.sb.entity.Servico;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;
import br.com.zup.estrelas.sb.repository.FuncionarioRepository;
import br.com.zup.estrelas.sb.repository.SalaoRepository;
import br.com.zup.estrelas.sb.repository.ServicoRepository;
import br.com.zup.estrelas.sb.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private static final String O_SALÃO_NÃO_EXISTE =
            "O salão em que está sendo alocado o funcionario não existe!";
    private static final String FUNCIONARIO_JA_CADASTRADO = "Funcionário já cadastrado.";
    private static final String FUNCIONARIO_INEXISTENTE = "Funcionário inexistente.";
    private static final String CPF_JÁ_EXISTE = "Cpf já existe no Banco de Dados!";

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    SalaoRepository salaoRepository;

    @Override
    public Funcionario buscaFuncionario(Long idFuncionario) throws RegrasDeNegocioException {
        return funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RegrasDeNegocioException(
                        "Não for possível achar o funcionario pelo Id: " + idFuncionario));
    }

    @Override
    public List<Funcionario> listaFuncionarios() {
        return (List<Funcionario>) funcionarioRepository.findAll();
    }

    @Override
    public Funcionario insereFuncionario(FuncionarioDTO funcionarioDTO)
            throws RegrasDeNegocioException {

        if (funcionarioRepository.existsByCpf(funcionarioDTO.getCpf())) {
            throw new RegrasDeNegocioException(FUNCIONARIO_JA_CADASTRADO);
        }

        if (!salaoRepository.existsById(funcionarioDTO.getIdUsuario())) {
            throw new RegrasDeNegocioException(O_SALÃO_NÃO_EXISTE);
        }

        Salao salao = salaoRepository.findById(funcionarioDTO.getIdUsuario()).get();

        return this.adicionaFuncionarioComSucesso(salao, funcionarioDTO);
    }

    @Override
    public Funcionario alteraFuncionario(Long idFuncionario, FuncionarioDTO alteraFuncionarioDTO)
            throws RegrasDeNegocioException {

        Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);

        if (!funcionarioConsultado.isPresent()) {
            throw new RegrasDeNegocioException(FUNCIONARIO_INEXISTENTE);
        }

        if (!funcionarioConsultado.get().getCpf().equals(alteraFuncionarioDTO.getCpf())
                && funcionarioRepository.existsByCpf(alteraFuncionarioDTO.getCpf())) {
            throw new RegrasDeNegocioException(CPF_JÁ_EXISTE);
        }

        if (!salaoRepository.existsById(alteraFuncionarioDTO.getIdUsuario())) {
            throw new RegrasDeNegocioException(O_SALÃO_NÃO_EXISTE);
        }

        Salao salao = salaoRepository.findById(alteraFuncionarioDTO.getIdUsuario()).get();

        return this.modificaFuncionario(salao, funcionarioConsultado, alteraFuncionarioDTO);
    }

    @Override
    public Funcionario inativaFuncionario(Long idFuncionario,
            InativaFuncionarioDTO inativaFuncionarioDTO) throws RegrasDeNegocioException {

        Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);

        if (!funcionarioConsultado.isPresent()) {
            throw new RegrasDeNegocioException(FUNCIONARIO_INEXISTENTE);
        }

        return inativaFuncionarioComSucesso(funcionarioConsultado, inativaFuncionarioDTO);
    }

    @Override
    public Funcionario adicionaServicoFuncionario(Long idFuncionario,
            AdicionaServicoDTO adicionaServicoDTO) throws RegrasDeNegocioException {

        if (!funcionarioRepository.existsById(idFuncionario)) {
            throw new RegrasDeNegocioException("FUNCIONARIO INEXISTENTE!");
        }

        if (!servicoRepository.existsById(adicionaServicoDTO.getIdServico())) {
            throw new RegrasDeNegocioException("SERVIÇO INEXISTENTE!");
        }

        return this.adicionaServico(idFuncionario, adicionaServicoDTO);
    }

    private Funcionario modificaFuncionario(Salao salao,
            Optional<Funcionario> funcionarioConsultado, FuncionarioDTO alteraFuncionarioDTO) {

        Funcionario funcionarioAlterado = funcionarioConsultado.get();

        BeanUtils.copyProperties(alteraFuncionarioDTO, funcionarioAlterado);
        funcionarioAlterado.setSalao(salao);

        funcionarioRepository.save(funcionarioAlterado);

        return funcionarioAlterado;

    }

    private Funcionario inativaFuncionarioComSucesso(Optional<Funcionario> funcionarioConsultado,
            InativaFuncionarioDTO inativaFuncionarioDTO) {

        Funcionario funcionario = funcionarioConsultado.get();

        BeanUtils.copyProperties(inativaFuncionarioDTO, funcionario);

        funcionarioRepository.save(funcionario);

        return funcionario;
    }

    private Funcionario adicionaFuncionarioComSucesso(Salao salao, FuncionarioDTO funcionarioDTO)
            throws RegrasDeNegocioException {

        Funcionario funcionario = new Funcionario();

        BeanUtils.copyProperties(funcionarioDTO, funcionario);
        funcionario.setAgendamentos(Collections.emptyList());
        funcionario.setServicos(Collections.emptyList());
        funcionario.setAtivo(true);
        funcionario.setSalao(salao);

        funcionarioRepository.save(funcionario);

        return funcionario;
    }

    private Funcionario adicionaServico(Long idFuncionario, AdicionaServicoDTO adicionaServicoDTO)
            throws RegrasDeNegocioException {

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();

        Servico servico = servicoRepository.findById(adicionaServicoDTO.getIdServico()).get();

        List<Servico> servicos = funcionario.getServicos();

        if (!servico.isAtivo()) {
            throw new RegrasDeNegocioException(
                    "O SERVICO ESTÁ MARCADO COMO INATIVO, ENTRE EM CONTATO COM SUPORTE!");
        }

        if (servicos.contains(servico)) {
            throw new RegrasDeNegocioException("SERVIÇO JÁ EXISTENTE NO PERFIL DO FUNCIONARIO!");
        }

        servicos.add(servico);

        funcionario.setServicos(servicos);

        funcionarioRepository.save(funcionario);

        return funcionario;
    }

}


