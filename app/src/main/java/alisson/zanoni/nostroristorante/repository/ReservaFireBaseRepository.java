package alisson.zanoni.nostroristorante.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
}
