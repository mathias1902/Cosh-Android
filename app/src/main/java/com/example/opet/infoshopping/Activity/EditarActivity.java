package com.example.opet.infoshopping.Activity;

import android.arch.persistence.room.Update;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opet.infoshopping.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;

/**
 * Created by opet on 09/11/2018.
 */

public class EditarActivity extends AppCompatActivity {

    private ImageView imgSelected;
    private StorageReference mStorageRef;
    private ArrayList<String> photos;
    private TextView textView;
    private EditText TextNome;
    private EditText TextEmail;
    private EditText TextSenha;
    String nome = null;
    String email = null;
    String senha = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        photos = new ArrayList<>();
        imgSelected = findViewById(R.id.imgSelected);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        textView = findViewById(R.id.textView);
        TextNome = findViewById(R.id.TextNome);
        TextEmail = findViewById(R.id.TextEmail);
        TextSenha = findViewById(R.id.TextSenha);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Toast.makeText(this, "Bem vindo " + user.getDisplayName() + " !" , Toast.LENGTH_SHORT).show();

        }
    }

    public void photoPickerFunction(View view){
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                imgSelected.setImageURI(Uri.parse(photos.get(0)));
            }
        }
    }

    private void resetForm(){
        photos.clear();
        imgSelected.setImageResource(0);
    }

    public void sendPhotoFunction(View view) {
            if (photos.size() > 0) {
                Uri file = Uri.fromFile(new File(photos.get(0)));
                StorageReference photoRef = mStorageRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                GetUser();
                if (email.equals("") || senha.equals("") || nome.equals("")) {

                    Toast.makeText(this, "Todos campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();

                }
                if (validarEmail(email) == false) {

                    Toast.makeText(this, "Por favor, preencha com um email válido!", Toast.LENGTH_SHORT).show();

                }
                else {
                    GetUser();
                    UpdateUser();
                    UpdateSenha();
                }
                CleanUser();
                photoRef.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(EditarActivity.this, "Arquivo Enviado com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditarActivity.this, "Falha ao enviar arquivo.", Toast.LENGTH_SHORT).show();
                    }
                });
                resetForm();
            } else {
                Toast.makeText(this, "Nenhum arquivo carregado.", Toast.LENGTH_SHORT).show();
            }
        }

    private boolean validarEmail(final String email) {

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;

    }

    private void GetUser(){
        nome = TextNome.getText().toString();
        email =  TextEmail.getText().toString();
        senha = TextSenha.getText().toString();

    }
    private void UpdateUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updateEmail(email);
        Uri image = Uri.parse(photos.get(0));
        user.updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(nome).setPhotoUri(image).build());
    }

    private void UpdateSenha() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        senha = TextSenha.getText().toString();

        user.updatePassword(senha).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(EditarActivity.this, "Sucesso", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(EditarActivity.this, "Falha", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void CleanUser(){
        TextNome.setText(null);
        TextEmail.setText(null);
        TextSenha.setText(null);
    }
}
