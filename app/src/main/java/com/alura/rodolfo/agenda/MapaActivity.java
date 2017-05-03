package com.alura.rodolfo.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Rodolfo on 03/05/17.
 */

public class MapaActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mapa);

    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction tx = manager.beginTransaction();
    tx.replace(R.id.frame_mapa, new MapaFragment());
    tx.commit();
  }
}
