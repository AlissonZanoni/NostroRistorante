package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import alisson.zanoni.nostroristorante.repository.UsuarioFireBaseRepository;

public class HomeActivity extends AppCompatActivity{

    TextView textUsuarioLogado;
    Button btnLogout;
    UsuarioFireBaseRepository fireBaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fireBaseRepository = new UsuarioFireBaseRepository();
        textUsuarioLogado = findViewById(R.id.usuarioLogado);
        btnLogout = findViewById(R.id.idBtnLogout);
        btnLogout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent logout = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(logout);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        textUsuarioLogado.setText("");
        String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();

        fireBaseRepository.getNomeUsuario(idUsuario, new UsuarioFireBaseRepository.MyCallback() {
            @Override
            public void onCallback(String nomeUsuario) {
                if(nomeUsuario != null)
                    textUsuarioLogado.setText(" "+nomeUsuario);
                else {
                    Toast.makeText(HomeActivity.this, "Falha ao buscar os dados do usuário", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                    Intent logout = new Intent(HomeActivity.this,LoginActivity.class);
                    startActivity(logout);
                    finish();
                }
            }
        });
    }
}