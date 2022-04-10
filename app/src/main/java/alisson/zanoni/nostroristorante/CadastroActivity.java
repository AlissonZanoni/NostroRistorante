package alisson.zanoni.nostroristorante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import alisson.zanoni.nostroristorante.repository.UsuarioFireBaseRepository;
import alisson.zanoni.nostroristorante.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome, editSobrenome, editDataNascimento, editTelefone, editEmail, editSenha, editConfirmarSenha;
    UsuarioFireBaseRepository fireBaseRepository;
    String[] mensagens = {"Preencha todos os campos",
                          "As senhas precisam ser iguais"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        fireBaseRepository = new UsuarioFireBaseRepository();

        iniciarComponentes();

        Button btnCadastrar = findViewById(R.id.idBtnCadastrar);
        btnCadastrar.setOnClickListener(view -> {

            String nome = editNome.getText().toString();
            String sobrenome = editSobrenome.getText().toString();
            String dataNascimento = editDataNascimento.getText().toString();
            String telefone = editTelefone.getText().toString();
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString().trim();
            String confirmarSenha = editConfirmarSenha.getText().toString();

            if(nome.isEmpty() || sobrenome.isEmpty() || dataNascimento.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
                Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            } else if(!senha.equals(confirmarSenha)){
                Snackbar snackbar = Snackbar.make(view, mensagens[1], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            } else {
                CadastrarUsuario(view, nome, sobrenome, dataNascimento, telefone, email, senha);
            }
        });
    }

    private void SalvarDadosUsuario(String nome, String sobrenome, String dataNascimento, String telefone) {

        Usuario usuario = new Usuario(nome, sobrenome, dataNascimento, telefone);

        fireBaseRepository.add(usuario).addOnSuccessListener(suc -> {
            Log.d("db", "Sucesso ao salvar os dados");
        }).addOnFailureListener(er-> {
            Log.d("db_error", "Falha ao salvar os dados");
        });
    }

    private void iniciarComponentes() {
        editNome = findViewById(R.id.idNomeCadastro);
        editSobrenome = findViewById(R.id.idSobrenomeCadastro);
        editDataNascimento = findViewById(R.id.idDateNascimentoCadastro);
        editTelefone = findViewById(R.id.idTelefoneCadastro);
        editEmail = findViewById(R.id.idEmailCadastro);
        editSenha = findViewById(R.id.idSenhaCadastro);
        editConfirmarSenha = findViewById(R.id.idConfirmarSenhaCadastro);
    }

    private void CadastrarUsuario(View view, String nome, String sobrenome, String dataNascimento, String telefone, String email, String senha) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    SalvarDadosUsuario(nome, sobrenome, dataNascimento, telefone);

                    Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();

                    Intent login = new Intent(CadastroActivity.this,LoginActivity.class);
                    startActivity(login);
                    finish();
                } else {
                    String erro;
                    try {
                        throw task.getException();
                    } catch(FirebaseAuthWeakPasswordException e) {
                        erro = "A senha precisa ter no mínimo 6 caracteres";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = "Este email já está cadastrado";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "Email inválido";
                    } catch (Exception e) {
                        erro = "Erro ao cadastrar o usuário";
                    }
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }
}