package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText lvl;
    private EditText pokebola;
    private PokemonDAO dao;
    private Pokemon pokemon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editNome);
        lvl = findViewById(R.id.editLevel);
        pokebola = findViewById(R.id.editPokebola);
        dao = new PokemonDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("pokemon")) {
            pokemon = (Pokemon) it.getSerializableExtra("pokemon");
            nome.setText(pokemon.getNome());
            lvl.setText(pokemon.getLvl());
            pokebola.setText(pokemon.getPokebola());
        }

    }

    public void salvar(View view) {
        if (pokemon == null) {
            pokemon = new Pokemon();
            pokemon.setNome(nome.getText().toString());
            pokemon.setLvl(lvl.getText().toString());
            pokemon.setPokebola(pokebola.getText().toString());
            long id = dao.inserir(pokemon);
            Toast.makeText(this, "Seu Pokémom inserido", Toast.LENGTH_SHORT).show();
        } else {
            pokemon.setNome(nome.getText().toString());
            pokemon.setLvl(lvl.getText().toString());
            pokemon.setPokebola(pokebola.getText().toString());
            dao.atualizar(pokemon);
            Toast.makeText(this, "Seu Pokémom foi atualizado", Toast.LENGTH_SHORT).show();
        }
    }
}