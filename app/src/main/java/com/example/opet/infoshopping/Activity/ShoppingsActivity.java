package com.example.opet.infoshopping.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.opet.infoshopping.R;

/**
 * Created by opet on 06/06/2018.
 */

public class ShoppingsActivity extends AppCompatActivity {

    GridLayout shoppingGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppings);

        shoppingGrid = (GridLayout) findViewById(R.id.shoppingGrid);

        //Setar o Evento

        setSingleEvent(shoppingGrid);
    }

    private void setSingleEvent(GridLayout shoppingGrid) {
        for (int i = 0; i < shoppingGrid.getChildCount(); i++) {
            CardView cardView = (CardView) shoppingGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) // abrir activity 1
                    {
                        Intent intent = new Intent(ShoppingsActivity.this, LojasEstacaoActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 1) // abrir activity 2
                    {
                        Intent intent = new Intent(ShoppingsActivity.this, LojasPalladiumActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ShoppingsActivity.this, "Activity ainda nao setada ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
