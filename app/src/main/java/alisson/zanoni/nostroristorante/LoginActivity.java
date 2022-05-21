package alisson.zanoni.nostroristorante;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnEntrar, btnRegistrar;
    SharedPreferences preferences;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarComponentes();

        preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        edit = preferences.edit();

        btnEntrar.setOnClickListener(view -> {
            String emailLogin = editEmail.getText().toString();
            String senhaLogin = editSenha.getText().toString();

            if(emailLogin.isEmpty() || senhaLogin.isEmpty()) {
                Snackbar snackbar = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            } else {
                AutenticarUsuario(view, emailLogin, senhaLogin);
            }
        });

        btnRegistrar.setOnClickListener(view -> trocarDeTela(CadastroActivity.class));

    }
    public void trocarDeTela(Class tela){
        Intent trocarTela = new Intent(LoginActivity.this,tela);
        startActivity(trocarTela);
        finish();
    }

    private void AutenticarUsuario(View view, String email, String senha) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    boolean telaApresentacaoJaVista = preferences.getBoolean("telaApresentacaoJaVista", false);
                    if(!telaApresentacaoJaVista)
                        trocarDeTela(ApresentacaoActivity.class);
                    else
                        trocarDeTela(HomeActivity.class);
                } else {
                    String erro;
                    try {
                        throw task.getException();
                    } catch (Exception e) {
                        erro = "Login inválido";
                    }
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void iniciarComponentes() {
        editEmail = findViewById(R.id.idEmailLogin);
        editSenha = findViewById(R.id.idSenhaLogin);
        btnEntrar = findViewById(R.id.idBtnEntrar);
        btnRegistrar = findViewById(R.id.idBtnRegistrar);
    }
}