package com.example.opet.infoshopping.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.opet.infoshopping.Model.Cliente;
import com.example.opet.infoshopping.R;
import com.facebook.stetho.Stetho;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by opet on 09/11/2018.
 */

public class UserActivity extends AppCompatActivity {

    Spinner estadoSpinner;
    Spinner cidadeSpinner;
    public static Cliente clienteLogado;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Stetho.initializeWithDefaults(this);

        estadoSpinner = findViewById(R.id.spinnerEstado);
        cidadeSpinner = findViewById(R.id.spinnerCidade);

        cidadeSpinner.setEnabled(false);

        estadoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    String selecionada = estadoSpinner.getSelectedItem().toString();
                    String[] cidades = getResources().getStringArray(R.array.cidades_array);
                    switch (selecionada){
                        case "Paraná":
                            cidades  = getResources().getStringArray(R.array.cidades_array_parana);
                            break;
                        case "São Paulo":
                            cidades  = getResources().getStringArray(R.array.cidades_array_sao_paulo);
                            break;
                    }

                    ArrayAdapter yourarray = new ArrayAdapter(UserActivity.this,android.R.layout.simple_spinner_item, cidades);
                    yourarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cidadeSpinner.setAdapter(yourarray);
                    cidadeSpinner.setEnabled(true);
                }
                else{
                    cidadeSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cidadeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    String selecionada = cidadeSpinner.getSelectedItem().toString();
                    Toast.makeText(UserActivity.this, selecionada, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void pesquisar (View view){
        String selecionada = cidadeSpinner.getSelectedItem().toString();
        switch (selecionada){
            case "Curitiba":
                Intent intent = new Intent(this, ShoppingsActivity.class);
                startActivity(intent);
                break;
            case "São Paulo":
                Toast.makeText(UserActivity.this, "Cidade ainda não implementada", Toast.LENGTH_SHORT).show();
                break;
            case "Campinas":
                Toast.makeText(UserActivity.this, "Cidade ainda não implementada", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void editarDados(View view){

        Intent intent = new Intent(UserActivity.this, EditarActivity.class);
        startActivity(intent);
    }

}