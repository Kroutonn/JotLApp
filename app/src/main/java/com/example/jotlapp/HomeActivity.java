package com.example.jotlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    // ui components
    Button mLoadButton;
    Button mCreateButton;

    // vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mLoadButton = findViewById(R.id.ActivityHome_Button_Load);
        mCreateButton = findViewById(R.id.ActivityHome_Button_Create);

        setupButtons();
    }

    private void setupButtons() {
        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoadHeroActivity.class);
                startActivity(intent);
            }
        });

        mCreateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomeActivity.this, CreateHeroActivity.class);
                startActivity(intent);
            }
        });
    }
}