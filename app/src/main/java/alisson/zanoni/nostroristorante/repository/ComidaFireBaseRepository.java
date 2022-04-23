package alisson.zanoni.nostroristorante.repository;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import alisson.zanoni.nostroristorante.model.Comida;

public class ComidaFireBaseRepository {

    private DatabaseReference databaseReference;
    private FirebaseDatabase db;

    public ComidaFireBaseRepository() {
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Comida.class.getSimpleName());
    }

    public void getComidas() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Comida> comidas = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Comida comida = new Comida();
                    comida.setNome(dataSnapshot.child("Nome").getValue().toString());
                    comida.setDescricao(dataSnapshot.child("Descricao").getValue().toString());
                    comida.setPreco(Double.parseDouble(dataSnapshot.child("Preco").getValue().toString()));
                    comida.setTipo(dataSnapshot.child("Tipo").getValue().toString());
                    comidas.add(comida);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public interface callbackComidasPorTipo {
        void onCallback(List<Comida> comidas);
    }

    public void getComidasPorTipo(String tipo, callbackComidasPorTipo myCallback) {
        databaseReference.orderByChild("Tipo").equalTo(tipo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Comida> comidas = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Comida comida = new Comida();
                    comida.setNome(dataSnapshot.child("Nome").getValue().toString());
                    comida.setDescricao(dataSnapshot.child("Descricao").getValue().toString());
                    comida.setPreco(Double.parseDouble(dataSnapshot.child("Preco").getValue().toString()));
                    comida.setTipo(dataSnapshot.child("Tipo").getValue().toString());
                    comidas.add(comida);
                }
                myCallback.onCallback(comidas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
