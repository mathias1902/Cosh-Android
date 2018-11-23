package com.example.opet.infoshopping.Activity;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.opet.infoshopping.Model.Cidade;
import com.example.opet.infoshopping.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by opet on 23/11/2018.
 */

public class RemoverCidadeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nomeCidade;
    private DatabaseReference mDatabase;
    private Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remover_cidade);

        nomeCidade = findViewById(R.id.nomeCidade);
        btnExcluir = findViewById(R.id.btnSalvar);
        btnExcluir.setOnClickListener(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    private String remove = nomeCidade.getText().toString();

    public void removerCidade(Cidade cidade) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = mDatabase.child("cidades").orderByChild("nome").equalTo(remove);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        Cidade cidade = new Cidade();
        removerCidade(cidade);
    }
}
