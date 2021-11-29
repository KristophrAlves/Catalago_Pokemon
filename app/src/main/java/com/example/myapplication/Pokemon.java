package com.example.myapplication;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Pokemon implements Serializable {

    private Integer id;
    private String nome;
    private String lvl;
    private String pokebola;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLvl() { return lvl; }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public String getPokebola() {
        return pokebola;
    }

    public void setPokebola(String pokebola) {
        this.pokebola = pokebola;
    }

    @Override
    public String toString(){
        return nome;
    }

}
