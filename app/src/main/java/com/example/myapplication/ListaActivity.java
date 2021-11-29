package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {



    private ListView listView;
    private PokemonDAO dao;
    private List<Pokemon> pokemons;
    private List<Pokemon> pokemonsFiltrados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listView = findViewById(R.id.lista_pokemons);
        dao = new PokemonDAO(this);
        pokemons = dao.obterTodos();
        pokemonsFiltrados.addAll(pokemons);
        //ArrayAdapter<Pokemon> adaptador = new ArrayAdapter<Pokemon>(this, android.R.layout.simple_list_item_1, pokemonsFiltrados);
        PokemonAdapter adaptador = new PokemonAdapter(this,pokemonsFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);
        //busca o botão de pesquisa
        SearchView sv = (SearchView)  menu.findItem(R.id.app_bar_search). getActionView();
        //
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //System.out.println("Ditiou" + s); <--teste do campo pesquisar>
                procuraPokemon(s);
                return false;
            }
        });
        return true;
    }

    //
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.meu_contexto, menu);
    }

    //Faz a procura do texto digitado
    public void procuraPokemon(String nome){
        pokemonsFiltrados.clear();
        for(Pokemon a : pokemons){
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())){
                pokemonsFiltrados.add(a);
            }
        }
        listView.invalidateViews();
    }

    public  void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo ) item.getMenuInfo();
        final Pokemon pokemonExcluir = pokemonsFiltrados.get(menuInfo.position);//pega a posição do item selecionado

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o pokemon ?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pokemonsFiltrados.remove(pokemonExcluir);
                        pokemons.remove(pokemonExcluir);
                        dao.excluir(pokemonExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    //aciona botão cadastrar
    public void cadastrar(MenuItem item){
        Intent it = new Intent (this, MainActivity.class);
        startActivity(it);
    }

    public  void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo ) item.getMenuInfo();
        final Pokemon PokemonAtualizar = pokemonsFiltrados.get(menuInfo.position);//pega a posição do item selecionado
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("pokemon", PokemonAtualizar);
        startActivity(it);
    }

    //atualiza lista de cadastrados
    @Override
    public void onResume(){
        super.onResume();
        pokemons  = dao.obterTodos();
        pokemonsFiltrados.clear();
        pokemonsFiltrados.addAll(pokemons);
        listView.invalidateViews();
    }
}