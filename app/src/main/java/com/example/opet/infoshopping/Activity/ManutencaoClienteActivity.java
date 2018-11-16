package com.example.opet.infoshopping.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.infoshopping.Model.Cliente;
import com.example.opet.infoshopping.R;
import com.example.opet.infoshopping.Repository.ClienteRepository;

/**
 * Created by opet on 19/06/2018.
 */

public class ManutencaoClienteActivity extends Activity {
    private int ID_CLIENTE;
    private ClienteRepository clienteRepository;
    private Cliente cliente;

    private EditText editNome;
    private EditText editCPF;
    private EditText editEmail;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manutencao_cliente);

        Intent intent = getIntent();
        if(intent.hasExtra("ID_CLIENTE")){
            ID_CLIENTE = intent.getIntExtra("ID_CLIENTE",0);
        }
        clienteRepository = new ClienteRepository(this);
        cliente = clienteRepository.carregaClientePorID(ID_CLIENTE);

        editNome = (EditText) findViewById(R.id.atualizarNome);
        editEmail = (EditText) findViewById(R.id.atualizarEMAIL);
        editSenha = (EditText) findViewById(R.id.atualizarSENHA);
        editCPF = (EditText) findViewById(R.id.atualizarCPF);

        editNome.setText(cliente.getNome());
        editEmail.setText(cliente.getEmail());
        Long l = cliente.getCPF();
        editCPF.setText(l.toString());
        editSenha.setText(cliente.getSenha());
    }
    public void atualizarCliente(View v){
        cliente.setNome(editNome.getText().toString());
        cliente.setCPF(Long.parseLong(editCPF.getText().toString()));
        cliente.setEmail(editEmail.getText().toString());
        cliente.setSenha(editSenha.getText().toString());

        if(clienteRepository.atualizaCliente(cliente))
            Toast.makeText(ManutencaoClienteActivity.this, "Cliente atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ManutencaoClienteActivity.this, "Erro ao atualizar cliente.", Toast.LENGTH_SHORT).show();
        homepage();
    }

    public void removerCliente(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirma_msg);
        builder.setMessage(R.string.titulo_remove);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clienteRepository.deletaRegistro(ID_CLIENTE);
                Toast.makeText(ManutencaoClienteActivity.this, "Cliente removido com sucesso.", Toast.LENGTH_SHORT).show();
                homepage();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }








    //Metodo para retornar para Home Page.
    private void homepage() {
        Intent homepage = new Intent(ManutencaoClienteActivity.this,MainActivity.class);
        startActivity(homepage);
        finish();
    }
}
