package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AliveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alive);

        List<Personality> alive = new ArrayList<>();
        alive.add(new Personality(R.drawable.mere1, "Meredith"));
        alive.add(new Personality(R.drawable.alex, "Alex"));
        alive.add(new Personality(R.drawable.ez, "EZ"));
        alive.add(new Personality(R.drawable.cris, "Christina"));
        alive.add(new Personality(R.drawable.jack, "Jackson"));
        alive.add(new Personality(R.drawable.bailey, "Bailey"));
        alive.add(new Personality(R.drawable.em, "Emilia"));
        alive.add(new Personality(R.drawable.april, "April"));

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(AliveActivity.this, alive));
    }
}
