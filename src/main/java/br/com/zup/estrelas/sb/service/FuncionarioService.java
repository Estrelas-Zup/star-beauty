package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FuncionarioDTO;
import br.com.zup.estrelas.sb.dto.InativaFuncionarioDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;
import br.com.zup.estrelas.sb.exceptions.RegrasDeNegocioException;

public interface FuncionarioService {

    public MensagemDTO adicionaFuncionario(FuncionarioDTO funcionarioDTO) throws RegrasDeNegocioException;

    public Funcionario buscaFuncionario(Long idFuncionario) throws RegrasDeNegocioException;

    public List<Funcionario> listaFuncionarios();

    public MensagemDTO alteraFuncionario(Long idFuncionario, FuncionarioDTO alteraFuncionarioDTO)
            throws RegrasDeNegocioException;

    public MensagemDTO inativaFuncionario(Long idFuncionario,
            InativaFuncionarioDTO inativaFuncionarioDTO) throws RegrasDeNegocioException;

    public MensagemDTO adicionaServicoFuncionario(Long idFuncionario,
            AdicionaServicoDTO adicionaServicoDTO) throws RegrasDeNegocioException;

}
