package com.alura.rodolfo.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.alura.rodolfo.agenda.modelo.Aluno;

/**
 * Created by Rodolfo on 09/01/17.
 */
public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoSite;
    private final EditText campoTelefone;
    private final EditText campoEmail;
    private final RatingBar campoNota;

    private Aluno aluno;

/* instacia novo aluno, recupera os dados preenchidos no formulario, preencher os dados do aluno, devolver o aluno*/

    public FormularioHelper(FormularioActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);

        aluno = new Aluno();

    }

    public Aluno pegaAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));

        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoEmail.setText(aluno.getEmail());
        campoNota.setProgress((int) aluno.getNota());
        this.aluno = aluno;
    }

}
