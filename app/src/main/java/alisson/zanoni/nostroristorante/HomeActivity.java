package alisson.zanoni.nostroristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import alisson.zanoni.nostroristorante.fragments.HomeFragment;
import alisson.zanoni.nostroristorante.fragments.MenuFragment;
import alisson.zanoni.nostroristorante.fragments.PerfilFragment;

public class HomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new HomeFragment())
                    .commit();
        }

        LinearLayout layoutInicio = (LinearLayout) findViewById(R.id.layoutInicio);
        layoutInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment())
                        .commit();
            }
        });

        LinearLayout layoutMenu = (LinearLayout) findViewById(R.id.layoutMenu);
        layoutMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new MenuFragment())
                        .commit();
            }
        });

        LinearLayout layoutPerfil = (LinearLayout) findViewById(R.id.layoutPerfil);
        layoutPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new PerfilFragment())
                        .commit();
            }
        });
    }
}