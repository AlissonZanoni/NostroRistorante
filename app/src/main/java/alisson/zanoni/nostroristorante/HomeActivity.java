package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import alisson.zanoni.nostroristorante.databinding.ActivityHomeBinding;
import alisson.zanoni.nostroristorante.databinding.ActivityMainBinding;
import alisson.zanoni.nostroristorante.model.Comida;
import alisson.zanoni.nostroristorante.repository.ComidaFireBaseRepository;
import alisson.zanoni.nostroristorante.repository.UsuarioFireBaseRepository;

public class HomeActivity extends AppCompatActivity{

    private ActivityHomeBinding binding;
    private NavHostFragment navHostFragment;
    private NavController navController;

////    TextView textUsuarioLogado;
//   // Button btnMenu;
//    UsuarioFireBaseRepository fireBaseRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initNavigation();

//        fireBaseRepository = new UsuarioFireBaseRepository();
       // textUsuarioLogado = findViewById(R.id.usuarioLogado);
       // btnMenu = findViewById(R.id.BtnMenu);

        //btnMenu.setOnClickListener(view -> trocarDeTela(MenuActivity.class));

//metodo para deslogar.
//        btnLogout.setOnClickListener(view -> {
//            FirebaseAuth.getInstance().signOut();
//            trocarDeTela(LoginActivity.class);
//        });

//        btnPerfil.setOnClickListener(view -> {
//            trocarDeTela(PerfilActivity.class);
//        });
    }

     private void initNavigation(){
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
    }



//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        textUsuarioLogado.setText("");
//
//        String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//        fireBaseRepository.getNomeUsuario(idUsuario, new UsuarioFireBaseRepository.callbackNome() {
//            @Override
//            public void onCallback(String nomeUsuario) {
//                if(nomeUsuario != null) {
//                    textUsuarioLogado.setText(" " + nomeUsuario);
//                } else {
//                    Toast.makeText(HomeActivity.this, "Falha ao buscar os dados do usuário", Toast.LENGTH_SHORT).show();
//                    FirebaseAuth.getInstance().signOut();
//                    Intent logout = new Intent(HomeActivity.this,LoginActivity.class);
//                    startActivity(logout);
//                    finish();
//                }
//            }
//        });
//    }

//    public void trocarDeTela(Class tela){
//        Intent trocarTela = new Intent(HomeActivity.this, tela);
//        startActivity(trocarTela);
//        finish();
//    }
}