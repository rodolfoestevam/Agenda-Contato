package com.alura.rodolfo.agenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.view.MenuItem;

import com.alura.rodolfo.agenda.adapter.AlunosAdapter;
import com.alura.rodolfo.agenda.dao.AlunoDAO;
import com.alura.rodolfo.agenda.modelo.Aluno;
import com.alura.rodolfo.agenda.tasks.EnviaAlunoTask;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

  private ListView listaAlunos;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista_alunos);

    listaAlunos = (ListView) findViewById(R.id.lista_alunos);

    listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);

        Intent intentVaiProFormulario = new Intent(ListaAlunosActivity.this,
            FormularioActivity.class);
        intentVaiProFormulario.putExtra("aluno", aluno);
        startActivity(intentVaiProFormulario);
      }
    });

    Button novoaluno = (Button) findViewById(R.id.novo_aluno);
    novoaluno.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intentVaiProFormulario = new Intent(ListaAlunosActivity.this,
            FormularioActivity.class);
        startActivity(intentVaiProFormulario);
      }
    });

    registerForContextMenu(listaAlunos);

  }


  private void carregaLista() {
    //busca no banco de dados
    AlunoDAO dao = new AlunoDAO(this);
    List<Aluno> alunos = dao.buscaAlunos();
    dao.close();
    //carrega a lista
    AlunosAdapter adapter = new AlunosAdapter(this, alunos);
    listaAlunos.setAdapter(adapter);
  }

  //carrega a lista no lifecycle
  @Override
  protected void onResume() {
    super.onResume();
    carregaLista();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_enviar_notas:
        new EnviaAlunoTask(this).execute();
        break;

      case R.id.menu_baixar_provas:
        Intent vaiParaProvas = new Intent(this, ProvasActivity.class);
        startActivity(vaiParaProvas);
        break;
      case R.id.menu_mapa:
        Intent vaiParaMapa = new Intent(this, MapaActivity.class);
        startActivity(vaiParaMapa);
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v,
      final ContextMenu.ContextMenuInfo menuInfo) {
    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
    final Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

    MenuItem itemLigar = menu.add("Ligar");
    itemLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        if (ActivityCompat
            .checkSelfPermission(ListaAlunosActivity.this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {
          ActivityCompat.requestPermissions(ListaAlunosActivity.this,
              new String[]{Manifest.permission.CALL_PHONE}, 123);
        } else {
          Uri uri = Uri.parse("tel:" + aluno.getTelefone());
          Intent intentLigar = new Intent(Intent.ACTION_CALL, uri);
          startActivity(intentLigar);
        }
        return false;
      }
    });

    //SMS
    MenuItem itemSMS = menu.add("Enviar SMS");
    Intent intentSMS = new Intent(Intent.ACTION_VIEW);
    intentSMS.setData(Uri.parse("sms:" + aluno.getTelefone()));
    itemSMS.setIntent(intentSMS);

    //MAP
    MenuItem itemMapa = menu.add("Visualizar no mapa");
    Intent intentMapa = new Intent(Intent.ACTION_VIEW);
    intentMapa.setData(Uri.parse("geo:0,0?q=" + aluno.getEndereco()));
    itemMapa.setIntent(intentMapa);

    //Site
    MenuItem itemSite = menu.add("Visitar Site");
    Intent intentSite = new Intent(Intent.ACTION_VIEW);
    String site = aluno.getSite();
    if (!site.startsWith("http://")) {
      site = "http://" + site;
    }

    intentSite.setData(Uri.parse(site));
    itemSite.setIntent(intentSite);

    MenuItem deletar = menu.add("Deletar");
    deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

      @Override
      public boolean onMenuItemClick(MenuItem item) {

        AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);

        dao.deleta(aluno);
        dao.close();

        carregaLista();
        return false;
      }
    });


  }
}
