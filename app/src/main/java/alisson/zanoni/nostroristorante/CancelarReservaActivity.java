package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import alisson.zanoni.nostroristorante.model.Reserva;
import alisson.zanoni.nostroristorante.repository.MesaFireBaseRepository;
import alisson.zanoni.nostroristorante.repository.ReservaFireBaseRepository;

public class CancelarReservaActivity extends AppCompatActivity {

    private ListView listaReservasCancelar;
    ReservaFireBaseRepository reservaFireBaseRepository;
    MesaFireBaseRepository mesaFireBaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_reserva);

        listaReservasCancelar = findViewById(R.id.listaReservasCancelar);
        mesaFireBaseRepository = new MesaFireBaseRepository();

        reservaFireBaseRepository = new ReservaFireBaseRepository();
        String idUsuario =  FirebaseAuth.getInstance().getCurrentUser().getUid();

        reservaFireBaseRepository.getReservasPorUsuario(idUsuario, new ReservaFireBaseRepository.callbackReservaPorUsuario() {
            @Override
            public void onCallback(List<Reserva> reservas) {
                ArrayAdapter<Reserva> adapter = new ArrayAdapter<Reserva>(CancelarReservaActivity.this, android.R.layout.simple_list_item_1, reservas);
                listaReservasCancelar.setAdapter(adapter);
            }
        });

        listaReservasCancelar.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Reserva reservaEscolhida = (Reserva) listaReservasCancelar.getItemAtPosition(i);
                reservaFireBaseRepository.deletarReserva(idUsuario, reservaEscolhida.getNumeracaoMesa());
                mesaFireBaseRepository.updateStatus(reservaEscolhida.getNumeracaoMesa(), false);
                Toast.makeText(CancelarReservaActivity.this, "Reserva deletada com sucesso!", Toast.LENGTH_SHORT).show();
                Intent trocarTela = new Intent(CancelarReservaActivity.this, MenuReservaActivity.class);
                startActivity(trocarTela);
                finish();
            }
        });
    }
}