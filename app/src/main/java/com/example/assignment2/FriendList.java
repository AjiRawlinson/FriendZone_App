package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class FriendList extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Friends> friendsArrayList;
    myDBAdapter dbAdapter;
    myRVAdapter rvAdapter;
    RecyclerViewItemInterface rvii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        dbAdapter = new myDBAdapter(this);
        friendsArrayList = dbAdapter.getAllFriends();
        rv = (RecyclerView) findViewById(R.id.RecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        rvAdapter = new myRVAdapter(FriendList.this, friendsArrayList, rvii);
        rv.setAdapter(rvAdapter);
        DividerItemDecoration did = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        rv.addItemDecoration(did);

    }

    public void home(View v) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
