package alisson.zanoni.nostroristorante.repository;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import alisson.zanoni.nostroristorante.model.Mesa;

public class MesaFireBaseRepository {

    private DatabaseReference databaseReference;
    private FirebaseDatabase db;

    public MesaFireBaseRepository() {
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Mesa.class.getSimpleName());
    }

    public interface callbackMesas {
        void onCallback(List<Mesa> mesas);
    }

    public void getMesas(callbackMesas myCallback) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Mesa> mesas = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Mesa mesa = new Mesa();
                    mesa.setNumeracao(Integer.parseInt(dataSnapshot.child("Numeracao").getValue().toString()));
                    mesa.setQuantidadeCadeiras(Integer.parseInt(dataSnapshot.child("Cadeiras").getValue().toString()));
                    mesa.setDescricao(dataSnapshot.child("Descricao").getValue().toString());
                    mesa.setStatus(Boolean.parseBoolean(dataSnapshot.child("Status").getValue().toString()));
                    mesas.add(mesa);
                }
                myCallback.onCallback(mesas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public interface callbackMesa {
        void onCallback(Mesa mesa);
    }

    public void getMesaPorNumeracao(int numeracao, MesaFireBaseRepository.callbackMesa myCallback) {
        databaseReference.child(Integer.toString(numeracao)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Mesa mesa = snapshot.getValue(Mesa.class);
                if(mesa != null) {
                    myCallback.onCallback(mesa);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateStatus(int numeracao, Boolean status) {

        HashMap mesaUpdate = new HashMap();
        mesaUpdate.put("Status", status);

        databaseReference.child(Integer.toString(numeracao)).updateChildren(mesaUpdate);
    }
}
