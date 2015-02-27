package com.edaaslanoba.fuelmeapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class OldDataActivity extends ActionBarActivity {

    //saved entries
    private String gallon;
    private String mileage;
    private String price;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_data);

        //Font paths
        String fontPath = "fonts/digital-7.ttf"; /*digital font*/

        //Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        //labels
        TextView textViewPrice = (TextView) findViewById(R.id.textViewPrice);
        TextView textViewGallon = (TextView) findViewById(R.id.textViewGallon);
        TextView textViewMileage = (TextView) findViewById(R.id.textViewMileage);
        TextView textViewDate = (TextView) findViewById(R.id.textViewDate);

        //Apply font
        textViewPrice.setTypeface(tf);
        textViewGallon.setTypeface(tf);
        textViewMileage.setTypeface(tf);
        textViewDate.setTypeface(tf);

        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        //retrieve data saved
        date = preferences.getString("DateEntries", "");
        TextView textDate = (TextView) findViewById(R.id.textDate);
        textDate.setText(date);

        price = preferences.getString("PriceEntries", "");
        TextView textPrice = (TextView) findViewById(R.id.textPrice);
        textPrice.setText(price);

        gallon = preferences.getString("GallonEntries", "");
        TextView textGallon = (TextView) findViewById(R.id.textGallon);
        textGallon.setText(gallon);

        mileage = preferences.getString("MileageEntries", "");
        TextView textMileage = (TextView) findViewById(R.id.textMileage);
        textMileage.setText(mileage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_old_data, menu);
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
}
