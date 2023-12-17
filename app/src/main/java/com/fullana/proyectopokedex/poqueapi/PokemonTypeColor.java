package com.fullana.proyectopokedex.poqueapi;

import com.fullana.proyectopokedex.R;

public class PokemonTypeColor {


    public static Integer getTypeColor(String nameType) {
        switch (nameType) {
            case "fighting":
                return R.color.fighting;
            case "flying":
                return R.color.flying;
            case "poison":
                return R.color.poison;
            case "ground":
                return R.color.ground;
            case "rock":
                return R.color.rock;
            case "bug":
                return R.color.bug;
            case "ghost":
                return R.color.ghost;
            case "steel":
                return R.color.steel;
            case "fire":
                return R.color.fire;
            case "water":
                return R.color.water;
            case "grass":
                return R.color.grass;
            case "electric":
                return R.color.electric;
            case "psychic":
                return R.color.psychic;
            case "ice":
                return R.color.ice;
            case "dragon":
                return R.color.dragon;
            case "fairy":
                return R.color.fairy;
            case "dark":
                return R.color.dark;
            default:
                return R.color.white;
        }
    }
}