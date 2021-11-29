package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {

    private Conexao conexao;
    private SQLiteDatabase banco4;

    public PokemonDAO(Context context) {
        conexao = new Conexao(context);
        banco4 = conexao.getWritableDatabase();
    }

    //Insere as informações no db
    public long inserir(Pokemon pokemon) {
        ContentValues values = new ContentValues();
        values.put("nome", pokemon.getNome());
        values.put("lvl", pokemon.getLvl());
        values.put("pokebola", pokemon.getPokebola());
        return banco4.insert("pokemon", null, values);
    }

    //Cria lista dos alunos
    public List<Pokemon> obterTodos() {
        List<Pokemon> pokemons = new ArrayList<>();
        Cursor cursor = banco4.query("pokemon", new String[]{"id", "nome", "lvl", "pokebola"},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Pokemon a = new Pokemon();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setLvl(cursor.getString(2));
            a.setPokebola(cursor.getString(3));
            pokemons.add(a);
        }
        return pokemons;
    }

    //Exclui item selecionado
    public void excluir(Pokemon a) {
        banco4.delete("pokemon", "id= ?", new String[]{a.getId().toString()});
    }

    public void atualizar(Pokemon pokemon) {
        ContentValues values = new ContentValues();
        values.put("nome", pokemon.getNome());
        values.put("lvl", pokemon.getLvl());
        values.put("pokebola", pokemon.getPokebola());
        banco4.update("pokemon",values,"id = ?", new String[]{pokemon.getId().toString()});
    }

}
