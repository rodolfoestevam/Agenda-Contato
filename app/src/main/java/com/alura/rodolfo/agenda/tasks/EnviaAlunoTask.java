package com.alura.rodolfo.agenda.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.alura.rodolfo.agenda.web.WebClient;
import com.alura.rodolfo.agenda.dao.AlunoDAO;
import com.alura.rodolfo.agenda.modelo.Aluno;

import java.util.List;

import com.alura.rodolfo.agenda.converter.AlunoConverter;

/**
 * Created by Rodolfo on 17/04/17.
 */

public class EnviaAlunoTask extends AsyncTask <Void, Void, String> {
    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunoTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
         dialog = ProgressDialog.show(context, "Aguarde", "Enviando Alunos...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converteParaJSON(alunos);

        WebClient client = new WebClient();

        return client.post(json);
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context,resposta, Toast.LENGTH_LONG).show();
    }
}
