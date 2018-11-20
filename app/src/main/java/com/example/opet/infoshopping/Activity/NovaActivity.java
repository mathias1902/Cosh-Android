package com.example.opet.infoshopping.Activity;

import com.example.opet.infoshopping.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.AccessToken;

/**
 * Created by opet on 20/11/2018.
 */

public class NovaActivity extends Activity 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova);
    }

    public void sair(View v){
        AccessToken.setCurrentAccessToken(null);
        Intent loginIntent = new Intent(NovaActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
