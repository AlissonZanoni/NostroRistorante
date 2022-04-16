package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ReservaActivity extends AppCompatActivity {

    Button btnEscolherMesa;
    EditText dataReserva, qtdPessoas, cadeiraBebe, observacoes;
    Bundle params;
    public static final int telaReservaParams = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        btnEscolherMesa = findViewById(R.id.idBtnEscolherMesa);
        dataReserva = findViewById(R.id.idDataReserva);
        qtdPessoas = findViewById(R.id.idQtdPessoas);
        cadeiraBebe = findViewById(R.id.idCadeiraBebe);
        observacoes = findViewById(R.id.idObservacoes);
        params = new Bundle();

        btnEscolherMesa.setOnClickListener(view -> {

            params.putString("data", dataReserva.getText().toString());
            params.putInt("qtdPessoas", Integer.parseInt(qtdPessoas.getText().toString()));
            params.putBoolean("precisaCadeiraBebe", Boolean.parseBoolean(cadeiraBebe.getText().toString()));
            params.putString("observacoes", observacoes.getText().toString());
            Intent trocarTela = new Intent(ReservaActivity.this,MesaReserva.class);
            trocarTela.putExtras(params);
            startActivityForResult(trocarTela, telaReservaParams);
            finish();
        });
    }
}