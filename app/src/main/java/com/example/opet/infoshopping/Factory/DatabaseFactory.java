package com.example.opet.infoshopping.Factory;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.infoshopping.Util.BancoUtil;


/**
 * Created by opet on 18/06/2018.
 */

public class DatabaseFactory extends SQLiteOpenHelper {

    public DatabaseFactory(Context context){
        super(context, BancoUtil.NOME_BANCO,null,BancoUtil.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+ BancoUtil.TABELA_CLIENTE+"("
                + BancoUtil.ID_CLIENTE+ " integer primary key autoincrement,"
                + BancoUtil.NOME_CLIENTE+ " text,"
                + BancoUtil.SENHA + " text,"
                + BancoUtil.EMAIL + " text,"
                + BancoUtil.CPF + " integer"
                +")";
                  db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_CLIENTE);
        onCreate(db);
    }
}

