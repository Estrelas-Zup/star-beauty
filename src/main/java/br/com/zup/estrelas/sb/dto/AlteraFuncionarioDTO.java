package br.com.zup.estrelas.sb.dto;


public class AlteraFuncionarioDTO {


    private String nome;

    private String cpf;

    private String telefone;

    private String horarioAlmoco;

    private boolean ativo;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getHorarioAlmoco() {
        return horarioAlmoco;
    }

    public void setHorarioAlmoco(String horarioAlmoco) {
        this.horarioAlmoco = horarioAlmoco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // private Salao salao; -> dúvida se há necessidade

}
