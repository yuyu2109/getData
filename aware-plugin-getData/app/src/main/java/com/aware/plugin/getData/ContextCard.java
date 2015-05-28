package com.aware.plugin.getData;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.aware.utils.IContextCard;

public class ContextCard implements IContextCard {

    //Empty constructor used to instantiate this card
    public ContextCard(){}

    //You may use sContext on uiChanger to do queries to databases, etc.
    private Context sContext;

    //Declare here all the UI elements you'll be accessing
    private View card;
    private TextView text;

    //Used to load your context card
    private LayoutInflater sInflater;

    @Override
    public View getContextCard(Context context) {
        sContext = context;

         //Load card information to memory
        sInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        card = sInflater.inflate(R.layout.card, null);

        //Initialize UI elements from the card
        text = (TextView) card.findViewById(R.id.text);

        long start = System.currentTimeMillis() - 20000;
        long end = System.currentTimeMillis() - 100000;

        String s = DataHandler.getAccelerometerData(context.getContentResolver(), start, end);
        if (s != null)
            text.setText(s);
        else
            text.setText("Erreur");

        //Return the card to AWARE/apps
        return card;
    }
}
