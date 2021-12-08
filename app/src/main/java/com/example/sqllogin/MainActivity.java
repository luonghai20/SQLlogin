package com.example.sqllogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    EditText edUsername,edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPassword=findViewById(R.id.tvPassword);
        edUsername=findViewById(R.id.tvUsername);

        initData();
    }

    private void initData() {
        database=openOrCreateDatabase("account.db",MODE_PRIVATE,null);
        String sql="CREATE TABLE IF NOT EXISTS thongtinsv (id integer primary key autoincrement, username text, password text)";
        database.execSQL(sql);
    }

    public void check_click(View view) {
        check();
    }

    private void check() {
        if (edUsername.getText().toString().isEmpty()){
            Toast.makeText(this,"Nhập username",Toast.LENGTH_SHORT).show();
        }else if (edUsername.getText().toString().contains(" ")){
            Toast.makeText(this,"username chứa dấu cách",Toast.LENGTH_SHORT).show();
        }else if (edPassword.getText().toString().isEmpty()){
            Toast.makeText(this,"nhập password",Toast.LENGTH_SHORT).show();
        }else if (edPassword.getText().toString().length()<6){
            Toast.makeText(this,"password không hợp lệ",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Hợp lệ",Toast.LENGTH_SHORT).show();
        }
    }

    public void login_click(View view) {
        insertData();
        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        Bundle bundle=new Bundle();
        bundle.putString("username",edUsername.getText().toString());
        bundle.putString("password",edPassword.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);

    }
    public void insertData(){
        ContentValues values=new ContentValues();
        values.put("username",edUsername.getText().toString());
        values.put("password",edPassword.getText().toString());
        database.insert("thongtinsv",null,values);
    }
}