package br.com.apesoft.applicationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.com.apesoft.applicationexample.bancodedados.BancoDeDados;

public class EditarAnotacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_anotacao);

        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        final Cursor cursor = bancoDeDados.consultarAnotacaoPeloId(this.getIntent().getIntExtra("id",0));

        EditText titulo = (EditText) findViewById(R.id.campoTitulo);
        EditText conteudo = (EditText) findViewById(R.id.campoConteudo);

        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
        conteudo.setText(cursor.getString(cursor.getColumnIndexOrThrow("conteudo")));
    }

    // Método 'voltar', que irá abrir a tela inicial do aplicativo.
    public void voltar(View v) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }

    public void atualizarAnotacao(View v) {
        // Instancia-se a classe 'BancoDeDados'.
        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());

        // Obtém-se os campos digitáveis, setando-os em variáveis.
        EditText titulo = (EditText) findViewById(R.id.campoTitulo);
        EditText conteudo = (EditText) findViewById(R.id.campoConteudo);

        /* Faz-se o 'update' no banco de dados e verifica-se a resposta da atualização,
           informando ao usuário sobre o processo e retornando à tela inicial do aplicativo. */
        try {
            bancoDeDados.atualizarAnotacao(this.getIntent().getIntExtra("id",0),
                    titulo.getText().toString(), conteudo.getText().toString());
            Toast.makeText(getApplicationContext(),
                    "Anotação atualizada com sucesso!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Erro ao atualizar anotação. Tente novamente.", Toast.LENGTH_LONG).show();
        }

        // Chama-se o método 'voltar' a fim de carregar a tela inicial do aplicativo novamente.
        voltar(v);
    }

    public void excluirAnotacao(View v) {
        // Instancia-se a classe 'BancoDeDados'.
        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());

        /* Faz-se o 'delete' no banco de dados e verifica-se a resposta da atualização,
           informando ao usuário sobre o processo e retornando à tela inicial do aplicativo. */
        try {
            bancoDeDados.excluirAnotacao(this.getIntent().getIntExtra("id",0));
            Toast.makeText(getApplicationContext(),
                    "Anotação excluída com sucesso!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Erro ao excluir anotação. Tente novamente.", Toast.LENGTH_LONG).show();
        }

        // Chama-se o método 'voltar' a fim de carregar a tela inicial do aplicativo novamente.
        voltar(v);
    }
}