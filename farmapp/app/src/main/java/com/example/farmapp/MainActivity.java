package com.example.farmapp;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button myPlantButton;
    private Button plantButton;
    private Button bugButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPlantButton = findViewById(R.id.activity_main_myPlantButton);
        plantButton = findViewById(R.id.activity_main_plantButton);
        bugButton = findViewById(R.id.activity_main_bugButton);

        myPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), myPlant.class);
                startActivity(intent);
            }
        });

        plantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), plant.class);
                startActivity(intent);
            }
        });

        bugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), bug.class);
                startActivity(intent);
            }
        });

    }
}