package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

            String requestDataReserva = dataReserva.getText().toString();
            String requestQuantidadePessoas = qtdPessoas.getText().toString();
            int requestQuantidadePessoasConvertido;
            String requestPrecisaCadeiraBebe = cadeiraBebe.getText().toString();
            Boolean requestPrecisaCadeiraBebeConvertido;
            String requestObservacoes = observacoes.getText().toString();

            if(requestDataReserva.isEmpty()){
                Toast.makeText(ReservaActivity.this, "Digite a data da reserva.", Toast.LENGTH_SHORT).show();
                return;
            }

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date dataFormatada;
            try {
                dataFormatada = formato.parse(requestDataReserva);
            } catch (ParseException e) {
                Toast.makeText(ReservaActivity.this, "Formato de data inválido. Digite (dd/MM/yyyy hh:mm)", Toast.LENGTH_SHORT).show();
                return;
            }

            Date dataAtual = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dataAtual);
            c.add(Calendar.HOUR, -3);
            dataAtual = c.getTime();

            if(dataFormatada.before(dataAtual)){
                Toast.makeText(ReservaActivity.this, "A data da reserva precisa ser maior que a data atual", Toast.LENGTH_SHORT).show();
                return;
            }

            if(requestQuantidadePessoas.isEmpty()){
                Toast.makeText(ReservaActivity.this, "Digite a quantidade de pessoas.", Toast.LENGTH_SHORT).show();
                return;
            } else {
                try {
                    requestQuantidadePessoasConvertido = Integer.parseInt(qtdPessoas.getText().toString());
                } catch (NumberFormatException  e) {
                    Toast.makeText(ReservaActivity.this, "Formato de quantidade pessoas inválido. Digite um número", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if(requestPrecisaCadeiraBebe.isEmpty()){
                Toast.makeText(ReservaActivity.this, "Digite se é necessário cadeira de bebê.", Toast.LENGTH_SHORT).show();
                return;
            } else {
                requestPrecisaCadeiraBebeConvertido = Boolean.parseBoolean(cadeiraBebe.getText().toString());
            }

            params.putString("data", requestDataReserva);
            params.putInt("qtdPessoas", requestQuantidadePessoasConvertido);
            params.putBoolean("precisaCadeiraBebe", requestPrecisaCadeiraBebeConvertido);
            params.putString("observacoes", requestObservacoes);
            Intent trocarTela = new Intent(ReservaActivity.this,MesaReserva.class);
            trocarTela.putExtras(params);
            startActivityForResult(trocarTela, telaReservaParams);
            finish();
        });
    }
}