package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import alisson.zanoni.nostroristorante.model.Mesa;
import alisson.zanoni.nostroristorante.repository.MesaFireBaseRepository;

public class MesaReserva extends AppCompatActivity {

    private ListView listaAndroid;
    MesaFireBaseRepository mesaFireBaseRepository;
    public static final int telaMesaReservaParams = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa_reserva);

        listaAndroid = findViewById(R.id.listaMesas);
        mesaFireBaseRepository = new MesaFireBaseRepository();

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        mesaFireBaseRepository.getMesas(new MesaFireBaseRepository.callbackMesas() {

            @Override
            public void onCallback(List<Mesa> mesas) {
                ArrayAdapter<Mesa> adapter = new ArrayAdapter<Mesa>(MesaReserva.this, android.R.layout.simple_list_item_1, mesas);
                listaAndroid.setAdapter(adapter);
            }
        });

        listaAndroid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Mesa mesaEscolhida = (Mesa) listaAndroid.getItemAtPosition(i);
                params.putInt("mesa", mesaEscolhida.getNumeracao());
                Intent trocarTela = new Intent(MesaReserva.this,ConfirmarReservaActivity.class);
                trocarTela.putExtras(params);
                startActivityForResult(trocarTela, telaMesaReservaParams);
                finish();
            }
        });
    }
}