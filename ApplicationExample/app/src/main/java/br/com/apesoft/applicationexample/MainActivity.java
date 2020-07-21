package br.com.apesoft.applicationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.apesoft.applicationexample.bancodedados.BancoDeDados;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Abre-se uma conexão com o banco de dados.
        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());

        /* Chama-se o método 'obterAnotacoes' e armazena-se
           as anotações em uma variável do tipo 'Cursor'. */
        final Cursor cursor = bancoDeDados.obterAnotacoes();

        /* Cria-se um objeto do tipo 'array de Strings',
           com o nome dos campos que se quer resgatar. */
        String[] nomeCampos = new String[] {"_id", "titulo"};

        /* Cria-se outro array, com o ID dos campos que foram
           criados no modelo de layout que será mostrado no ListView. */
        int[] idViews = new int[] {R.id.labelId, R.id.labelTitulo};

        /* Adiciona-se o conteúdo do banco e o modelo de layout em um adaptador. */
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.modelo_lista, cursor, nomeCampos, idViews, 0);

        /* Resgata-se a ListView e em seguida entrega-se a ela os
           itens que serão mostrados ao usuário. */
        ListView lista = (ListView) findViewById(R.id.listaDeNotas);
        lista.setAdapter(adaptador);

        /* Adiciona-se um evento de 'click' em todos os itens da lista.
           Após o 'click', a tela 'Editar Anotacao' será carregada,
           passando como parâmetro o 'ID' da anotação selecionada. */
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent intent = new Intent(MainActivity.this, EditarAnotacao.class);
                intent.putExtra("id",cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                startActivity(intent);
                finish();
            }
        });
    }

    public void abrirTelaCriarNovaAnotacao(View v) {
        Intent startNewActivity = new Intent(this, CriarAnotacao.class);
        startActivity(startNewActivity);
    }
}