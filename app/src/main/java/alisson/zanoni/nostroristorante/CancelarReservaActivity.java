package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import alisson.zanoni.nostroristorante.adapter.ComidaAdapter;
import alisson.zanoni.nostroristorante.adapter.ReservaAdapter;
import alisson.zanoni.nostroristorante.model.Comida;
import alisson.zanoni.nostroristorante.model.Reserva;
import alisson.zanoni.nostroristorante.repository.MesaFireBaseRepository;
import alisson.zanoni.nostroristorante.repository.ReservaFireBaseRepository;

public class CancelarReservaActivity extends AppCompatActivity {


    private ListView listaReservasCancelar;
    private ReservaAdapter adapter;
    private ArrayList<Reserva> list = new ArrayList<>();

    TextView listaVaziaCancelarReserva;
    ReservaFireBaseRepository reservaFireBaseRepository;
    MesaFireBaseRepository mesaFireBaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_reserva);

        listaReservasCancelar = findViewById(R.id.listaReservasCancelar);
        mesaFireBaseRepository = new MesaFireBaseRepository();
        listaVaziaCancelarReserva = findViewById(R.id.listaVaziaConsultaReserva);

        reservaFireBaseRepository = new ReservaFireBaseRepository();
        String idUsuario =  FirebaseAuth.getInstance().getCurrentUser().getUid();

        reservaFireBaseRepository.getReservasPorUsuario(idUsuario, new ReservaFireBaseRepository.callbackReservaPorUsuario() {

            @Override
            public void onCallback(List<Reserva> reservas) {
                if(reservas.size() == 0){
                    listaVaziaCancelarReserva.setText("Você não possui reservas.");
                } else {
                    for(Reserva reserva : reservas) {
                        list.add(reserva);
                    }
                    adapter = new ReservaAdapter(CancelarReservaActivity.this,list);
                    listaReservasCancelar.setAdapter(adapter);
                }
            }
        });

        listaReservasCancelar.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Reserva reservaEscolhida = (Reserva) listaReservasCancelar.getItemAtPosition(i);
                reservaFireBaseRepository.deletarReserva(idUsuario, reservaEscolhida);
                mesaFireBaseRepository.updateStatus(reservaEscolhida.getNumeracaoMesa(), false);
                Toast.makeText(CancelarReservaActivity.this, "Reserva deletada com sucesso!", Toast.LENGTH_SHORT).show();
                Intent trocarTela = new Intent(CancelarReservaActivity.this, MenuReservaActivity.class);
                startActivity(trocarTela);
                finish();
            }
        });
    }
}