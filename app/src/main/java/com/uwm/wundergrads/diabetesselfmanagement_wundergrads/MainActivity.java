package com.uwm.wundergrads.diabetesselfmanagement_wundergrads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        // This goes to DietFilter class no matter what button is clicked. Need to figure out how to go to
        // specific class based on what button was pressed.
        Intent intent = new Intent(MainActivity.this, DietFilter.class);
        startActivity(intent);
    }
}
