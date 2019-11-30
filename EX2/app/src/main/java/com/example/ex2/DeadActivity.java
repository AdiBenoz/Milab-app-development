package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DeadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dead);


        List<Personality> dead = new ArrayList<>();
        dead.add(new Personality(R.drawable.d, "Derek"));
        dead.add(new Personality(R.drawable.g, "George"));
        dead.add(new Personality(R.drawable.lex, "Lexie"));
        dead.add(new Personality(R.drawable.ms, "Mark"));
        dead.add(new Personality(R.drawable.denny, "Denny"));


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(DeadActivity.this, dead));
    }
}
