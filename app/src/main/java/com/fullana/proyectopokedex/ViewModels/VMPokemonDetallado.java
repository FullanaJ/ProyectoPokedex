package com.fullana.proyectopokedex.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fullana.proyectopokedex.poqueapi.PokemonTypes;

import java.util.List;

public class VMPokemonDetallado extends ViewModel {

    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<Float> hp = new MutableLiveData<>();
    MutableLiveData<Float> atk = new MutableLiveData<>();
    MutableLiveData<Float> def = new MutableLiveData<>();
    MutableLiveData<Float> spd = new MutableLiveData<>();
    MutableLiveData<Float> exp = new MutableLiveData<>();
    MutableLiveData<List<PokemonTypes>> types = new MutableLiveData<>();
    MutableLiveData<String> image = new MutableLiveData<>();
    MutableLiveData<Integer> weight = new MutableLiveData<>();
    MutableLiveData<Integer> height = new MutableLiveData<>();

    public MutableLiveData<Integer> getWeight() {
        return weight;
    }

    public void setWeight(MutableLiveData<Integer> weight) {
        this.weight = weight;
    }

    public MutableLiveData<Integer> getHeight() {
        return height;
    }

    public void setHeight(MutableLiveData<Integer> height) {
        this.height = height;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(MutableLiveData<String> name) {
        this.name = name;
    }

    public VMPokemonDetallado() {
    }

    public MutableLiveData<String> getImage() {
        return image;
    }

    public void setImage(MutableLiveData<String> image) {
        this.image = image;
    }

    public MutableLiveData<Float> getHp() {
        return hp;
    }

    public void setHp(MutableLiveData<Float> hp) {
        this.hp = hp;
    }

    public MutableLiveData<Float> getAtk() {
        return atk;
    }

    public void setAtk(MutableLiveData<Float> atk) {
        this.atk = atk;
    }

    public MutableLiveData<Float> getDef() {
        return def;
    }

    public void setDef(MutableLiveData<Float> def) {
        this.def = def;
    }

    public MutableLiveData<Float> getSpd() {
        return spd;
    }

    public void setSpd(MutableLiveData<Float> spd) {
        this.spd = spd;
    }

    public MutableLiveData<Float> getExp() {
        return exp;
    }

    public void setExp(MutableLiveData<Float> exp) {
        this.exp = exp;
    }

    public MutableLiveData<List<PokemonTypes>> getTypes() {
        return types;
    }

    public void setTypes(MutableLiveData<List<PokemonTypes>> types) {
        this.types = types;
    }
}
