package com.alura.rodolfo.agenda.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alura.rodolfo.agenda.ListaAlunosActivity;
import com.alura.rodolfo.agenda.modelo.Aluno;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Rodolfo on 13/04/17.
 */

public class AlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(context);
        view.setText("Item na posição " + position);
        
        return view;
    }
}
