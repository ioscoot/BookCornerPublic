package com.example.rober.bookcorner.classes;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rober.bookcorner.R;

import java.util.List;

public class BookList extends ArrayAdapter<Carte> {

    private Activity context;
    private List<Carte> bookList;

    public BookList(Activity context, List<Carte> bookList) {
        super(context, R.layout.list_layout, bookList);
        this.context = context;
        this.bookList = bookList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewAutor = (TextView) listViewItem.findViewById(R.id.textViewAutor);
        TextView textViewTitlu = (TextView) listViewItem.findViewById(R.id.textViewTitlu);

        Carte carte = bookList.get(position);

        textViewAutor.setText(carte.getAutor());
        textViewTitlu.setText(carte.getTitlu());

        return listViewItem;

    }
}
