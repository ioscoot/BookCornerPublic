package com.example.rober.bookcorner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rober.bookcorner.classes.BookList;
import com.example.rober.bookcorner.classes.Carte;
import com.example.rober.bookcorner.classes.CosCumparaturiListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    ListView listViewBooks;
    List<Carte> bookList = new ArrayList<>();
    TextView textView_main_numar_produse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.textView_main_numar_produse = findViewById(R.id.textView_main_numar_produse);

        myRef = database.getReference("afaceri");
        listViewBooks = findViewById(R.id.listViewBooks);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CosCumparaturiActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onStart() {
        super.onStart();
        this.textView_main_numar_produse.setText(String.valueOf(CosCumparaturiActivity.listaCartiCosCumparaturi.size()));
        String categorie = getIntent().getStringExtra("NUME_CATEGORIE");

        if (Objects.nonNull(categorie)) {
            myRef = database.getReference(categorie);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    bookList.clear();
                    for(DataSnapshot d : dataSnapshot.getChildren()) {
                        Carte carte = d.getValue(Carte.class);
                        bookList.add(carte);
                    }

                    BookList adapter = new BookList(MainActivity.this,bookList);
                    listViewBooks.setAdapter(adapter);

                    listViewBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(MainActivity.this, DisplayBookActivity.class);
                            intent.putExtra("Carte", bookList.get(position));
                            startActivity(intent);
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // TODO: 5/2/2019 Adaugare noi activitati 
        if (id == R.id.nav_categorie_sanatate) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("NUME_CATEGORIE", "sanatate");
            startActivity(intent);
        } else if (id == R.id.nav_categorie_dezvoltare_personala) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("NUME_CATEGORIE", "dezvoltarePersonala");
            startActivity(intent);
        } else if (id == R.id.nav_categorie_actiune) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("NUME_CATEGORIE", "fictiune");
            startActivity(intent);
        } else if (id == R.id.nav_categorie_afaceri) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("NUME_CATEGORIE", "afaceri");
            startActivity(intent);
        } else if (id == R.id.nav_categorie_locatii) {
            Intent intent = new Intent(getBaseContext(), LocatiiActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
