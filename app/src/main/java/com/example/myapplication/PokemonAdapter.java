package com.example.myapplication;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PokemonAdapter extends BaseAdapter {

    private List<Pokemon> pokemons;
    private Activity activity;
    private ViewGroup viewGroup;

    public PokemonAdapter(Activity activity, List<Pokemon> pokemons) {
        this.activity = activity;
        this.pokemons = pokemons;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int i) {
        return pokemons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return pokemons.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);
        TextView nome = v.findViewById(R.id.txt_nome);
        TextView lvl = v.findViewById(R.id.txt_lvl);
        TextView pokebola = v.findViewById(R.id.txt_pokebola);
        Pokemon a = pokemons.get(i);
        nome.setText(a.getNome());
        lvl.setText(a.getLvl());
        pokebola.setText(a.getPokebola());
        return v;
    }
}
