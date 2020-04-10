package com.example.trial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String DECIMAL_MODE = "decimal_mode";
    public static final String DARK_MODE = "dark_mode";

    Button calculate_button;
    TextView output_text;
    //Initialise edit text fields to get user inputs
    EditText percent_text, amount_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount_text = findViewById(R.id.amount_text);
        percent_text = findViewById(R.id.percent_text);
        calculate_button = findViewById(R.id.calculate_button);
        output_text = findViewById(R.id.output_text);

        calculate_button.setOnClickListener(new View.OnClickListener() {
            //Handle calculate button click
            @Override
            public void onClick(View v) {
                //error checking to ensure the user input fields are not blank
                try {
                    float percentage = Float.parseFloat(percent_text.getText().toString());
                    float dec = percentage / 100;
                    float total = dec * Float.parseFloat(amount_text.getText().toString());
                    output_text.setText(String.format("%s %% of %s is %s", percentage, amount_text.getText(), total));

                } catch (NumberFormatException e) {
                    percent_text.setError("Enter percent");
                    amount_text.setError("Enter amount");
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        //Calls methods to apply current settings when MainActivity is resumed
        applySettings(this);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    static boolean getPrecisionPref(MainActivity mainActivity) {
        //Gets the precision setting preference
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("sharedPrefs", 0);
        return sharedPreferences.getBoolean("decimal_mode", false);
    }

    static void applySettings(MainActivity mainActivity) {
        //Finds the selected settings and applies the appropriate constraints to the app

        if (getPrecisionPref(mainActivity)) {
            //Set keyboard to decimals
            mainActivity.percent_text.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            mainActivity.amount_text.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        } else {
            //Set keyboard to numbers
            mainActivity.percent_text.setInputType(InputType.TYPE_CLASS_NUMBER);
            mainActivity.amount_text.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }
}
