package com.example.wsternlicht.odds;

import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;
import java.util.logging.Handler;


public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Toast.makeText(MainActivity.this, "Have you chosen your odds? Enter them below.", Toast.LENGTH_LONG).show();
    }

    public void calculateRandom(View view) {
        //get value from min and max
        EditText minView = (EditText) findViewById(R.id.min);
        EditText maxView = (EditText) findViewById(R.id.max);
        final String minString = minView.getText().toString();
        final String maxString = maxView.getText().toString();
        if (!checkIntegers(minString, maxString)) {
            return; //Wait for issue to be resolved so return to allow new button...
        }
        Toast.makeText(MainActivity.this, "Ready for your fate?", Toast.LENGTH_SHORT).show();
        //must be final so that we can change it at multiple times throughout the program.
        final TextView resultText = (TextView) findViewById(R.id.resultNumber);
        resultText.setText("...");
        new CountDownTimer(2300, 500) { //set timer so that you have to wait for your result!!
            @Override
            public void onTick(long millisUntilFinished) {
                //do nothing
            }

            @Override
            public void onFinish(
            ) {
                Integer.parseInt(minString);
                Integer.parseInt(maxString);
                int min = Integer.parseInt(minString);
                int max = Integer.parseInt(maxString);
                //calculate random with those variables
                Random rand = new Random();
                int resultNum = rand.nextInt((max + 1) - min) + min; //max + 1 so it's inclusive :)
                //print out the result to the resultNumber textbox
                resultText.setText(Integer.toString(resultNum));
            }
        }.start();
    }

    public boolean checkIntegers(String min, String max) {
        if (min.matches("")) {
            Toast.makeText(MainActivity.this, "Enter an Integer >= 0 in the Min Box!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (max.matches("")) {
            Toast.makeText(MainActivity.this, "Enter an Integer > 1 in the Max Box!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (Integer.parseInt(min) >= Integer.parseInt(max)) {
            Toast.makeText(MainActivity.this, "Min must be less than max!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.wsternlicht.odds/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.wsternlicht.odds/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

