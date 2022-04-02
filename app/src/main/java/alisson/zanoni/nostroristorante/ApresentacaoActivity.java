package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ApresentacaoActivity extends AppCompatActivity {

    private LinearLayout lnLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        lnLayout = findViewById(R.id.idLayoutApresentacao);

        lnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trocarTela = new Intent(ApresentacaoActivity.this,HomeActivity.class);
                startActivity(trocarTela);
                finish();
            }
        });

    }
}