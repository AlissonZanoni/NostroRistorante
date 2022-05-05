package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReservaActivity extends AppCompatActivity {

    EditText etDate;
    DatePickerDialog.OnDateSetListener setListener;

    Button btnEscolherMesa;
    EditText dataReserva, qtdPessoas, observacoes;
    Bundle params;
    public static final int telaReservaParams = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        etDate = findViewById(R.id.ed_date);

        Calendar calendar = Calendar.getInstance();
        final int ano = calendar.get(Calendar.YEAR);
        final int mes = calendar.get(Calendar.MONTH);
        final int dia = calendar.get(Calendar.DAY_OF_MONTH);


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ReservaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        mes = mes+1;
                        String date  = dia+"/"+mes+"/"+ano;
                        etDate.setText(date);
                    }
                },ano,mes,dia);
                datePickerDialog.show();
            }
        });

        btnEscolherMesa = findViewById(R.id.idBtnEscolherMesa);
        dataReserva = findViewById(R.id.ed_date);
        qtdPessoas = findViewById(R.id.confirmarQtdPessoas);
        observacoes = findViewById(R.id.observacao);
        params = new Bundle();

        btnEscolherMesa.setOnClickListener(view -> {

            String requestDataReserva = dataReserva.getText().toString();
            String requestQuantidadePessoas = qtdPessoas.getText().toString();
            int requestQuantidadePessoasConvertido;
            String requestObservacoes = observacoes.getText().toString();

            if(requestDataReserva.isEmpty()){
                Toast.makeText(ReservaActivity.this, "Digite a data da reserva.", Toast.LENGTH_SHORT).show();
                return;
            }

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataFormatada;
            try {
                dataFormatada = formato.parse(requestDataReserva);
            } catch (ParseException e) {
                Toast.makeText(ReservaActivity.this, "Formato de data inválido. Digite (dd/MM/yyyy)", Toast.LENGTH_SHORT).show();
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

            params.putString("data", requestDataReserva);
            params.putInt("qtdPessoas", requestQuantidadePessoasConvertido);
            params.putString("observacoes", requestObservacoes);
            Intent trocarTela = new Intent(ReservaActivity.this,MesaReserva.class);
            trocarTela.putExtras(params);
            startActivityForResult(trocarTela, telaReservaParams);
            finish();
        });
    }
}