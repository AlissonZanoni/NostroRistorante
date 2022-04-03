package alisson.zanoni.nostroristorante;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import alisson.zanoni.nostroristorante.dao.UsuarioDAO;
import alisson.zanoni.nostroristorante.model.UsuarioModel;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Switch switchLogin;
    UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dao = new UsuarioDAO(LoginActivity.this);

        editEmail = findViewById(R.id.idEmailLogin);
        editSenha = findViewById(R.id.idSenhaLogin);
        switchLogin = findViewById(R.id.switchLogin);

        Button btnEntrar = findViewById(R.id.idBtnEntrar);
        Button btnRegistrar = findViewById(R.id.idBtnRegistrar);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        SharedPreferences.Editor edit = preferences.edit();

        btnEntrar.setOnClickListener(view -> {
            String emailLogin = editEmail.getText().toString();
            String senhaLogin = editSenha.getText().toString();

            if(!emailLogin.isEmpty() && !senhaLogin.isEmpty()) {
                UsuarioModel usuario = dao.SelectUsuarioEmailLogin(emailLogin);
                if(usuario != null) {
                    if(usuario.getSenha().equals(senhaLogin)){
                        ((Aplicacao) this.getApplication()).setUsuarioLogado(usuario.getNome());
                        if(switchLogin.isChecked()){
                            edit.putBoolean("manterConectado", true);
                            edit.putString("usuarioLogado", usuario.getNome());
                            edit.apply();
                        }
                        boolean telaApresentacaoJaVista = preferences.getBoolean("telaApresentacaoJaVista", false);
                        if(!telaApresentacaoJaVista)
                            trocarDeTela(ApresentacaoActivity.class);
                        else
                            trocarDeTela(HomeActivity.class);
                    } else {
                        Toast.makeText(LoginActivity.this, "Senha inválida!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Email não cadastrado!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegistrar.setOnClickListener(view -> trocarDeTela(CadastroActivity.class));

    }
    public void trocarDeTela(Class tela){
        Intent trocarTela = new Intent(LoginActivity.this,tela);
        startActivity(trocarTela);
        finish();
    }
}