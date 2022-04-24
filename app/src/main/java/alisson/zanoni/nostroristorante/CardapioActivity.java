package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class CardapioActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout btnEntrada, btnAcompanhamento, btnMassa, btnRisoto, btnCarne, btnDoce, btnBebida, btnVinho, btnCerveja;
    public static final int telaParams = 1;
    Bundle params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        btnEntrada = findViewById(R.id.BtnEntrada);
        btnAcompanhamento = findViewById(R.id.BtnAcompanhamento);
        btnMassa = findViewById(R.id.BtnMassa);
        btnRisoto = findViewById(R.id.BtnRisoto);
        btnCarne = findViewById(R.id.BtnCarne);
        btnDoce = findViewById(R.id.BtnDoce);
        btnBebida = findViewById(R.id.BtnBebida);
        btnVinho = findViewById(R.id.BtnVinho);
        btnCerveja = findViewById(R.id.BtnCerveja);
        params = new Bundle();

        btnEntrada.setOnClickListener(this);
        btnAcompanhamento.setOnClickListener(this);
        btnMassa.setOnClickListener(this);
        btnRisoto.setOnClickListener(this);
        btnCarne.setOnClickListener(this);
        btnDoce.setOnClickListener(this);
        btnBebida.setOnClickListener(this);
        btnVinho.setOnClickListener(this);
        btnCerveja.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String tipo = "";
        switch (v.getId()){
            case R.id.BtnEntrada:
                tipo = "Entrada";
                break;
            case R.id.BtnAcompanhamento:
                tipo = "Acompanhamento";
                break;
            case R.id.BtnMassa:
                tipo = "Massa";
                break;
            case R.id.BtnRisoto:
                tipo = "Risoto";
                break;
            case R.id.BtnCarne:
                tipo = "Carne";
                break;
            case R.id.BtnDoce:
                tipo = "Doce";
                break;
            case R.id.BtnBebida:
                tipo = "Bebida";
                break;
            case R.id.BtnVinho:
                tipo = "Vinho";
                break;
            case R.id.BtnCerveja:
                tipo = "Cerveja";
                break;
        }
        params.putString("tipo", tipo);
        Intent trocarTela = new Intent(CardapioActivity.this,CardapioTipoActivity.class);
        trocarTela.putExtras(params);
        startActivityForResult(trocarTela, telaParams);
        finish();
    }
}