package alisson.zanoni.nostroristorante;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button btnEntrar,btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar = findViewById(R.id.idBtnEntrar);
        btnRegistrar = findViewById(R.id.idBtnRegistrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trocarDeTela(ApresentacaoActivity.class);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trocarDeTela(CadastroActivity.class);
            }
        });

    }
    public void trocarDeTela(Class tela){
        Intent trocarTela = new Intent(LoginActivity.this,tela);
        startActivity(trocarTela);
        finish();
    }
}