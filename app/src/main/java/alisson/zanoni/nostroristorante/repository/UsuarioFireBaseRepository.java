package alisson.zanoni.nostroristorante.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import alisson.zanoni.nostroristorante.model.Usuario;

public class UsuarioFireBaseRepository {

    private DatabaseReference databaseReference;
    private FirebaseDatabase db;

    public UsuarioFireBaseRepository() {
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Usuario.class.getSimpleName());
    }

    public interface MyCallback {
        void onCallback(String nomeUsuario);
    }

    public void getNomeUsuario(String idUsuario, MyCallback myCallback) {
        databaseReference.child(idUsuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario user = snapshot.getValue(Usuario.class);
                if(user != null) {
                    myCallback.onCallback(user.getNome());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public Task<Void> add(Usuario usuario) {
        String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return databaseReference.child(idUsuario).setValue(usuario);
    }
}
