package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void search(View v){
        Intent in = new Intent(this, Search.class);
        startActivity(in);
    }

    public void list(View v){
        Intent in = new Intent(this, FriendList.class);
        startActivity(in);
    }

    public void save(View v) {
        Intent in = new Intent(this, Register.class);
        startActivity(in);
    }
}
