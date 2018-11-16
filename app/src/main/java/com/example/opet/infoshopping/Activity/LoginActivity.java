package com.example.opet.infoshopping.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.example.opet.infoshopping.R;
import com.example.opet.infoshopping.Repository.ClienteRepository;
import com.example.opet.infoshopping.Util.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by opet on 04/06/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editLogin;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        editLogin = findViewById(R.id.editEmailLogin);
        editSenha = findViewById(R.id.editSenhaLogin);

    }

    public void logarUsuario (View view){

        String sEmail = editLogin.getText().toString();
        String sSenha = editSenha.getText().toString();

        mAuth.signInWithEmailAndPassword(sEmail, sSenha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Sucesso", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Falha", Toast.LENGTH_SHORT).show();
                        }
                    }
                } );
    }

    /*public void logarUsuario(View v){
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();

        ClienteRepository clienteRepository = new ClienteRepository(this);
        long idUsuario = clienteRepository.validaCliente(login, Util.toMD5(senha));
        if(idUsuario > 0){
            Toast.makeText(this, "Usuário Logado com Sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("ID_CLIENTE",idUsuario);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Usuário não Cadastrado e/ou Senha Inválida", Toast.LENGTH_SHORT).show();
        }
    }*/
}
