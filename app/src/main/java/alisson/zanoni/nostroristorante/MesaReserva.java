package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import alisson.zanoni.nostroristorante.adapter.ComidaAdapter;
import alisson.zanoni.nostroristorante.adapter.MesaAdapter;
import alisson.zanoni.nostroristorante.model.Comida;
import alisson.zanoni.nostroristorante.model.Mesa;
import alisson.zanoni.nostroristorante.repository.MesaFireBaseRepository;

public class MesaReserva extends AppCompatActivity {

    private ListView listaMesa;
    private ArrayList<Mesa> list = new ArrayList<>();
    private MesaAdapter adapter;
    MesaFireBaseRepository mesaFireBaseRepository;
    public static final int telaMesaReservaParams = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa_reserva);

        listaMesa = findViewById(R.id.listaMesas);
        mesaFireBaseRepository = new MesaFireBaseRepository();

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        mesaFireBaseRepository.getMesas(new MesaFireBaseRepository.callbackMesas() {

            @Override
            public void onCallback(List<Mesa> mesas) {
                for(Mesa mesa : mesas) {

                    if(mesa.getStatus().equals(false)){
                        list.add(mesa);
                    }
                }

                adapter = new MesaAdapter(MesaReserva.this, list);
                listaMesa.setAdapter(adapter);
            }
        });

        listaMesa.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Mesa mesaEscolhida = (Mesa) listaMesa.getItemAtPosition(i);
                if(mesaEscolhida.getStatus()) {
                    Toast.makeText(MesaReserva.this, "Esta mesa já está reservada, por favor selecione outra.", Toast.LENGTH_SHORT).show();
                    return;
                }
                params.putInt("mesa", mesaEscolhida.getNumeracao());
                Intent trocarTela = new Intent(MesaReserva.this,ConfirmarReservaActivity.class);
                trocarTela.putExtras(params);
                startActivityForResult(trocarTela, telaMesaReservaParams);
                finish();
            }
        });
    }
}