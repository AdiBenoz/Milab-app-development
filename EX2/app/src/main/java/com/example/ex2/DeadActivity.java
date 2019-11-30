package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class DeadActivity extends AppCompatActivity {

    int[] Images = {R.drawable.d, R.drawable.g, R.drawable.lex, R.drawable.ms, R.drawable.denny};
    String[] Names = {"Derek", "George", "Lexie", "Mark", "Denny"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dead);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(DeadActivity.this, Names, Images));
    }
}
