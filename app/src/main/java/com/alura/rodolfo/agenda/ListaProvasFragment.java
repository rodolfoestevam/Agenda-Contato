package com.alura.rodolfo.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alura.rodolfo.agenda.modelo.Prova;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rodolfo on 24/04/17.
 */

public class ListaProvasFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);

    List<String> topicosJava = Arrays.asList(
        "Classes Abstratas, Interface, Exceptions, Threads, IDE Eclipse, Heranca, Polimorfismo, Collections");
    Prova provaJava =
        new Prova("Java", "01/05/2017", topicosJava);

    List<String> topicosAndroid = Arrays
        .asList("Viewgroups, R class, Grid, Retrofit, Fragments, Intents, Material Design");
    Prova provaAndroid =
        new Prova("Android", "01/05/2017", topicosAndroid);

    List<String> topicosJavascript = Arrays.asList("Form, functions, jQuery, selector, data");
    Prova provaJavascript =
        new Prova("Javascript", "01/05/2017", topicosJavascript);

    List<String> topicosHTML = Arrays.asList("Estrutura HTML, Padding, Margin");
    Prova provaHTML =
        new Prova("HTML", "01/05/2017", topicosHTML);

    List<Prova> provas = Arrays.asList(provaJava, provaAndroid, provaJavascript, provaHTML);

    ArrayAdapter<Prova> adapter = new ArrayAdapter<>(getContext(),
        android.R.layout.simple_list_item_1,
        provas);

    ListView lista = (ListView) view.findViewById(R.id.provas_lista);
    lista.setAdapter(adapter);

    lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Prova prova = (Prova) parent.getItemAtPosition(position);
        Toast.makeText(getContext(), "Clicou na prova de " + prova, Toast.LENGTH_LONG)
            .show();
        ProvasActivity provasActivity = (ProvasActivity) getActivity();
        provasActivity.selecionaProva(prova);

      }
    });

    return view;

  }
}
