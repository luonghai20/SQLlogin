package com.example.sqllogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    EditText edUsername,edPassword,edConPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPassword=findViewById(R.id.tvPassword);
        edUsername=findViewById(R.id.tvUsername);
        edConPassword=findViewById(R.id.tvConPassword);

        initData();
    }

    private void initData() {
        database=openOrCreateDatabase("account.db",MODE_PRIVATE,null);
        String sql="CREATE TABLE IF NOT EXISTS tblogin (id integer primary key autoincrement, username text, password text)";
        database.execSQL(sql);
    }

    private void check() {
        if (edUsername.getText().toString().isEmpty()){
            Toast.makeText(this,"Nhập username",Toast.LENGTH_SHORT).show();
        }else if (edUsername.getText().toString().contains(" ")){
            Toast.makeText(this,"username không được chứa dấu cách",Toast.LENGTH_SHORT).show();
        }else if (edPassword.getText().toString().isEmpty()){
            Toast.makeText(this,"nhập password",Toast.LENGTH_SHORT).show();
        }else if (edPassword.getText().toString().length()<6){
            Toast.makeText(this,"password phải lớn hơn 6 kí tự",Toast.LENGTH_SHORT).show();
        }else if(edConPassword.getText().toString().equals(edPassword.getText().toString())==false){
            Toast.makeText(this,"password không trùng khớp",Toast.LENGTH_SHORT).show();
        }
    }

    public void login_click(View view) {
        check();
        if (checkAccount()){
            Toast.makeText(MainActivity.this,"tài khoản đã tồn tại",Toast.LENGTH_SHORT).show();
        }else{
            insertData();
            Toast.makeText(MainActivity.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
            Bundle bundle=new Bundle();
            bundle.putString("username",edUsername.getText().toString());
            bundle.putString("password",edPassword.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    public void insertData(){
        ContentValues values=new ContentValues();
        values.put("username",edUsername.getText().toString());
        values.put("password",edPassword.getText().toString());
        database.insert("tblogin",null,values);
    }

    public boolean checkAccount(){
        int count=0;
        String sql="SELECT * FROM tblogin WHERE username ='"+edUsername.getText().toString()+"'";
        Cursor cursor=database.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            count++;
            cursor.moveToNext();
        }
        cursor.close();
        if (count>0){
            return true;
        }
        return false;
    }

}