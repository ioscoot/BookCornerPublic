package com.example.rober.bookcorner;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;

import com.example.rober.bookcorner.classes.Carte;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implementation of App Widget functionality.
 */
public class BookCornerWidget extends AppWidgetProvider {
    static Carte randomBook = new Carte();
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        getRandomBook();
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.book_corner_widget);

        Intent intent = new Intent(context, DisplayBookActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0 );
        remoteViews.setOnClickPendingIntent(R.id.appwidget_titlu, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void getRandomBook() {
        myRef = database.getReference(getRandomCategorie());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Carte> bookList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Carte carte = snapshot.getValue(Carte.class);
                    bookList.add(carte);
                }

                int randomItem = getRandomPosition(bookList);
                BookCornerWidget.randomBook = bookList.get(randomItem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static String getRandomCategorie() {
        ArrayList<String> listaCategorii = new ArrayList<>();
        listaCategorii.add("sanatate");
        listaCategorii.add("afaceri");
        listaCategorii.add("dezvoltarePersonala");
        listaCategorii.add("fictiune");

        int randomInt = ThreadLocalRandom.current().nextInt(0, listaCategorii.size()-1);

        return listaCategorii.get(randomInt);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static int getRandomPosition(ArrayList<Carte> list){
        int randomInt = ThreadLocalRandom.current().nextInt(0, list.size()-1);

        return randomInt;
    }
}

