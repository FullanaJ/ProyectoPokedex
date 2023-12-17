package com.fullana.proyectopokedex.poqueapi;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {


    public static final int DEFAULT_HP = 23;
    public static final int DEFAULT_ATK = 10;
    public static final int DEFAULT_DEF = 7;
    public static final int DEFAULT_SPD = 16;
    public static final int DEFAULT_EXP = 0;
    public static final int MAXHP = 300;
    public static final int MAXATK = 300;
    public static final int MAXDEF = 300;
    public static final int MAXSPD = 300;
    public static final int MAXEXP = 1000;
    private String name;
    private int height;
    private int weight;
    private PokemonSprites sprites;
    private List<PokemonTypes> types;

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", sprites=" + sprites +
                ", types=" + types +
                '}';
    }
    public static float createStats(int min,int max){
        return (int) (Math.random()*max)+min;
    }
    public void setTypes(List<PokemonTypes> types) {
        this.types = types;
    }

    public List<PokemonTypes> getTypes() {
        return types;
    }

    public String getSprites(){
        return sprites.getFront_default();
    }

    public String getType(int i){
        return types.get(i).getType().getName();
    }

    public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }
}
class PokemonSprites implements Serializable{

    private String front_default;

    @Override
    public String toString() {
        return "PokemonSprites{" +
                "front_default='" + front_default + '\'' +
                '}';
    }

    public PokemonSprites() {
    }

    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }
}

