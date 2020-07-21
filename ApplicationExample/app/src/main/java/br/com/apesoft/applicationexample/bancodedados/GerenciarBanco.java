package br.com.apesoft.applicationexample.bancodedados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class GerenciarBanco extends SQLiteOpenHelper {

    // Definindo o nome do banco de dados
    public static final String NOME_BANCO = "bancoDeDados.db";
    /* Define-se a versão do banco de dados.
       Essa versão serve para realizar um "update" no banco,
       caso seja feita alguma alteração na sua estrutura.
       Dessa forma, basta alterar o número da versão para o
       número posterior para que, automaticamente, ele exclua
       a tabela existente e crie uma nova com a estrutura
       atualizada. */
    public static final int VERSAO = 1;

    /* Construtor da classe GerenciarBanco que irá solicitar
       ao construtor da classe SQLiteOpneHelper a criação
       do banco de dados, caso seja necessário. */
    public GerenciarBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    /* Sobrescreve-se o método 'onCreate' da classe SQLiteOpenHelper,
       que irá criar a tabela no banco de dados, sendo o script SQL
       declarado abaixo o responsável pela criação da tabela. */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE anotacoes (_id integer primary key autoincrement," +
                                            "titulo text, conteudo text)";
        db.execSQL(sql);
    }

    /* Sobrescreve-se o método 'onUpdate' para que, caso a versão do
       banco de dados criada seja inferior a versão definida na linha 18,
       ele remova a tabela existente através do script SQL abaixo e crie
       uma nova tabela, chamando o método 'onCreate' definido acima. */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS anotacoes");
        onCreate(db);
    }
}
