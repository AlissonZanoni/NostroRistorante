package alisson.zanoni.nostroristorante.model;

public class Mesa {
    public int Numeracao;
    public int QuantidadeCadeiras;
    public String Descricao;
    public Boolean Status;

    public Mesa(){ }

    public Mesa(int numeracao, int quantidadeCadeiras, String descricao, Boolean status) {
        Numeracao = numeracao;
        QuantidadeCadeiras = quantidadeCadeiras;
        Descricao = descricao;
        Status = status;
    }

    public int getNumeracao() {
        return Numeracao;
    }

    public void setNumeracao(int numeracao) {
        Numeracao = numeracao;
    }

    public int getQuantidadeCadeiras() {
        return QuantidadeCadeiras;
    }

    public void setQuantidadeCadeiras(int quantidadeCadeiras) {
        QuantidadeCadeiras = quantidadeCadeiras;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    @Override public String toString() {
        return "Numeração: " + Numeracao + " Quantidade de Cadeiras: " + QuantidadeCadeiras + " Descrição: " + Descricao + " Status: " + Status;
    }
}
