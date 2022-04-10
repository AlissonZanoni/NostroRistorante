package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    int getTempoDeEspera = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
            if(usuarioAtual != null)
                trocarDeTela(HomeActivity.class);
            else
                trocarDeTela(LoginActivity.class);

        },getTempoDeEspera );
    }

    public void trocarDeTela(Class tela){
        Intent trocarTela = new Intent(MainActivity.this,tela);
        startActivity(trocarTela);
        finish();
    }
}

