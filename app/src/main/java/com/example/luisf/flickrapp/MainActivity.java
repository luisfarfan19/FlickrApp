package com.example.luisf.flickrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lvCategories = (ListView) findViewById(R.id.lvCategories);
        ArrayList<String> categoriesList = new ArrayList<String>();
        categoriesList.add("Playa");
        categoriesList.add("Football");
        categoriesList.add("Ciudad");
        categoriesList.add("Mexico");
        /*ArrayList<Category> categoriesList = new ArrayList<Category>();
        categoriesList.add(new Category("Playa", "Playa"));
        categoriesList.add(new Category("Football", "Football"));
        categoriesList.add(new Category("Ciudad", "Ciudad"));
        categoriesList.add(new Category("Mexico", "Mexico"));*/

        //ArrayAdapter<Category> adapter1 = new CustomAdapter(this, categoriesList);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, categoriesList
        );
        lvCategories.setAdapter(adapter1);

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category = (String) parent.getItemAtPosition(position);
                Intent intent1 = new Intent(MainActivity.this, Activity2.class);
                intent1.putExtra("category", category);
                startActivity(intent1);
            }
        });
    }
}
