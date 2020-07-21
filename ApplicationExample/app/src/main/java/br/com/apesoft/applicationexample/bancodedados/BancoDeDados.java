package br.com.apesoft.applicationexample.bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoDeDados {

    public SQLiteDatabase banco;
    public GerenciarBanco gerenciarBanco;

    public BancoDeDados(Context context) {
        gerenciarBanco = new GerenciarBanco(context);
    }

    /* Método 'criarAnotacao', que receberá dois parâmetros do tipo 'String',
       um título e um conteúdo, e adicionará ambos ao banco de dados. */
    public boolean criarAnotacao(String titulo, String conteudo) {
        // Faz-se um requisição do banco de dados em modo leitura e escrita
        banco = gerenciarBanco.getWritableDatabase();

        // Cria-se um objeto do tipo 'ContentValues', com o título e conteúdo da anotação.
        ContentValues valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("conteudo", conteudo);

        // Insere-se a nova anotação no banco de dados.
        long resultado = banco.insert("anotacoes", null, valores);
        // Fecha-se a conexão com o banco de dados
        banco.close();

        /* Retorna-se 'true' se a inserção for realizada com sucesso,
           ou 'false', caso ocorra alguma falha. */
        return resultado > 0;
    }

    public Cursor obterAnotacoes() {
        /* Cria-se um objeto do tipo 'array de Strings',
           com o nome dos campos que se quer resgatar. */
        String[] campos = {"_id", "titulo"};

        /* Obtém-se uma conexão com o banco de dados em modo leitura,
           ou seja, que só pode executar pesquisas no banco de dados. */
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();

        /* Realiza-se uma consulta no banco de dados, especificamente
           na tabela de anotações, ordenando os dados pelo título e em
           ordem decrescente. */
        Cursor cursor = db.query("anotacoes", campos, null,
                null, null,null, "titulo ASC");

        if (cursor!=null) {
            // Ajusta-se o cursor para o primeiro elemento.
            cursor.moveToFirst();
        }

        // Fecha-se a conexão com o banco de dados.
        db.close();

        /* Enfim, retorna-se 'cursor' com todos os elementos obtidos
           através da consulta ao banco de dados. */
        return cursor;
    }

    public void atualizarAnotacao(int id, String titulo, String conteudo) {
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        String where = "_id = " + id;

        ContentValues valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("conteudo", conteudo);

        db.update("anotacoes", valores, where, null);
        db.close();
    }

    public void excluirAnotacao(int id) {
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        String where = "_id = " + id;

        db.delete("anotacoes", where, null);
        db.close();
    }

    public Cursor consultarAnotacaoPeloId(int id) {
        Cursor cursor;
        String[] campos = {"_id", "titulo", "conteudo"};
        String where = "_id = " + id;
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        cursor = db.query("anotacoes", campos, where, null, null, null, null, null);

        if (cursor!=null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}
