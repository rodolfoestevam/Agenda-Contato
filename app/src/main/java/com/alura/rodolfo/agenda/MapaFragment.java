package com.alura.rodolfo.agenda;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import com.alura.rodolfo.agenda.dao.AlunoDAO;
import com.alura.rodolfo.agenda.modelo.Aluno;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

/**
 * Created by Rodolfo on 03/05/17.
 */

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

  private GoogleMap mapa;

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);

    getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    LatLng minhaPosicao = pegaCoordenadaEndereco(
        "Rua Pastor José Amaro da Silva, Boa Viagem, Pernambuco");
    if (minhaPosicao != null) {
      CameraUpdate update = CameraUpdateFactory.newLatLngZoom(minhaPosicao, 17);
      googleMap.moveCamera(update);
    }

    AlunoDAO alunoDAO = new AlunoDAO(getContext());
    for (
        Aluno aluno : alunoDAO.buscaAlunos()) {
      LatLng coordenada = pegaCoordenadaEndereco(aluno.getEndereco());
      if (coordenada != null) {
        MarkerOptions marcador = new MarkerOptions();
        marcador.position(coordenada);
        marcador.title(aluno.getNome());
        marcador.snippet(String.valueOf(aluno.getNota()));
        googleMap.addMarker(marcador);
      }

    }
    alunoDAO.close();

    new Localizador(getContext(), googleMap);

  }

  private LatLng pegaCoordenadaEndereco(String endereco) {

    try {
      Geocoder geocoder = new Geocoder(getContext());
      List<Address> resultados =
          geocoder.getFromLocationName(endereco, 1);

      if (!resultados.isEmpty()) {
        LatLng posicao = new LatLng(resultados.get(0).getLatitude(),
            resultados.get(0).getLongitude());
        return posicao;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
