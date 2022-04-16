package alisson.zanoni.nostroristorante.model;

public class Reserva {
    private String DataReserva;
    private int QuantidadePessoas;
    private Boolean PrecisaCadeirinhaBebe;
    private String Observacoes;
    private String IdUsuario;
    private int NumeracaoMesa;

    public Reserva() {}

    public Reserva(String dataReserva, int quantidadePessoas, Boolean precisaCadeirinhaBebe, String observacoes, String idUsuario, int numeracaoMesa) {
        DataReserva = dataReserva;
        QuantidadePessoas = quantidadePessoas;
        PrecisaCadeirinhaBebe = precisaCadeirinhaBebe;
        Observacoes = observacoes;
        IdUsuario = idUsuario;
        NumeracaoMesa = numeracaoMesa;
    }

    public String getDataReserva() {
        return DataReserva;
    }

    public void setDataReserva(String data) {
        DataReserva = data;
    }

    public int getQuantidadePessoas() {
        return QuantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        QuantidadePessoas = quantidadePessoas;
    }

    public Boolean getPrecisaCadeirinhaBebe() {
        return PrecisaCadeirinhaBebe;
    }

    public void setPrecisaCadeirinhaBebe(Boolean precisaCadeirinhaBebe) {
        PrecisaCadeirinhaBebe = precisaCadeirinhaBebe;
    }

    public String getObservacoes() {
        return Observacoes;
    }

    public void setObservacoes(String observacoes) {
        Observacoes = observacoes;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.IdUsuario = idUsuario;
    }

    public int getNumeracaoMesa() {
        return NumeracaoMesa;
    }

    public void setNumeracaoMesa(int numeracaoMesa) {
        NumeracaoMesa = numeracaoMesa;
    }
}
