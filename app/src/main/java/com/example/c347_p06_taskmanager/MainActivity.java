package com.example.c347_p06_taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Button btnAdd;
    ArrayList<Task> tasks;
    ArrayAdapter<Task> aaTasks;
    ScrollView sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lvTask);
       // sv = findViewById(R.id.sv);
        btnAdd = findViewById(R.id.btnAdd);

        DBHelper db = new DBHelper(MainActivity.this);
        tasks = db.getAllTask();
        aaTasks = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, tasks);
        lv.setAdapter(aaTasks);
        aaTasks.notifyDataSetChanged();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddTask.class);
                startActivityForResult(intent, 1);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);
        DBHelper db = new DBHelper(MainActivity.this);

        tasks.clear();
        tasks = db.getAllTask();
        aaTasks = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, tasks);
        lv.setAdapter(aaTasks);


    }

}

