package com.example.rober.bookcorner.classes;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rober.bookcorner.R;

import java.util.ArrayList;

public class CosCumparaturiListAdapter extends ArrayAdapter<Carte> {
    private Activity context;
    private ArrayList<Carte> bookList;


    public CosCumparaturiListAdapter(Activity context, ArrayList<Carte> bookList) {
        super(context, R.layout.list_cos_cumparaturi_layout, bookList);

        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_cos_cumparaturi_layout, null, true);

        TextView textViewTitlu = (TextView) listViewItem.findViewById(R.id.textView_item_list_cos_titlu);
        TextView textViewCantitate = (TextView) listViewItem.findViewById(R.id.textView_item_list_cos_cantitate);

        Carte carte = bookList.get(position);

        textViewTitlu.setText(carte.getTitlu());
        textViewCantitate.setText(String.valueOf(carte.getCantitate()));

        return listViewItem;

    }
}
