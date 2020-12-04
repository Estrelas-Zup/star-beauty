package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FuncionarioDTO;
import br.com.zup.estrelas.sb.dto.InativaFuncionarioDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface FuncionarioService {

    public Funcionario insereFuncionario(FuncionarioDTO funcionarioDTO) throws RegrasDeNegocioException;

    public Funcionario buscaFuncionario(Long idFuncionario) throws RegrasDeNegocioException;

    public List<Funcionario> listaFuncionarios();

    public Funcionario alteraFuncionario(Long idFuncionario, FuncionarioDTO alteraFuncionarioDTO)
            throws RegrasDeNegocioException;

    public Funcionario inativaFuncionario(Long idFuncionario,
            InativaFuncionarioDTO inativaFuncionarioDTO) throws RegrasDeNegocioException;

    public Funcionario adicionaServicoFuncionario(Long idFuncionario,
            AdicionaServicoDTO adicionaServicoDTO) throws RegrasDeNegocioException;

}
