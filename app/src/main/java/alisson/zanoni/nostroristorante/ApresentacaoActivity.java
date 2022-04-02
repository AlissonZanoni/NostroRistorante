package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class ApresentacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        LinearLayout lnLayout = findViewById(R.id.idLayoutApresentacao);

        lnLayout.setOnClickListener(view -> {
            Intent trocarTela = new Intent(ApresentacaoActivity.this,HomeActivity.class);
            startActivity(trocarTela);
            finish();
        });

    }
}