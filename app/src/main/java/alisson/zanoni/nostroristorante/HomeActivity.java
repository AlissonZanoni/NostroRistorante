package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView textUsuarioLogado;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String nomeUsuarioLogado = ((Aplicacao) this.getApplication()).getUsuarioLogado();
        textUsuarioLogado = findViewById(R.id.usuarioLogado);
        textUsuarioLogado.setText(" "+nomeUsuarioLogado);

        btnLogout = findViewById(R.id.idBtnLogout);
        btnLogout.setOnClickListener(view -> {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("manterConectado", false);
            edit.putString("usuarioLogado", "Usuário");
            edit.apply();

            Intent logout = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(logout);
            finish();
        });

    }
}