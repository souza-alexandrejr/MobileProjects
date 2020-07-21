package br.com.apesoft.applicationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.apesoft.applicationexample.bancodedados.BancoDeDados;

public class CriarAnotacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_anotacao);
    }

    // Método 'voltar', que irá abrir a tela inicial do aplicativo.
    public void voltar(View v) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }
    public void criarAnotacao(View v) {
        // Instancia-se a classe 'BancoDeDados'.
        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());

        // Obtém-se os campos digitáveis, setando-os em variáveis.
        EditText titulo = (EditText) findViewById(R.id.campoTitulo);
        EditText conteudo = (EditText) findViewById(R.id.campoConteudo);

        /* Lê-se o que foi digitado nos campos editáveis, passando esses dados
           como parâmetros para o método 'criarAnotacao' da classe 'Banco de Dados'. */
        boolean resultado = bancoDeDados.criarAnotacao(titulo.getText().toString(),
                                                        conteudo.getText().toString());

        /* Dessa forma, será feita a inserção no banco, que retornará 'true' ou 'false'.
           Cria-se um 'Toast' para avisar ao usuário sobre a inserção da anotação. */
        if (resultado) {
            Toast.makeText(getApplicationContext(),
                    "Anotação criada com sucesso!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Erro ao incluir anotação. Tente novamente.", Toast.LENGTH_LONG).show();
        }

        // Chama-se o método 'voltar' a fim de carregar a tela inicial do aplicativo novamente.
        voltar(v);
    }
}