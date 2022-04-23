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

import alisson.zanoni.nostroristorante.HomeActivity;
import alisson.zanoni.nostroristorante.LoginActivity;
import alisson.zanoni.nostroristorante.R;
import alisson.zanoni.nostroristorante.repository.UsuarioFireBaseRepository;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView textUsuarioLogado;
    Button btnMenu;
    UsuarioFireBaseRepository fireBaseRepository;

    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        textUsuarioLogado = view.findViewById(R.id.usuarioLogado);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        textUsuarioLogado.setText("");

        String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();

        fireBaseRepository.getNomeUsuario(idUsuario, new UsuarioFireBaseRepository.callbackNome() {
            @Override
            public void onCallback(String nomeUsuario) {
                if(nomeUsuario != null) {
                    textUsuarioLogado.setText(" " + nomeUsuario);
                } else {
                    Toast.makeText(getActivity(), "Falha ao buscar os dados do usuário", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                    Intent logout = new Intent(getActivity(), LoginActivity.class);
                    startActivity(logout);
                }
            }
        });
    }
}