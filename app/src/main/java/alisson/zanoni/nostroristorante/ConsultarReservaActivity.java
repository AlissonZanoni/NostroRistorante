package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import alisson.zanoni.nostroristorante.model.Reserva;
import alisson.zanoni.nostroristorante.repository.ReservaFireBaseRepository;

public class ConsultarReservaActivity extends AppCompatActivity {

    private ListView listaReservas;
    ReservaFireBaseRepository reservaFireBaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_reserva);

        listaReservas = findViewById(R.id.listaReservas);

        reservaFireBaseRepository = new ReservaFireBaseRepository();
        String idUsuario =  FirebaseAuth.getInstance().getCurrentUser().getUid();

        reservaFireBaseRepository.getReservasPorUsuario(idUsuario, new ReservaFireBaseRepository.callbackReservaPorUsuario() {
            @Override
            public void onCallback(List<Reserva> reservas) {
                ArrayAdapter<Reserva> adapter = new ArrayAdapter<Reserva>(ConsultarReservaActivity.this, android.R.layout.simple_list_item_1, reservas);
                listaReservas.setAdapter(adapter);
            }
        });
    }
}