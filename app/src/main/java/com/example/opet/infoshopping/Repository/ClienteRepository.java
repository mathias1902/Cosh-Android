package com.example.opet.infoshopping.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.infoshopping.Factory.DatabaseFactory;
import com.example.opet.infoshopping.Model.Cliente;
import com.example.opet.infoshopping.Util.BancoUtil;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by opet on 13/06/2018.
 */

public class ClienteRepository {

    private SQLiteDatabase db;
    private DatabaseFactory banco;
    public static final int CLIENTES_TOTAL = 1;

    public ClienteRepository (Context context) {
        banco = new DatabaseFactory(context);
    }

    public ClienteRepository() {

    }

    public long insereDado(Cliente cliente) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNome());
        valores.put(BancoUtil.EMAIL, cliente.getEmail());
        valores.put(BancoUtil.CPF, cliente.getCPF()) ;
        valores.put(BancoUtil.SENHA, cliente.getSenha()) ;

        resultado = db.insert(BancoUtil.TABELA_CLIENTE, null, valores);
        db.close();

        return resultado;

    }


    public Cliente carregaClientePorID(int id){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_CLIENTE, BancoUtil.NOME_CLIENTE,
                BancoUtil.EMAIL, BancoUtil.SENHA,
                BancoUtil.CPF};
        db = banco.getReadableDatabase();


        String where = BancoUtil.ID_CLIENTE + " = " + id;

        cursor = db.query(BancoUtil.TABELA_CLIENTE, campos, where, null, null, null, null, null);

        Cliente cliente = new Cliente();
        if (cursor != null) {
            cursor.moveToFirst();

            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_CLIENTE));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_CLIENTE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.EMAIL));
            String senha = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SENHA));
            long cpf = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CPF));


            cliente.setID(ID);
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setSenha(senha);
            cliente.setCPF(cpf);


        }
        db.close();
        return cliente;
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_CLIENTE, BancoUtil.NOME_CLIENTE, BancoUtil.EMAIL, BancoUtil.CPF,
                BancoUtil.SENHA};
        db = banco.getReadableDatabase();

        cursor = db.query(BancoUtil.TABELA_CLIENTE, campos, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public List<Cliente> carregaDadosLista() {
        Cursor cursor = null;
           cursor = carregaDados();

        List<Cliente> clientes = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Cliente cliente = new Cliente();
                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_CLIENTE));
                    String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_CLIENTE));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.EMAIL));
                    String senha = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SENHA));
                    long cpf = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CPF));


                    cliente.setID(ID);
                    cliente.setNome(nome);
                    cliente.setCPF(cpf);
                    cliente.setEmail(email);
                    cliente.setSenha(senha);
                    clientes.add(cliente);

                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return clientes;
    }




    public void deletaRegistro(int id_cliente){
        String where = BancoUtil.ID_CLIENTE + "=" + id_cliente;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_CLIENTE,where,null);
        db.close();
    }

    public boolean atualizaCliente(Cliente cliente){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_CLIENTE + " = " + cliente.getID();

        valores = new ContentValues();
        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNome());
        valores.put(BancoUtil.EMAIL, cliente.getEmail());
        valores.put(BancoUtil.CPF, cliente.getCPF());
        valores.put(BancoUtil.SENHA, cliente.getSenha());

        int resultado = db.update(BancoUtil.TABELA_CLIENTE,valores,where,null);
        db.close();
        if(resultado > 0)
            return true;
        else
            return false;
    }

    public long validaCliente(String email, String senha){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_CLIENTE, BancoUtil.EMAIL, BancoUtil.SENHA};
        db = banco.getReadableDatabase();

        String where = BancoUtil.EMAIL + " = " + "'" + email + "'";
        where += " and " + BancoUtil.SENHA + " = " + "'" + senha + "'";

        cursor = db.query(BancoUtil.TABELA_CLIENTE, campos, where, null, null, null, null, null);

        cursor.moveToFirst();

        db.close();

        if(cursor.getCount() > 0)
            return cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_CLIENTE));
        return -1;
    }

}