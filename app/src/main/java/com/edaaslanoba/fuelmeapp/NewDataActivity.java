package com.edaaslanoba.fuelmeapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NewDataActivity extends ActionBarActivity {

    //new entries
    private String gallonEntry;
    private String mileageEntry;
    private String priceEntry;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);

        //Font paths
        String fontPath = "fonts/digital-7.ttf"; /*digital font*/

        //Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        //display current date
        // create a date object for testing
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");

        // create a date instance using default style
        String currentDateTime = format.format(date);

        TextView textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewDate.setText(currentDateTime);
        Log.d("MYLOG", "Date is: " + currentDateTime);

        //labels
        Button saveButton = (Button) findViewById(R.id.buttonSave);
        TextView textViewPrice = (TextView) findViewById(R.id.textViewPrice);
        TextView textViewGallon = (TextView) findViewById(R.id.textViewGallon);
        TextView textViewMileage = (TextView) findViewById(R.id.textViewMileage);
        TextView textViewEntry = (TextView) findViewById(R.id.textViewEntryFor);

        //Apply font
        saveButton.setTypeface(tf);
        textViewPrice.setTypeface(tf);
        textViewGallon.setTypeface(tf);
        textViewMileage.setTypeface(tf);
        textViewDate.setTypeface(tf);
        textViewEntry.setTypeface(tf);

    }

    /**
     * get each new entry the user adds and save the data
     * @param v
     */
    public void saveNewEntry(View v) {

        //get each new entry
        TextView todaysDate = (TextView) findViewById(R.id.textViewDate);
        date = todaysDate.getText().toString();

        EditText editPrice = (EditText) findViewById(R.id.editPrice);
        priceEntry = editPrice.getText().toString();

        EditText editGallon = (EditText) findViewById(R.id.editGallon);
        gallonEntry = editGallon.getText().toString();

        EditText editMileage = (EditText) findViewById(R.id.editMileage);
        mileageEntry = editMileage.getText().toString();

        //edit shared preferences to save new entries
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Editor editor = preferences.edit();

        String previousDateEntries = preferences.getString("DateEntries", "");
        String previousPriceEntries = preferences.getString("PriceEntries", "");
        String previousGallonEntries = preferences.getString("GallonEntries", "");
        String previousMileageEntries = preferences.getString("MileageEntries", "");

        //append previous entries to be displayed with the most recent entry on the saved data page
        editor.putString("DateEntries", date + "\n" + previousDateEntries);
        editor.putString("PriceEntries", priceEntry + "\n" + previousPriceEntries);
        editor.putString("GallonEntries", gallonEntry + "\n" + previousGallonEntries);
        editor.putString("MileageEntries", mileageEntry + "\n" + previousMileageEntries);

        //no field should be left empty - warn user in such case
        if (date.isEmpty() || priceEntry.isEmpty() || gallonEntry.isEmpty() || mileageEntry.isEmpty()) {
            Toast.makeText(this, "Go back and enter missing fields :)", Toast.LENGTH_LONG).show();
        } else {
            editor.commit();
            Toast.makeText(this, "New entry saved", Toast.LENGTH_SHORT).show();
            finish(); //finish activity to go back to homepage once the entry is saved
        }

        Log.d("MYLOG", preferences.getString("DateEntries", ""));
        Log.d("MYLOG", preferences.getString("PriceEntries", ""));
        Log.d("MYLOG", preferences.getString("GallonEntries", ""));
        Log.d("MYLOG", preferences.getString("MileageEntries", ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_data, menu);
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
