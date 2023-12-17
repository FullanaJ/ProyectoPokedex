package com.fullana.proyectopokedex.poqueapi;

import java.io.Serializable;

public class Type implements Serializable {
    private String name;

    public Type() {
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
