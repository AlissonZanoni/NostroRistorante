package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import alisson.zanoni.nostroristorante.model.Reserva;
import alisson.zanoni.nostroristorante.repository.ReservaFireBaseRepository;

public class ConsultarReservaActivity extends AppCompatActivity {

    private ListView listaReservas;
    TextView listaVaziaConsultaReserva;
    ReservaFireBaseRepository reservaFireBaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_reserva);

        listaReservas = findViewById(R.id.listaReservas);
        listaVaziaConsultaReserva = findViewById(R.id.listaVaziaConsultaReserva);

        reservaFireBaseRepository = new ReservaFireBaseRepository();
        String idUsuario =  FirebaseAuth.getInstance().getCurrentUser().getUid();

        reservaFireBaseRepository.getReservasPorUsuario(idUsuario, new ReservaFireBaseRepository.callbackReservaPorUsuario() {
            @Override
            public void onCallback(List<Reserva> reservas) {
                if(reservas.size() == 0){
                    listaVaziaConsultaReserva.setText("Você não possui reservas.");
                } else {
                    listaVaziaConsultaReserva.setText("Reservas ativas:");
                    ArrayAdapter<Reserva> adapter = new ArrayAdapter<Reserva>(ConsultarReservaActivity.this, android.R.layout.simple_list_item_1, reservas);
                    listaReservas.setAdapter(adapter);
                }
            }
        });
    }
}