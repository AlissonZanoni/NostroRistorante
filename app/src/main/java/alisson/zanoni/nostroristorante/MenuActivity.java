package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btnCardapio, btnReservaCancelamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnCardapio = findViewById(R.id.BtnCardapio);
        btnReservaCancelamento = findViewById(R.id.BtnReservaCancelamento);

        btnCardapio.setOnClickListener(view -> trocarDeTela(CardapioActivity.class));
        btnReservaCancelamento.setOnClickListener(view -> trocarDeTela(MenuReservaActivity.class));
    }

    public void trocarDeTela(Class tela){
        Intent trocarTela = new Intent(MenuActivity.this, tela);
        startActivity(trocarTela);
        finish();
    }
}