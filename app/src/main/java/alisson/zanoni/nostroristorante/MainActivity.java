package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    int getTempoDeEspera = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        boolean isChecked = preferences.getBoolean("manterConectado", false);
        if(isChecked) {
            new Handler().postDelayed(() -> {
                ((Aplicacao) this.getApplication()).setUsuarioLogado(preferences.getString("usuarioLogado", "Usuário"));
                Intent splash = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(splash);
                finish();
            },getTempoDeEspera );
        } else {
            trocarDeTela();
        }
    }

    private void trocarDeTela() {
        new Handler().postDelayed(() -> {
            Intent splash = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(splash);
            finish();
        },getTempoDeEspera );
    }
}

