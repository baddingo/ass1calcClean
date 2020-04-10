package com.example.trial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    //Set shared preferences that is used to save the state of objects
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String DECIMAL_MODE = "decimal_mode";
    public static final String SHORTHAND_NOTATION = "shorthand_notation";
    public static final String DATASIZE_SPINNER = "datasize_spinner";
    public static final String TRANSFER_RATE_SPINNER = "transfer_rate_spinner";

    Button calculate_button;
    EditText percent_text, amount_text;
    TextView output_text;
    double display_datasize_in_MB = 0.0;
    double transferRate = 0.0;

    //Initialise strings to store spinner selections
    String datasize_notation;
    String transferRate_notation;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set screen orientation to only be portrait & content to be displayed on startup
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        amount_text = findViewById(R.id.amount_text);
        percent_text = findViewById(R.id.percent_text);
        calculate_button = findViewById(R.id.calculate_button);
        output_text = findViewById(R.id.output_text);

        calculate_button.setOnClickListener(new View.OnClickListener() {

            //            Handle calculate button click
            @Override
            public void onClick(View v) {
                try {
                    //error checking to ensure the user input fields are not blank

                    float percentage = Float.parseFloat(percent_text.getText().toString());
                    float dec = percentage / 100;
                    float total = dec * Float.parseFloat(amount_text.getText().toString());
                    output_text.setText(String.format("%s %% of %s is %s", percentage, amount_text.getText(), total));

                } catch(NumberFormatException e ) {
                    percent_text.setError("Enter percent");
                    amount_text.setError("Enter amount");
                }
            }
        });
//Create toolbar object and set it
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set/get the layout variables & set the key listeners for the app
        DecimalConverter.getLayoutVariables(this);
        Utilities.setKeyListeners(this);
    }

    @Override
    protected void onResume() {
        //Calls methods to apply current settings and load the spinners when MainActivity is resumed
        Utilities.applySettings(this);

        super.onResume();
    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here.
            int id = item.getItemId();

            //Start settings activity when settings is selected
            if (id == R.id.action_settings) {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
            }
            return super.onOptionsItemSelected(item);
        }

    public void onClick(View view) {
        //Calls appropriate class methods when calculate time button is pressed
        Utilities.hideKeyboard(MainActivity.this);
        DecimalConverter.displayTime(this);
    }
    }
