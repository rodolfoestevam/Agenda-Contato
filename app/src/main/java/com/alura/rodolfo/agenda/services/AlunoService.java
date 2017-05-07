package com.alura.rodolfo.agenda.services;

import com.alura.rodolfo.agenda.modelo.Aluno;
import com.alura.rodolfo.agenda.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Rodolfo on 07/05/17.
 */

public interface AlunoService {

  @POST("aluno")
  Call<Void> insere(@Body Aluno aluno);
}
