package alisson.zanoni.nostroristorante.model;

public class Comida {
    private String Nome;
    private String Descricao;
    private Double Preco;
    private String Tipo;

    public Comida(){}

    public Comida(String nome, String descricao, Double preco, String tipo) {
        Nome = nome;
        Descricao = descricao;
        Preco = preco;
        Tipo = tipo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Double getPreco() {
        return Preco;
    }

    public void setPreco(Double preco) {
        Preco = preco;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    @Override public String toString() {
        return "Nome: " + Nome + " Descrição: " + Descricao + " Preço: " + Preco;
    }
}
