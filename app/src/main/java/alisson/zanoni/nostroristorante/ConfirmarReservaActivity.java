package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import alisson.zanoni.nostroristorante.model.Reserva;
import alisson.zanoni.nostroristorante.repository.MesaFireBaseRepository;
import alisson.zanoni.nostroristorante.repository.ReservaFireBaseRepository;

public class ConfirmarReservaActivity extends AppCompatActivity {

    ReservaFireBaseRepository reservaFireBaseRepository;
    MesaFireBaseRepository mesaFireBaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_reserva);

        reservaFireBaseRepository = new ReservaFireBaseRepository();
        mesaFireBaseRepository = new MesaFireBaseRepository();

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        String data = params.getString("data");
        int qtdPessoas = params.getInt("qtdPessoas");
        Boolean cadeiraBebe = params.getBoolean("precisaCadeiraBebe");
        String observacoes = params.getString("observacoes");
        int mesa = params.getInt("mesa");
        String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String idReserva = idUsuario + "-" + mesa;
        Reserva reserva = new Reserva(idReserva, data, qtdPessoas, cadeiraBebe, observacoes, idUsuario, mesa);
        reservaFireBaseRepository.add(reserva).addOnSuccessListener(suc -> {

            mesaFireBaseRepository.updateStatus(mesa, true);

            Toast.makeText(ConfirmarReservaActivity.this, "Reserva cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
            trocarDeTela(MenuReservaActivity.class);
        }).addOnFailureListener(er -> {
            Toast.makeText(ConfirmarReservaActivity.this, "Falha ao cadastrar reserva.", Toast.LENGTH_SHORT).show();
        });
    }

    public void trocarDeTela(Class tela){
        Intent trocarTela = new Intent(ConfirmarReservaActivity.this,tela);
        startActivity(trocarTela);
        finish();
    }
}