package com.fullana.proyectopokedex.poqueapi;

import java.io.Serializable;

public class PokemonTypes implements Serializable {
    private Type type;

    public PokemonTypes() {
    }

    @Override
    public String toString() {
        return "PokemonTypes{" +
                "type=" + type +
                '}';
    }

    public Type getType() {
        return type;
    }

    public void setType(Type thype) {
        this.type = thype;
    }
}
