package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import alisson.zanoni.nostroristorante.model.Comida;
import alisson.zanoni.nostroristorante.repository.ComidaFireBaseRepository;

public class CardapioTipoActivity extends AppCompatActivity {

    private ListView listaAndroid;
    ComidaFireBaseRepository comidaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio_tipo);

        listaAndroid = findViewById(R.id.listaComidas);

        comidaRepository = new ComidaFireBaseRepository();
        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        String nome = params.getString("tipo");

        comidaRepository.getComidasPorTipo(nome, new ComidaFireBaseRepository.callbackComidasPorTipo() {

            @Override
            public void onCallback(List<Comida> comidas) {
                ArrayAdapter<Comida> adapter = new ArrayAdapter<Comida>(CardapioTipoActivity.this, android.R.layout.simple_list_item_1, comidas);
                listaAndroid.setAdapter(adapter);
            }
        });
    }
}