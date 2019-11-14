package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private EditText name;
    private EditText number;
    private EditText email;
    private myDBAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.edEnterName);
        number = (EditText) findViewById(R.id.edPhone);
        email = (EditText) findViewById(R.id.edEnterEmail);
        helper = new myDBAdapter(this);
    }

    public void addFriend(View v) {
        if (name.getText().toString().isEmpty() || number.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty String Error!", Toast.LENGTH_SHORT).show();
        } else {
            if(checkDataValidation() && emailValidation()) {
                long id = helper.insertData(name.getText().toString(), number.getText().toString(), email.getText().toString());
                if (id <= 0) {
                    Toast.makeText(this, "Database Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "" + name.getText().toString() + " has been Friendzoned", Toast.LENGTH_SHORT).show();
                    name.setText(""); email.setText(""); number.setText("");
                }
            }
        }
    }

    public void home(View v) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    public boolean checkDataValidation() {
        ArrayList<Friends> friendsArrayList = new ArrayList<Friends>();
        friendsArrayList = helper.getAllFriends();

        for(Friends f: friendsArrayList) {
            if(f.getName().equalsIgnoreCase(name.getText().toString())) {
                Toast.makeText(this, "Error: Already have a friend with Name: " + name.getText().toString(), Toast.LENGTH_SHORT).show();
                name.setText("");
                return false;
            }
            else if(f.getNumber().equalsIgnoreCase(number.getText().toString())) {
                Toast.makeText(this, "Error: Already have a friend with Number: " + number.getText().toString(), Toast.LENGTH_SHORT).show();
                number.setText("");
                return false;
            }
            else if(f.getEmail().equalsIgnoreCase(email.getText().toString())) {
                Toast.makeText(this, "Error: Already have a friend with email: " + email.getText().toString(), Toast.LENGTH_SHORT).show();
                email.setText("");
                return false;
            }
        }
        return true;
    }

    public boolean emailValidation() {
        String emailPattern = "[a-zA-Z0-9.-_]+@[a-zA-Z]+\\.+[a-z]+";
        if(email.getText().toString().matches(emailPattern)) { return true;}
        Toast.makeText(this, "Error: invalid email: " + email.getText().toString(), Toast.LENGTH_SHORT).show();
        return false;
    }


}
