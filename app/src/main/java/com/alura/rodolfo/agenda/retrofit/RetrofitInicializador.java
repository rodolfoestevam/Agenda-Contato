package com.alura.rodolfo.agenda.retrofit;

import com.alura.rodolfo.agenda.services.AlunoService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Rodolfo on 07/05/17.
 */

public class RetrofitInicializador {

  private final Retrofit retrofit;

  public RetrofitInicializador() {
    retrofit = new Retrofit.Builder()
        .baseUrl("http://192.168.0.6:8080/api/")
        .addConverterFactory(JacksonConverterFactory.create())
        .build();
  }

  public AlunoService getAlunoService() {
    return retrofit.create(AlunoService.class);
  }
}
