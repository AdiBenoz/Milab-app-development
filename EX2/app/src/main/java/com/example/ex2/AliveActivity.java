package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AliveActivity extends AppCompatActivity {

    int[] Images = {R.drawable.mere1, R.drawable.alex, R.drawable.ez, R.drawable.cris, R.drawable.jack};
    String[] Names = {"Meredith", "Alex", "EZ", "Christina", "Jackson"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alive);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(AliveActivity.this, Names, Images));
    }
}
