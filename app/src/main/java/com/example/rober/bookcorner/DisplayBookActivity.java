package com.example.rober.bookcorner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rober.bookcorner.classes.Carte;

import java.util.Objects;

public class DisplayBookActivity extends AppCompatActivity {

    TextView textView_display_book_autor;
    TextView textView_display_book_titlu;
    TextView textView_display_book_aprecieri;
    TextView textView_display_book_descriere;
    TextView textView_display_book_cantitate_adaugata;
    TextView textView_display_book_cantitate;
    TextView textView_display_book_numar_produse;

    Button button_display_book_minus;
    Button button_display_book_plus;
    Button button_display_book_add;
    private Carte carte;
    private int cantitateAdaugata = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_book);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.textView_display_book_autor = findViewById(R.id.textView_display_book_autor);
        this.textView_display_book_titlu = findViewById(R.id.textView_display_book_titlu);
        this.textView_display_book_aprecieri = findViewById(R.id.textView_display_book_aprecieri);
        this.textView_display_book_descriere = findViewById(R.id.textView_display_book_descriere);
        this.textView_display_book_cantitate_adaugata = findViewById(R.id.textView_display_book_cantitate_adaugata);
        this.textView_display_book_cantitate_adaugata.setText(String.valueOf(cantitateAdaugata));
        this.textView_display_book_cantitate = findViewById(R.id.textView_display_book_cantitate);
        this.textView_display_book_numar_produse = findViewById(R.id.textView_display_book_numar_produse);
        this.updateContorNumarProduse();

        populareInterfata();

        this.button_display_book_minus = findViewById(R.id.button_display_book_minus);
        this.button_display_book_plus = findViewById(R.id.button_display_book_plus);
        this.button_display_book_add = findViewById(R.id.button_display_book_add);

        FloatingActionButton fab = findViewById(R.id.fab_activity_display_book);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CosCumparaturiActivity.class);
                startActivity(intent);
            }
        });

        button_display_book_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scadeCantitate();
            }
        });

        button_display_book_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaugaCantitate();
            }
        });

        button_display_book_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carte.setCantitate(Integer.parseInt(textView_display_book_cantitate_adaugata.getText().toString()));
                CosCumparaturiActivity.listaCartiCosCumparaturi.add(carte);
                updateContorNumarProduse();
            }
        });
    }

    private void populareInterfata() {
        getCarteFromIntent();
        if (this.carte != null) {
            this.textView_display_book_autor.setText(carte.getAutor());
            this.textView_display_book_titlu.setText(carte.getTitlu());
            this.textView_display_book_aprecieri.setText(String.valueOf(carte.getNrAprecieri()));
            this.textView_display_book_descriere.setText(carte.getDescriere());
            this.textView_display_book_cantitate.setText(String.valueOf(carte.getCantitate()));
        } else {
            if (BookCornerWidget.randomBook != null) {
                this.carte = BookCornerWidget.randomBook;
                this.textView_display_book_autor.setText(carte.getAutor());
                this.textView_display_book_titlu.setText(carte.getTitlu());
                this.textView_display_book_aprecieri.setText(String.valueOf(carte.getNrAprecieri()));
                this.textView_display_book_descriere.setText(carte.getDescriere());
                this.textView_display_book_cantitate.setText(String.valueOf(carte.getCantitate()));
            }
        }

    }

    private void updateContorNumarProduse() {
        this.textView_display_book_numar_produse.setText(String.valueOf(CosCumparaturiActivity.listaCartiCosCumparaturi.size()));
    }

    private void getCarteFromIntent() {
        this.carte = (Carte) getIntent().getSerializableExtra("Carte");
    }

    private void scadeCantitate() {
        if (!(this.cantitateAdaugata == 1)) {
            this.cantitateAdaugata--;
            this.textView_display_book_cantitate_adaugata.setText(String.valueOf(this.cantitateAdaugata));
        }
    }

    private void adaugaCantitate() {
        if (!(this.cantitateAdaugata == this.carte.getCantitate())) {
            this.cantitateAdaugata++;
            this.textView_display_book_cantitate_adaugata.setText(String.valueOf(this.cantitateAdaugata));
        } else {
            Toast.makeText(getBaseContext(), "Cantiatea maxima a fost atinsa", Toast.LENGTH_SHORT).show();
        }
    }

}
