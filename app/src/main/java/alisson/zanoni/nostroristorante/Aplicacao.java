package alisson.zanoni.nostroristorante;

import android.app.Application;

public class Aplicacao extends Application {

    private String usuarioLogado;

    public String getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(String usuario) {
        this.usuarioLogado = usuario;
    }
}
