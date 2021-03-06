package com.example.opet.infoshopping.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.infoshopping.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
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

        mAuth = FirebaseAuth.getInstance();

    }

    public void logarUsuario (View view){

        String sEmail = editLogin.getText().toString();
        String sSenha = editSenha.getText().toString();

        if (sEmail.equals("") || sSenha.equals("")) {

            Toast.makeText(this, "Todos campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();

        }

        if (validarEmail(sEmail) == false) {

            Toast.makeText(this, "Por favor, digite um email válido.", Toast.LENGTH_SHORT).show();

        }
        else {
            mAuth.signInWithEmailAndPassword(sEmail, sSenha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Sucesso", Toast.LENGTH_SHORT).show();

                                CleanUser();

                                Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Falha", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } );

        }


    }

    private boolean validarEmail(final String email) {

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;

    }

    private void CleanUser(){
        editLogin.setText(null);
        editSenha.setText(null);
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
