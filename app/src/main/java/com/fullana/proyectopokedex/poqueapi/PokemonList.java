package com.fullana.proyectopokedex.poqueapi;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class PokemonList {
    private ArrayList<Pokemon> results;

    public PokemonList() {
        results = new ArrayList<>();
    }

    public ArrayList<Pokemon> getResults() {
        return results;
    }
    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }


}