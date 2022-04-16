package alisson.zanoni.nostroristorante.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import alisson.zanoni.nostroristorante.model.Reserva;

public class ReservaFireBaseRepository {
    private DatabaseReference databaseReference;
    private FirebaseDatabase db;

    public ReservaFireBaseRepository() {
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Reserva.class.getSimpleName());
    }

    public Task<Void> add(Reserva reserva) {
        return databaseReference.push().setValue(reserva);
    }

    public interface callbackReservaPorUsuario {
        void onCallback(List<Reserva> reservas);
    }

    public void getReservasPorUsuario(String idUsuario, ReservaFireBaseRepository.callbackReservaPorUsuario myCallback) {
        databaseReference.orderByChild("idUsuario").equalTo(idUsuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Reserva> reservas = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Reserva reserva = new Reserva();
                    reserva.setDataReserva(dataSnapshot.child("dataReserva").getValue().toString());
                    reserva.setIdUsuario(dataSnapshot.child("idUsuario").getValue().toString());
                    reserva.setNumeracaoMesa(Integer.parseInt(dataSnapshot.child("numeracaoMesa").getValue().toString()));
                    reserva.setObservacoes(dataSnapshot.child("observacoes").getValue().toString());
                    reserva.setPrecisaCadeirinhaBebe(Boolean.parseBoolean(dataSnapshot.child("precisaCadeirinhaBebe").getValue().toString()));
                    reserva.setQuantidadePessoas(Integer.parseInt(dataSnapshot.child("quantidadePessoas").getValue().toString()));
                    reservas.add(reserva);
                }
                myCallback.onCallback(reservas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void deletarReserva(String idUsuario, int numeracaoMesa) {
        databaseReference.orderByChild("idReserva").equalTo(idUsuario+"-"+numeracaoMesa).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
