package com.example.opet.infoshopping.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.opet.infoshopping.Model.Cliente;
import com.example.opet.infoshopping.R;
import com.example.opet.infoshopping.Repository.ClienteRepository;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    Spinner estadoSpinner;
    Spinner cidadeSpinner;
    public static Cliente clienteLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastrar(View view){

        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
    }


    public void logar(View view){

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}
