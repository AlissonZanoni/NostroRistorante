package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    int getTempoDeEspera = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trocarDeTela();
    }

    private void trocarDeTela() {
        new Handler().postDelayed(() -> {
            Intent splash = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(splash);
            finish();
        },getTempoDeEspera );
    }
}

