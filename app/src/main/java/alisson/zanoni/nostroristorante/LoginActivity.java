package alisson.zanoni.nostroristorante;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import alisson.zanoni.nostroristorante.dao.UsuarioDAO;
import alisson.zanoni.nostroristorante.model.UsuarioModel;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dao = new UsuarioDAO(LoginActivity.this);

        editEmail = findViewById(R.id.idEmailLogin);
        editSenha = findViewById(R.id.idSenhaLogin);

        Button btnEntrar = findViewById(R.id.idBtnEntrar);
        Button btnRegistrar = findViewById(R.id.idBtnRegistrar);

        btnEntrar.setOnClickListener(view -> {
            String emailLogin = editEmail.getText().toString();
            String senhaLogin = editSenha.getText().toString();

            if(!emailLogin.isEmpty() && !senhaLogin.isEmpty()) {
                UsuarioModel usuario = dao.SelectUsuarioEmailLogin(emailLogin);
                if(usuario != null) {
                    if(usuario.getSenha().equals(senhaLogin)){
                        trocarDeTela(ApresentacaoActivity.class);
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