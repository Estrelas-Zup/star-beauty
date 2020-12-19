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
            "O SALÃO ONDE ESTA SENDO ALOCADO O FUNCIONÁRIO  NÃO EXISTE!";
    private static final String FUNCIONARIO_JA_CADASTRADO = "FUNCINÁRIO JÁ CADASTRADO!";
    private static final String FUNCIONARIO_INEXISTENTE = "FUNCIONÁRIO INEXISTENTE!";
    private static final String CPF_JÁ_EXISTE = "CPF JÁ EXISTE NO BANCO DE DADOS!";
    private static final String SERVICO_INEXISTENTE = "SERVIÇO INEXISTENTE!";
    private static final String NAO_FOI_POSSIVEL_ACHAR_FUNCIONARIO_PELO_ID = "NÃO FOI POSSÍVEL ACHAR O FUNIONÁRIO PELO ID  ";
    private static final String SERVIÇO_JÁ_EXISTENTE_NO_PERFIL_DO_FUNCIONARIO =
            "SERVIÇO JÁ EXISTENTE NO PERFIL DO FUNCIONÁRIO!";

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
                        NAO_FOI_POSSIVEL_ACHAR_FUNCIONARIO_PELO_ID + idFuncionario));
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

        if (!salaoRepository.existsById(funcionarioDTO.getIdSalao())) {
            throw new RegrasDeNegocioException(O_SALÃO_NÃO_EXISTE);
        }

        return this.adicionaFuncionarioComSucesso(funcionarioDTO);
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

        if (!salaoRepository.existsById(alteraFuncionarioDTO.getIdSalao())) {
            throw new RegrasDeNegocioException(O_SALÃO_NÃO_EXISTE);
        }

        return this.modificaFuncionario(funcionarioConsultado, alteraFuncionarioDTO);
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
            throw new RegrasDeNegocioException(FUNCIONARIO_INEXISTENTE);
        }

        if (!servicoRepository.existsById(adicionaServicoDTO.getIdServico())) {
            throw new RegrasDeNegocioException(SERVICO_INEXISTENTE);
        }

        return this.adicionaServico(idFuncionario, adicionaServicoDTO);
    }

    private Funcionario modificaFuncionario(Optional<Funcionario> funcionarioConsultado, FuncionarioDTO alteraFuncionarioDTO) {

        Funcionario funcionarioAlterado = funcionarioConsultado.get();
        Salao salao = salaoRepository.findById(alteraFuncionarioDTO.getIdSalao()).get();

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

    private Funcionario adicionaFuncionarioComSucesso(FuncionarioDTO funcionarioDTO)
            throws RegrasDeNegocioException {

        Funcionario funcionario = new Funcionario();        
        Salao salao = salaoRepository.findById(funcionarioDTO.getIdSalao()).get();

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

        if (servicos.contains(servico)) {
            throw new RegrasDeNegocioException(SERVIÇO_JÁ_EXISTENTE_NO_PERFIL_DO_FUNCIONARIO);
        }

        servicos.add(servico);

        funcionario.setServicos(servicos);

        funcionarioRepository.save(funcionario);

        return funcionario;
    }

}


