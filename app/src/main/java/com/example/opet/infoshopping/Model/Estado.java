package com.example.opet.infoshopping.Model;

/**
 * Created by opet on 23/11/2018.
 */

public class Estado {

    private int id;
    private String estado;

    public Estado(){}

    public Estado(int id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
