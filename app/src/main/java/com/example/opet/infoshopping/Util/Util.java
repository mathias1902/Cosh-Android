package com.example.opet.infoshopping.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Message;

import com.example.opet.infoshopping.R;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by opet on 13/06/2018.
 */

public class Util {

    public static String toMD5 (String data){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(data));
            return String.format("%032x", new BigInteger(1, md5.digest()));
              } catch (NoSuchAlgorithmException e ){
            e.printStackTrace();
        }
     return null;
    }


     public static void Alert(Context context, String mensagem) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        //ADICIONANDO UM TITULO A NOSSA MENSAGEM DE ALERTA
        alertDialog.setTitle(R.string.app_name);

        //MENSAGEM A SER EXIBIDA
        alertDialog.setMessage(mensagem);

        //CRIA UM BOTÃO COM O TEXTO OK SEM AÇÃO
        alertDialog.setPositiveButton("OK", null);

        //MOSTRA A MENSAGEM NA TELA
        alertDialog.show();


    }
}