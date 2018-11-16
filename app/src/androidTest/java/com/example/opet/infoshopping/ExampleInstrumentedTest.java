package com.example.opet.infoshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.opet.infoshopping.Factory.DatabaseFactory;
import com.example.opet.infoshopping.Model.Cliente;
import com.example.opet.infoshopping.Repository.ClienteRepository;
import com.example.opet.infoshopping.Util.BancoUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.opet.infoshopping", appContext.getPackageName());
    }


    public void insereDado() throws Exception {

        //region Criando o Cliente
        Context appContext = InstrumentationRegistry.getTargetContext();
        SQLiteDatabase db;
        DatabaseFactory banco = null;
        banco = new DatabaseFactory(appContext);

        //inserts
        ClienteRepository clienteRepository = new ClienteRepository();
        Cliente cliente = new Cliente();
        cliente.setNome("Renan");
        cliente.setEmail("dell@mouse.com");
        cliente.setSenha("1584");
        cliente.setCPF(123456789);
        //endregion

        //region Buscando o Cliente no Banco de Dados
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNome());
        valores.put(BancoUtil.EMAIL, cliente.getEmail());
        valores.put(BancoUtil.SENHA, cliente.getSenha());
        valores.put(BancoUtil.CPF, cliente.getCPF());

        resultado = db.insert(BancoUtil.TABELA_CLIENTE, null, valores);
        db.close();

        assertTrue(resultado > 0);
        //endregion

    }

    @Test
    public void DeletandoDados() throws Exception {
        //Deletando o Empregado utilizando o ID como parametro

        //region Deletando o empregado
        Context appContext = InstrumentationRegistry.getTargetContext();
        SQLiteDatabase db;
        DatabaseFactory banco = new DatabaseFactory(appContext);
        ClienteRepository clienteRepository = new ClienteRepository();
        int id = 5;
        String where = BancoUtil.ID_CLIENTE + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(BancoUtil.TABELA_CLIENTE, where, null);
        db.close();
        //endregion

        //Verificando se ele realmente foi excluido

        //region Listando os Clientes
        //Listando os Clientes
        Cursor cursor;
        String[] campos = {BancoUtil.ID_CLIENTE, BancoUtil.NOME_CLIENTE, BancoUtil.EMAIL, BancoUtil.SENHA,
                BancoUtil.CPF};
        db = banco.getReadableDatabase();

        String condicao = BancoUtil.ID_CLIENTE + " = " + 5;

        cursor = db.query(BancoUtil.TABELA_CLIENTE, campos, condicao, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        //endregion

        //region Recuperando o Cliente listado com o ID passado para exclusão
        try {
            if (cursor.getCount() > 0) {
                assertTrue("Removido com sucesso", true);
            }
            else
                assertFalse("Ainda há registros com esse ID", true);

        } finally {
            cursor.close();
        }
        //endregion
    }


    @Test
    public void UpdateMetodo() throws Exception{

        //region Adicionandos os novos dados do Cliente
        Context appContext = InstrumentationRegistry.getTargetContext();
        SQLiteDatabase db;
        DatabaseFactory banco = null;

        banco = new DatabaseFactory(appContext);

        ContentValues valores;
        String where;

        Cliente cliente = new Cliente();
        cliente.setNome("Cuzon");
        cliente.setEmail("eita@poha");
        cliente.setSenha("Senhazinha");
        cliente.setCPF(3);
        cliente.setID(5);
        //endregion

        //region Fazendo Update
        db = banco.getWritableDatabase();

        where = BancoUtil.ID_CLIENTE + " = " + 5;

        valores = new ContentValues();

        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNome());
        valores.put(BancoUtil.EMAIL, cliente.getEmail());
        valores.put(BancoUtil.SENHA, cliente.getSenha());
        valores.put(BancoUtil.CPF, cliente.getCPF());

        int resultado = db.update(BancoUtil.TABELA_CLIENTE,valores,where,null);
        db.close();
        assertTrue(resultado > 0);
        //endregion

    }

}
