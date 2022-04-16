package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuReservaActivity extends AppCompatActivity {

    Button btnReservar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reserva);

        btnReservar = findViewById(R.id.BtnReservar);

        btnReservar.setOnClickListener(view -> trocarDeTela(ReservaActivity.class));
    }

    public void trocarDeTela(Class tela){
        Intent trocarTela = new Intent(MenuReservaActivity.this, tela);
        startActivity(trocarTela);
        finish();
    }
}