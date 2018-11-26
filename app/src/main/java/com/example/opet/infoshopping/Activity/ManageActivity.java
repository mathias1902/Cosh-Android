package com.example.opet.infoshopping.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.opet.infoshopping.Model.Cidade;
import com.example.opet.infoshopping.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 23/11/2018.
 */

public class ManageActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editNomeCidade;
    private Spinner spinnerEstado;
    private Button btnSalvar;

    private DatabaseReference mDatabase;

    private List<String> estados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        editNomeCidade = findViewById(R.id.editNomeCidade);
        btnSalvar = findViewById(R.id.btnSalvar);
        spinnerEstado = findViewById(R.id.spinnerEstado);
        btnSalvar.setOnClickListener(this);
        estados = new ArrayList<String>();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    protected void onStart(){
        super.onStart();

        carregarListaEstados();
    }

    public void onClick(View view) {
        Cidade cidade = new Cidade();

        if (spinnerEstado.getSelectedItem().toString().equals("Escolha um estado")) {

            Toast.makeText(this, "É necessário escolher um estado.", Toast.LENGTH_SHORT).show();

        }
        else {
            cidade.setNome(editNomeCidade.getText().toString());
            cidade.setEstado(spinnerEstado.getSelectedItem().toString());
            salvarNovaCidade(cidade);
        }
    }

    private void salvarNovaCidade(Cidade cidade) {

        if (cidade.getNome().equals("")) {

            Toast.makeText(this, "O campo cidade deve ser preenchido.", Toast.LENGTH_SHORT).show();

        }
        else{
            mDatabase.child("cidades").child(String.valueOf(cidade.generateTimeStamp())).setValue(cidade)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ManageActivity.this, "Cidade Salva!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ManageActivity.this, "Erro ao Salvar a cidade...", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    private String remove = "";

    private void removerCidade(Cidade cidade) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = mDatabase.child("cidades").orderByChild("nome").equalTo(remove);
    }

    public void removerTela(View view){

        Intent intent = new Intent(ManageActivity.this, RemoverCidadeActivity.class);
        startActivity(intent);
    }

    public void carregarListaEstados() {
        Query mQuery = mDatabase.child("estados");
        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                estados.clear();
                for (DataSnapshot estadosSnapshot : dataSnapshot.getChildren()) {
                    estados.add(estadosSnapshot.getValue(String.class));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManageActivity.this, android.R.layout.simple_spinner_item, estados);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerEstado.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
