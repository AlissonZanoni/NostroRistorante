package alisson.zanoni.nostroristorante.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import alisson.zanoni.nostroristorante.LoginActivity;
import alisson.zanoni.nostroristorante.R;
import alisson.zanoni.nostroristorante.model.Usuario;
import alisson.zanoni.nostroristorante.repository.UsuarioFireBaseRepository;

public class PerfilFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView textNome, textDataNascimento, textTelefone, textEmail;
    UsuarioFireBaseRepository fireBaseRepository;
    Button btnDesconectar;

    private String mParam1;
    private String mParam2;

    public PerfilFragment() {

    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        fireBaseRepository = new UsuarioFireBaseRepository();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textNome = view.findViewById(R.id.nome);
        textDataNascimento = view.findViewById(R.id.dataNascimento);
        textTelefone = view.findViewById(R.id.telefone);
        textEmail = view.findViewById(R.id.email);
        btnDesconectar = view.findViewById(R.id.btnDesconectar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //Metodo para deslogar
        btnDesconectar.setOnClickListener(view -> {
        FirebaseAuth.getInstance().signOut();
        trocarDeTela(LoginActivity.class); });

        textNome.setText("");
        textDataNascimento.setText("");
        textTelefone.setText("");
        textEmail.setText("");
        String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();

        fireBaseRepository.getUsuario(idUsuario, new UsuarioFireBaseRepository.callbackUsuario() {
            @Override
            public void onCallback(Usuario usuario) {
                if(usuario != null) {
                    textNome.setText(usuario.getNome()+" "+ usuario.getSobrenome());
                    textDataNascimento.setText(usuario.getDataNascimento());
                    textTelefone.setText(usuario.getTelefone());
                    textEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                } else {
                    Toast.makeText(getActivity(), "Falha ao buscar os dados do usuário", Toast.LENGTH_SHORT).show();
                    trocarDeTela(HomeFragment.class);
                }
            }
        });
    }

    public void trocarDeTela(Class tela){
        Intent trocarTela = new Intent(getActivity(), tela);
        startActivity(trocarTela);
    }
}