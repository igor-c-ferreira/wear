package br.com.pogamadores.tutoriais.wear.ui.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import br.com.pogamadores.tutoriais.wear.R;
import br.com.pogamadores.tutoriais.wear.ui.fragment.HomeFragment;


public class Principal extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeFragment())
                    .commit();
        }
    }
}
