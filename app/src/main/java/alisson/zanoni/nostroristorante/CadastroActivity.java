package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import alisson.zanoni.nostroristorante.dao.UsuarioDAO;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome, editSobrenome, editDataNascimento, editEmail, editSenha, editConfirmarSenha;
    UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dao = new UsuarioDAO(CadastroActivity.this);

        editNome = findViewById(R.id.idNomeCadastro);
        editSobrenome = findViewById(R.id.idSobrenomeCadastro);
        editDataNascimento = findViewById(R.id.idDateNascimentoCadastro);
        editEmail = findViewById(R.id.idEmailCadastro);
        editSenha = findViewById(R.id.idSenhaCadastro);
        editConfirmarSenha = findViewById(R.id.idConfirmarSenhaCadastro);

        Button btnCadastrar = findViewById(R.id.idBtnCadastrar);
        btnCadastrar.setOnClickListener(view -> {

            String nome = editNome.getText().toString();
            String sobrenome = editSobrenome.getText().toString();
            String dataNascimento = editDataNascimento.getText().toString();
            String email = editEmail.getText().toString();
            String senha = editSenha.getText().toString();
            String confirmarSenha = editConfirmarSenha.getText().toString();

            if(!nome.isEmpty() && !sobrenome.isEmpty() && !dataNascimento.isEmpty() && !email.isEmpty() && !senha.isEmpty() && !confirmarSenha.isEmpty()) {
                if(validaEmail(email)) {
                    if(senha.equals(confirmarSenha)) {
                        dao.Insert(nome, sobrenome, dataNascimento, email, senha);
                        Toast.makeText(CadastroActivity.this, "Usuário salvo com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(CadastroActivity.this,LoginActivity.class);
                        startActivity(login);
                        finish();
                    } else {
                        Toast.makeText(CadastroActivity.this, "As senhas precisam ser iguais.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "Email já cadastrado.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(CadastroActivity.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean validaEmail(String email) {
        ArrayList<String> emailsCadastrados = dao.SelectEmail();
        return !emailsCadastrados.contains(email);
    }

}