package com.fullana.proyectopokedex.poqueapi;

import com.google.gson.annotations.SerializedName;

public class Sprites {
    @SerializedName("default")
    public String mydefault;

    public String getMydefault() {
        return mydefault;
    }

    public void setMydefault(String mydefault) {
        this.mydefault = mydefault;
    }
}
