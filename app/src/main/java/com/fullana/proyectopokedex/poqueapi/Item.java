package com.fullana.proyectopokedex.poqueapi;

import com.google.gson.annotations.SerializedName;

public class Item {

        public String name;
        @SerializedName("sprites")
        public Sprites sprites;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
}
