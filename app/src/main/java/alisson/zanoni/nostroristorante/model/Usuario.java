package alisson.zanoni.nostroristorante.model;

public class Usuario {
    private String Nome;
    private String Sobrenome;
    private String DataNascimento;
    private String Telefone;

    public Usuario(){}

    public Usuario(String nome, String sobrenome, String dataNascimento, String telefone) {
        Nome = nome;
        Sobrenome = sobrenome;
        DataNascimento = dataNascimento;
        Telefone = telefone;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }


}
