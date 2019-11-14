package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myRVAdapter extends RecyclerView.Adapter<myRVAdapter.MyRVHolder> {

    Context context;
    ArrayList<Friends> friendsArrayList;
    private RecyclerViewItemInterface callBack;

    public myRVAdapter(Context context, ArrayList<Friends> list, RecyclerViewItemInterface rvii) {
        this.context = context;
        this.friendsArrayList = list;
        this.callBack = rvii;

    }


    @NonNull
    @Override
    public MyRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        MyRVHolder rvh = new MyRVHolder(v);
        return rvh;
    }


    @Override
    public void onBindViewHolder(@NonNull MyRVHolder holder, final int position) {
        holder.name.setText(friendsArrayList.get(position).getName());
        holder.number.setText(friendsArrayList.get(position).getNumber());
        holder.email.setText(friendsArrayList.get(position).getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, friendsArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return friendsArrayList.size();
    }


    public class MyRVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView number;
        TextView email;
        public MyRVHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.rowName);
            number = (TextView) itemView.findViewById(R.id.rowNumber);
            email = (TextView) itemView.findViewById(R.id.rowEmail);
        }

        @Override
        public void onClick(View v) {
            callBack.onItemClicked(getAdapterPosition());
        }
    }
}
