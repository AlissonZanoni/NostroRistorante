package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import alisson.zanoni.nostroristorante.model.Usuario;
import alisson.zanoni.nostroristorante.repository.UsuarioFireBaseRepository;

public class PerfilActivity extends AppCompatActivity {

    TextView textNome, textSobrenome, textDataNascimento, textTelefone, textEmail;
    UsuarioFireBaseRepository fireBaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        fireBaseRepository = new UsuarioFireBaseRepository();
        iniciarComponentes();
    }

    @Override
    protected void onStart() {
        super.onStart();

        textNome.setText("");
        textSobrenome.setText("");
        textDataNascimento.setText("");
        textTelefone.setText("");
        textEmail.setText("");
        String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();

        fireBaseRepository.getUsuario(idUsuario, new UsuarioFireBaseRepository.callbackUsuario() {
            @Override
            public void onCallback(Usuario usuario) {
                if(usuario != null) {
                    textNome.setText(usuario.getNome());
                    textSobrenome.setText(usuario.getSobrenome());
                    textDataNascimento.setText(usuario.getDataNascimento());
                    textTelefone.setText(usuario.getTelefone());
                    textEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                } else {
                    Toast.makeText(PerfilActivity.this, "Falha ao buscar os dados do usuário", Toast.LENGTH_SHORT).show();
                    trocarDeTela(PerfilActivity.class);
                }
            }
        });
    }

    public void trocarDeTela(Class tela){
        Intent trocarTela = new Intent(PerfilActivity.this, tela);
        startActivity(trocarTela);
        finish();
    }

    public void iniciarComponentes() {
        textNome = findViewById(R.id.nome);
        textSobrenome = findViewById(R.id.sobrenome);
        textDataNascimento = findViewById(R.id.dataNascimento);
        textTelefone = findViewById(R.id.telefone);
        textEmail = findViewById(R.id.email);
    }
}