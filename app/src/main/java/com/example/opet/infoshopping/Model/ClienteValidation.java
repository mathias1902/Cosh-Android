package com.example.opet.infoshopping.Model;

import com.example.opet.infoshopping.Util.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by opet on 13/06/2018.
 */

public class ClienteValidation extends SQLiteOpenHelper {

    String[] columns = {
            "id_cliente"
    };

    SQLiteDatabase db = this.getReadableDatabase();

    public ClienteValidation(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
