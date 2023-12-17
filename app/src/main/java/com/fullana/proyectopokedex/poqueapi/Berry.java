package com.fullana.proyectopokedex.poqueapi;


public class Berry{
    public Itemm item;

    public String getItemName() {
        return item.name;
    }
    public String getItemUrl() {
        return item.url;
    }
    public void setItem(Itemm item) {
        this.item = item;
    }


    static class Itemm{
        public String name;
        public String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }


}
