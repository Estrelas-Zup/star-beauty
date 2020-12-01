package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.AdicionaServicoDTO;
import br.com.zup.estrelas.sb.dto.FuncionarioDTO;
import br.com.zup.estrelas.sb.dto.InativaFuncionarioDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;

public interface FuncionarioService {

    public MensagemDTO adicionaFuncionario(FuncionarioDTO funcionarioDTO);

    public Funcionario buscaFuncionario(Long idFuncionario);

    public List<Funcionario> listaFuncionarios();

    public MensagemDTO alteraFuncionario(Long idFuncionario, FuncionarioDTO alteraFuncionarioDTO);

    public MensagemDTO inativaFuncionario(Long idFuncionario,
            InativaFuncionarioDTO inativaFuncionarioDTO);

    public MensagemDTO adicionaServicoFuncionario(Long idFuncionario,
            AdicionaServicoDTO adicionaServicoDTO);

}
