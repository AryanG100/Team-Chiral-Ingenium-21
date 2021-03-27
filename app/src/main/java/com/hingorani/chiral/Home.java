package com.hingorani.chiral;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    String[] names = {"Vishal Hingorani","Chirag Chaturvedi","Saloni Das","Aryan Gupta","Riya Shah","Dhruv Mehta","Neeti Sharma","Harjot Singh"};
    static int[] ages = {62,55,76,86,72,52,49,77};
    static String[] gender = {"Male","Male","Female","Male","Female","Male","Female","Male"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(Home.this,LinearLayoutManager.VERTICAL,false);
        recyclerViewAdapter = new RecyclerViewAdapter(this,names);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}