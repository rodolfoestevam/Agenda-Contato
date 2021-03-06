package com.alura.rodolfo.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alura.rodolfo.agenda.modelo.Prova;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Rodolfo on 24/04/17.
 */

public class ProvasActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_provas);

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction tx = fragmentManager.beginTransaction();

    tx.replace(R.id.frame_principal, new ListaProvasFragment());
    if (estaNoModoPaisagem()) {
      tx.replace(R.id.frame_secundario, new DetalhesProvaFragment());
    }

    tx.commit();
  }

  private boolean estaNoModoPaisagem() {
    return getResources().getBoolean(R.bool.modoPaisagem);
  }

  public void selecionaProva(Prova prova) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    if (!estaNoModoPaisagem()) {
      FragmentTransaction tx = fragmentManager.beginTransaction();

      DetalhesProvaFragment detalhesFragment = new DetalhesProvaFragment();
      Bundle parametros = new Bundle();
      parametros.putSerializable("prova", prova);
      detalhesFragment.setArguments(parametros);

      tx.replace(R.id.frame_principal, detalhesFragment);
      tx.addToBackStack(null);

      tx.commit();

    } else {
      DetalhesProvaFragment detalhesProvaFragment = (DetalhesProvaFragment) fragmentManager.findFragmentById(R.id.frame_secundario);
      detalhesProvaFragment.populaCamposCom(prova);
    }
  }
}

