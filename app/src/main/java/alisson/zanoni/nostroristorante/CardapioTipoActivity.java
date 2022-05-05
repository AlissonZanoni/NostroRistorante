package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import alisson.zanoni.nostroristorante.adapter.ComidaAdapter;
import alisson.zanoni.nostroristorante.model.Comida;
import alisson.zanoni.nostroristorante.repository.ComidaFireBaseRepository;

public class CardapioTipoActivity extends AppCompatActivity {

    private ListView listaComida;
    private ArrayList<Comida> list = new ArrayList<>();
    private ComidaAdapter adapter;
    ComidaFireBaseRepository comidaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio_tipo);

        listaComida = findViewById(R.id.listaComidas);

        comidaRepository = new ComidaFireBaseRepository();
        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        String nome = params.getString("tipo");

        comidaRepository.getComidasPorTipo(nome, new ComidaFireBaseRepository.callbackComidasPorTipo() {

            @Override
            public void onCallback(List<Comida> comidas) {
                for(Comida comida : comidas) {
                    list.add(comida);
                }
                adapter = new ComidaAdapter(CardapioTipoActivity.this, list);
                listaComida.setAdapter(adapter);
            }
        });
    }
}