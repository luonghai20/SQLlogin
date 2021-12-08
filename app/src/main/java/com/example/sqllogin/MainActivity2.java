package com.example.sqllogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView edUsername2,edPassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edUsername2=findViewById(R.id.tvUsername2);
        edPassword2=findViewById(R.id.tvPassword2);

        Bundle bundle=getIntent().getExtras();

        edUsername2.setText(bundle.getString("username","get username fail"));
        edPassword2.setText(bundle.getString("password","get password fail"));
    }
}