package com.alura.rodolfo.agenda.converter;

import com.alura.rodolfo.agenda.modelo.Aluno;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by Rodolfo on 17/04/17.
 */

public class AlunoConverter {

  public String converteParaJSON(List<Aluno> alunos) {
    JSONStringer js = new JSONStringer();
    try {
      js.object().key("list").array()
          .object().key("aluno").array();

      for (Aluno aluno : alunos) {
        js.object()
            .key("id").value(aluno.getId())
            .key("nome").value(aluno.getNome())
            .key("telefone").value(aluno.getTelefone())
            .key("endereco").value(aluno.getEndereco())
            .key("site").value(aluno.getSite())
            .key("nota").value(aluno.getNota())
            .endObject();
      }
      return js.endArray().endObject()
          .endArray().endObject().toString();
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return "";
  }

}
