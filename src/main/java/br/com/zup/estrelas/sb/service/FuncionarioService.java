package br.com.zup.estrelas.sb.service;

import java.util.List;
import br.com.zup.estrelas.sb.dto.AlteraFuncionarioDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.entity.Funcionario;

public interface FuncionarioService {
    
    public MensagemDTO adicionaFuncionario(Funcionario funcionario);
    
    public Funcionario buscaFuncionario(Long idFuncionario);
    
    public List<Funcionario> listaFuncionarios();
    
    public MensagemDTO removeFuncionario(Long idFuncionario);
    
    public MensagemDTO alteraFuncionario(Long idFuncionario,AlteraFuncionarioDTO alteraFuncionarioDTO);
    

}
