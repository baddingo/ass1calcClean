package com.example.trial;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button calculate_button;
    EditText percent_text, amount_text;
    TextView output_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

            Toolbar toolbar = findViewById(R.id.toolbar);

            setSupportActionBar(toolbar);
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
            }
            return super.onOptionsItemSelected(item);
        }
    }
