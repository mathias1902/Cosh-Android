package com.example.opet.infoshopping.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opet.infoshopping.Adapter.ClienteAdapter;
import com.example.opet.infoshopping.Model.Cliente;
import com.example.opet.infoshopping.R;
import com.example.opet.infoshopping.Repository.ClienteRepository;

import java.util.List;

/**
 * Created by opet on 19/06/2018.
 */

public class ListarClientesActivity extends Activity {

    private ListView listaClientes;
    private ClienteAdapter myAdapter;
    ClienteRepository clienteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clientes);


        carregarElementos();
    }

     public void carregarElementos() {
        listaClientes = (ListView) findViewById(R.id.listClientes);
        clienteRepository = new ClienteRepository(this);
        List<Cliente> clientes = clienteRepository.carregaDadosLista();
        myAdapter = new ClienteAdapter(this, R.layout.item_activity_cliente, clientes);
        listaClientes.setAdapter(myAdapter);
        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
                Cliente cliente = (Cliente) parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ListarClientesActivity.this, ManutencaoClienteActivity.class);
                atualizarIntent.putExtra("ID_CLIENTE", cliente.getID());
                startActivity(atualizarIntent);
            }
        });
    }
}

