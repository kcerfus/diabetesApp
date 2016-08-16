package com.uwm.wundergrads.diabetesselfmanagement_wundergrads;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import layout.BGLInput;
import layout.BGLQuery;
import layout.DietInput;
import layout.DietQuery;
import layout.ExerciseInput;
import layout.ExerciseQuery;
import layout.MedicationInput;
import layout.MedicationQuery;

public class InputQuery extends AppCompatActivity implements BGLInput.OnFragmentInteractionListener, BGLQuery.OnFragmentInteractionListener, DietInput.OnFragmentInteractionListener,
    DietQuery.OnFragmentInteractionListener, ExerciseInput.OnFragmentInteractionListener, ExerciseQuery.OnFragmentInteractionListener, MedicationInput.OnFragmentInteractionListener,
    MedicationQuery.OnFragmentInteractionListener
    {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private String mode;
    private Button dietButton, exerciseButton, medButton, bglButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_query);

        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        setButtons();
    }

    public void setButtons() {
        // Trying to remove clutter from onCreate, all categories will have a submit button which are what these buttons are
        dietButton = (Button) findViewById(R.id.ButtonSubmitDiet);
        medButton = (Button) findViewById(R.id.ButtonSubmitMedication);
        exerciseButton = (Button) findViewById(R.id.ButtonSubmitExercise);
        bglButton = (Button) findViewById(R.id.ButtonSubmitBGL);

        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(dietButton);
        buttons.add(medButton);
        buttons.add(exerciseButton);
        buttons.add(bglButton);

        // Create a listener for each button
        for(Button button : buttons){
            button.setOnClickListener((new View.OnClickListener() {
                public void onClick(View v) {
                    // Get the values from text fields to pass to database

                    // need to instantiate a FeedReaderContract object here and do a switch statement based on button id
                    switch(v.getId()){
                        case R.id.ButtonSubmitBGL:
                            // send BGL inputs textField Values to database using BloodGlucoseMeasurements tables insert method in FeedReaderContract
                        case R.id.ButtonSubmitDiet:
                            // send Diet inputs textField Values to database using Diet tables insert method in FeedReaderContract
                        case R.id.ButtonSubmitExercise:
                            // send Exercise inputs textField Values to database using Exercise tables insert method in FeedReaderContract
                        case R.id.ButtonSubmitMedication:
                            // send Medication inputs textField Values to database using Exercise tables insert method in FeedReaderContract
                    }
                   /* long id;
                    int calories = Integer.parseInt(findViewById(R.id.EditTextDietInput).toString());
                    String food = findViewById(R.id.EditFoodInput).toString();
                    String date = findViewById(R.id.EditTextDate).toString();
                    String time = findViewById(R.id.EditTextTime).toString();*/
                }
            }));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_query, menu);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0: //first tab
                    switch(mode){
                        case "BGL": return BGLInput.newInstance("","");
                        case "Diet": return DietInput.newInstance("", "");
                        case "Exercise" : return ExerciseInput.newInstance("","");
                        case "Medication" : return MedicationInput.newInstance("","");
                        default: return null;
                    }
                case 1: //second tab
                    switch(mode){
                        case "BGL": return BGLQuery.newInstance("","");
                        case "Diet": return DietQuery.newInstance("", "");
                        case "Exercise" : return ExerciseQuery.newInstance("","");
                        case "Medication" : return MedicationQuery.newInstance("","");
                        default: return null;
                    }
                default: return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: //title of first tab
                    return mode + " Input";
                case 1: //title of second tab
                    return mode + " Query";
            }
            return null;
        }
    }
}