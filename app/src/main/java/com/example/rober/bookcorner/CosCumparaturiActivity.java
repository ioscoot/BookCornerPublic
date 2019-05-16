package com.example.rober.bookcorner;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rober.bookcorner.classes.BookList;
import com.example.rober.bookcorner.classes.Carte;
import com.example.rober.bookcorner.classes.CosCumparaturiListAdapter;

import java.util.ArrayList;

public class CosCumparaturiActivity extends AppCompatActivity {

    public static ArrayList<Carte> listaCartiCosCumparaturi = new ArrayList<>();
    ListView listView_cos_cumparaturi;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cos_cumparaturi);

        listView_cos_cumparaturi = findViewById(R.id.listView_cos_cumparaturi);
        floatingActionButton = findViewById(R.id.fab_activity_cos_cumparaturi);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaCartiCosCumparaturi.removeAll(listaCartiCosCumparaturi);
                Toast.makeText(CosCumparaturiActivity.this, "Comanda a fost plasata. Multumim!", Toast.LENGTH_LONG).show();
                displayListViewCosCumparaturi();
            }
        });

        displayListViewCosCumparaturi();
    }

    private void displayListViewCosCumparaturi() {
        CosCumparaturiListAdapter adapter = new CosCumparaturiListAdapter(CosCumparaturiActivity.this, listaCartiCosCumparaturi);
        listView_cos_cumparaturi.setAdapter(adapter);

        listView_cos_cumparaturi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
