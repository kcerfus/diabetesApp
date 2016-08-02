package com.uwm.wundergrads.diabetesselfmanagement_wundergrads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    private Button BGL_button, Diet_button, Exercise_button, Medication_button, Regimen_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BGL_button = (Button) findViewById(R.id.bgl_button);
        BGL_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){ startActivity(new Intent(getApplicationContext(), BGLInput.class)); }
        });

        Diet_button = (Button) findViewById(R.id.diet_button);
        Diet_button.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){ startActivity(new Intent(getApplicationContext(), DietInput.class)); }
        });

        Exercise_button = (Button) findViewById(R.id.exercise_button);
        Exercise_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){ startActivity(new Intent(getApplicationContext(), ExerciseInput.class)); }
        });

        Medication_button = (Button) findViewById(R.id.medication_button);
        Medication_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){ startActivity(new Intent(getApplicationContext(), MedicationInput.class)); }
        });

        Regimen_button = (Button) findViewById(R.id.reg_button);
        Regimen_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){ startActivity(new Intent(getApplicationContext(), ExerciseInput.class)); }
        });
    }
}
