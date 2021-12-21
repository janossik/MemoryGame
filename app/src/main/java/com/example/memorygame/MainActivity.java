package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
private Button btn4x4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn4x4 = findViewById(R.id.btn4x4);
        btn4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGame4x4();

            }
        });
    }
    public void openGame4x4(){
        Intent intent = new Intent(this,Game4x4.class);
        startActivity(intent);
    }
}