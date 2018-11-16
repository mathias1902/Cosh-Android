package com.example.opet.infoshopping.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by opet on 06/06/2018.
 */

public class BancoUtil {

    //Tabela Cliente
    public static final String NOME_BANCO = "infoshopping.db";
    public static final String TABELA_CLIENTE = "Cliente";
    public static final String ID_CLIENTE = "idcliente";
    public static final String NOME_CLIENTE = "nome";
    public static final String EMAIL = "email";
    public static final String CPF = "cpf";
    public static final String SENHA = "senha";

    public static final int VERSAO = 5;


}