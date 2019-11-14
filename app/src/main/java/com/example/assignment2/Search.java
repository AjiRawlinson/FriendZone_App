package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    EditText search;
    EditText name, number, email;
    Friends aFriend;
    ArrayList<Friends> friendsArrayList;
    myDBAdapter dbAdapter;
    Boolean foundFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        name = (EditText) findViewById(R.id.etResultName);
        number = (EditText) findViewById(R.id.etResultNumber);
        email = (EditText) findViewById(R.id.etResultEmail);
        search = (EditText) findViewById((R.id.etSearch));
        name.setEnabled(false);
        number.setEnabled(false);
        email.setEnabled(false);
        dbAdapter = new myDBAdapter(this);
        friendsArrayList = dbAdapter.getAllFriends();
        foundFriend = false;
        aFriend = null;
    }

    public void search(View v) {
        name.setText(""); name.setEnabled(true);
        number.setText(""); number.setEnabled(true);
        email.setText(""); email.setEnabled(true);
        foundFriend = false;
        aFriend = null;
        for(Friends f: friendsArrayList) {
            if(f.getName().equalsIgnoreCase(search.getText().toString()) || f.getNumber().equalsIgnoreCase(search.getText().toString())) {
                name.setText(f.getName());
                number.setText(f.getNumber());
                email.setText(f.getEmail());
                foundFriend = true;
                aFriend = f;
            }
        }
        if(!foundFriend) {
            Toast.makeText(this, "No Friend found with name: " + search.getText().toString(), Toast.LENGTH_SHORT).show();
        }
        search.setText("");
    }

    public void Edit(View v) {
        if(foundFriend) {
            if (name.getText().toString().isEmpty() || number.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Empty String Error!", Toast.LENGTH_SHORT).show();
            } else if(emailValidation()) {
                dbAdapter.updateFriend(aFriend, name.getText().toString(), number.getText().toString(), email.getText().toString());
                Toast.makeText(this, "Friendship updated with " + name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        } else { Toast.makeText(this, "Please search for Friend first", Toast.LENGTH_SHORT).show(); }
    }

    public void delete(View v) {
       if(foundFriend) {
            dbAdapter.removeFriend(aFriend);
           Toast.makeText(this, "Friendship Ended with " + aFriend.getName(), Toast.LENGTH_SHORT).show();
       } else { Toast.makeText(this, "Please search for Friend first", Toast.LENGTH_SHORT).show(); }
    }

    public void home(View v) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    public boolean emailValidation() {
        String emailPattern = "[a-zA-Z0-9.-_]+@[a-zA-Z]+\\.+[a-z]+";
        if(email.getText().toString().matches(emailPattern)) { return true;}
        Toast.makeText(this, "Error: invalid email: " + email.getText().toString(), Toast.LENGTH_SHORT).show();
        return false;
    }
}
