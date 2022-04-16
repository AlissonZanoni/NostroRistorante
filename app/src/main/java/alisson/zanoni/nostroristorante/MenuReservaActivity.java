package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuReservaActivity extends AppCompatActivity {

    Button btnReservar, btnConsultarReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reserva);

        btnReservar = findViewById(R.id.BtnReservar);
        btnConsultarReserva = findViewById(R.id.BtnConsultarReserva);

        btnReservar.setOnClickListener(view -> trocarDeTela(ReservaActivity.class));
        btnConsultarReserva.setOnClickListener(view -> trocarDeTela(ConsultarReservaActivity.class));
    }

    public void trocarDeTela(Class tela){
        Intent trocarTela = new Intent(MenuReservaActivity.this, tela);
        startActivity(trocarTela);
        finish();
    }
}