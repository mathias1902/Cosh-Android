package com.example.opet.infoshopping.Activity;

import com.example.opet.infoshopping.Model.Cliente;
import com.example.opet.infoshopping.R;
import com.example.opet.infoshopping.Repository.ClienteRepository;
import com.example.opet.infoshopping.Util.Util;
import com.example.opet.infoshopping.Model.Cliente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by opet on 04/06/2018.
 */


public class CadastroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText editNome;
    EditText editTextEmail;
    EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        mAuth = FirebaseAuth.getInstance();
        editNome = (EditText) findViewById(R.id.editNome);
        editTextEmail = (EditText) findViewById(R.id.editEmail);
        editTextSenha = (EditText) findViewById(R.id.editSenha);
    }

    public void gravarCliente(View v) {

        final String nome = editNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (task.isSuccessful()) {

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(nome).build();
                            user.updateProfile(profileUpdates);

                            Toast.makeText(CadastroActivity.this, "Usuário cadastro com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CadastroActivity.this, "Falha no cadastro!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void voltarTela(View v) {

        Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
