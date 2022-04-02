package alisson.zanoni.nostroristorante.model;

public class UsuarioModel {

    public static final String tabela = "tb_usuario";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_NOME = "nome",
            COLUNA_SOBRENOME = "sobrenome",
            COLUNA_DATANASCIMENTO = "dataNascimento",
            COLUNA_EMAIL = "email",
            COLUNA_SENHA = "senha",
            COLUNA_TIPO_USUARIO = "tipoUsuario";

    public static final String
            CREATE_TABLE = "create table " + tabela
            +" ( "
            +COLUNA_ID + " integer primary key autoincrement, "
            +COLUNA_NOME + " text not null, "
            +COLUNA_SOBRENOME + " text not null, "
            +COLUNA_DATANASCIMENTO + " text not null, "
            +COLUNA_EMAIL + " text not null, "
            +COLUNA_SENHA + " text not null, "
            +COLUNA_TIPO_USUARIO + " int not null "
            +" );";

    public static final String
            DROP_TABLE = "drop table if exists " + tabela + ";";

    private long id;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String email;
    private String senha;
    private int tipoUsuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
