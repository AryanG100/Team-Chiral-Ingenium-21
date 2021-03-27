package com.hingorani.chiral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class stats extends AppCompatActivity {

    DatabaseReference referenceRoll;
    DatabaseReference referencePitch;
    ArrayList<Long> anglesRoll = new ArrayList<>();
    ArrayList<Long> serialsRoll = new ArrayList<>();
    LineChart graphRoll;
    ArrayList<Long> anglesPitch = new ArrayList<>();
    ArrayList<Long> serialsPitch = new ArrayList<>();
    LineChart graphPitch;
    TextView name;
    TextView age;
    TextView gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        name = findViewById(R.id.nameText);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);

        Intent intent = getIntent();
        name.setText("Name: "+intent.getStringExtra("name"));
        age.setText("Age: "+intent.getIntExtra("age",60));
        gender.setText("Gender: "+ intent.getStringExtra("gender"));

        referenceRoll = FirebaseDatabase.getInstance().getReference("Roll");
        referenceRoll.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                anglesRoll.clear();
                serialsRoll.clear();
                long i = 1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //serialsRoll.add((long) snapshot.child("serial").getValue());
                    serialsRoll.add(i++);
                    anglesRoll.add((long) snapshot.child("angle").getValue());
                }
                displayGraphRoll();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        referencePitch = FirebaseDatabase.getInstance().getReference("Pitch");
        referencePitch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                anglesPitch.clear();
                serialsPitch.clear();
                long i = 1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //serialsPitch.add((long) snapshot.child("serial").getValue());
                    serialsPitch.add(i++);
                    anglesPitch.add((long) snapshot.child("angle").getValue());
                }
                displayGraphPitch();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void displayGraphRoll(){
        graphRoll = findViewById(R.id.graph);
        List<Entry> entries = new ArrayList<Entry>();
        for (int i =0;i<anglesRoll.size();i++) {
            entries.add(new Entry(serialsRoll.get(i),anglesRoll.get(i)));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Spoon tremors Roll");
        dataSet.setColor(R.color.colorPrimaryDark);
        dataSet.setLineWidth(3f);
        dataSet.setValueTextSize(10f);
        dataSet.setValueTextColor(Color.YELLOW);
        LineData lineData = new LineData(dataSet);
        graphRoll.setData(lineData);
        graphRoll.invalidate();
    }

    public void displayGraphPitch(){
        graphPitch = findViewById(R.id.graphPitch);
        List<Entry> entries = new ArrayList<Entry>();
        for (int i =0;i<anglesPitch.size();i++) {
            entries.add(new Entry(serialsPitch.get(i),anglesPitch.get(i)));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Spoon tremors Pitch");
        dataSet.setColor(R.color.colorPrimaryDark);
        dataSet.setLineWidth(3f);
        dataSet.setValueTextSize(10f);
        dataSet.setValueTextColor(Color.YELLOW);

        LineData lineData = new LineData(dataSet);
        graphPitch.setData(lineData);
        graphPitch.invalidate();
    }
}