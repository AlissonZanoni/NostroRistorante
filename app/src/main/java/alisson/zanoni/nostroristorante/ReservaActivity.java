package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReservaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    EditText etDate;
    DatePickerDialog.OnDateSetListener setListener;
    Spinner spinner;

    String[] fases = {"Sim","Não"};

    Button btnEscolherMesa;
    EditText dataReserva, qtdPessoas, cadeiraBebe, observacoes;
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

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter aa = ArrayAdapter.createFromResource(
                this,R.array.Spinner_Itens,
                R.layout.spinner_dropdown_layout);

        aa.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinner.setAdapter(aa);


//        btnEscolherMesa = findViewById(R.id.idBtnEscolherMesa);
//        dataReserva = findViewById(R.id.idDataReserva);
//        qtdPessoas = findViewById(R.id.idQtdPessoas);
//        cadeiraBebe = findViewById(R.id.idCadeiraBebe);
//        observacoes = findViewById(R.id.idObservacoes);
//        params = new Bundle();
//
//        btnEscolherMesa.setOnClickListener(view -> {
//
//            String requestDataReserva = dataReserva.getText().toString();
//            String requestQuantidadePessoas = qtdPessoas.getText().toString();
//            int requestQuantidadePessoasConvertido;
//            String requestPrecisaCadeiraBebe = cadeiraBebe.getText().toString();
//            Boolean requestPrecisaCadeiraBebeConvertido;
//            String requestObservacoes = observacoes.getText().toString();
//
//            if(requestDataReserva.isEmpty()){
//                Toast.makeText(ReservaActivity.this, "Digite a data da reserva.", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//            Date dataFormatada;
//            try {
//                dataFormatada = formato.parse(requestDataReserva);
//            } catch (ParseException e) {
//                Toast.makeText(ReservaActivity.this, "Formato de data inválido. Digite (dd/MM/yyyy hh:mm)", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            Date dataAtual = new Date();
//            Calendar c = Calendar.getInstance();
//            c.setTime(dataAtual);
//            c.add(Calendar.HOUR, -3);
//            dataAtual = c.getTime();
//
//            if(dataFormatada.before(dataAtual)){
//                Toast.makeText(ReservaActivity.this, "A data da reserva precisa ser maior que a data atual", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if(requestQuantidadePessoas.isEmpty()){
//                Toast.makeText(ReservaActivity.this, "Digite a quantidade de pessoas.", Toast.LENGTH_SHORT).show();
//                return;
//            } else {
//                try {
//                    requestQuantidadePessoasConvertido = Integer.parseInt(qtdPessoas.getText().toString());
//                } catch (NumberFormatException  e) {
//                    Toast.makeText(ReservaActivity.this, "Formato de quantidade pessoas inválido. Digite um número", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//
//            if(requestPrecisaCadeiraBebe.isEmpty()){
//                Toast.makeText(ReservaActivity.this, "Digite se é necessário cadeira de bebê.", Toast.LENGTH_SHORT).show();
//                return;
//            } else {
//                requestPrecisaCadeiraBebeConvertido = Boolean.parseBoolean(cadeiraBebe.getText().toString());
//            }
//
//            params.putString("data", requestDataReserva);
//            params.putInt("qtdPessoas", requestQuantidadePessoasConvertido);
//            params.putBoolean("precisaCadeiraBebe", requestPrecisaCadeiraBebeConvertido);
//            params.putString("observacoes", requestObservacoes);
//            Intent trocarTela = new Intent(ReservaActivity.this,MesaReserva.class);
//            trocarTela.putExtras(params);
//            startActivityForResult(trocarTela, telaReservaParams);
//            finish();
//        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),fases[i], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}