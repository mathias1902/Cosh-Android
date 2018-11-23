package com.example.opet.infoshopping.Model;

import java.sql.Timestamp;

/**
 * Created by opet on 23/11/2018.
 */

public class Cidade {

    private String nome;
    private String estado;

    public Cidade(){

    }

    public Cidade(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long generateTimeStamp(){
        return new Timestamp(System.currentTimeMillis()).getTime();
    }
}
