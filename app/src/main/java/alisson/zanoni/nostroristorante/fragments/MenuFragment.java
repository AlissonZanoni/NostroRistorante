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

import alisson.zanoni.nostroristorante.CardapioActivity;
import alisson.zanoni.nostroristorante.MenuReservaActivity;
import alisson.zanoni.nostroristorante.R;

public class MenuFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button btnCardapio, btnReservaCancelamento;

    private String mParam1;
    private String mParam2;

    public MenuFragment() {

    }

    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCardapio = view.findViewById(R.id.BtnCardapio);
        btnReservaCancelamento = view.findViewById(R.id.BtnReservaCancelamento);

        btnCardapio.setOnClickListener(v -> trocarDeTela(CardapioActivity.class));
        btnReservaCancelamento.setOnClickListener(v -> trocarDeTela(MenuReservaActivity.class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    public void trocarDeTela(Class tela){
        Intent trocarTela = new Intent(getActivity(), tela);
        startActivity(trocarTela);
    }
}