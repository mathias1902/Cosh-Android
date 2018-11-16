package com.example.opet.infoshopping.Model;

import java.util.List;

/**
 * Created by opet on 19/06/2018.
 */

public class ListaClientes {

    private static List<Cliente> clientes;

    public static List<Cliente> getList(){
        return clientes;
    }

    public static void setList(List<Cliente> list){
        clientes = list;
    }
}
