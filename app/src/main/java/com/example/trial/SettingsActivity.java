package com.example.trial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    //    switch variables
    private Switch precision_mode;
    private Switch theme_mode;
    private boolean precision_boolean;
    private boolean theme_boolean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Find switch views
        precision_mode = findViewById(R.id.decimal_switch);
        theme_mode = findViewById(R.id.theme_switch);

        loadData();
        updateViews();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onClickSaveData(View view) {
        //Saves the preferences whenever a switch is clicked
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MainActivity.DECIMAL_MODE, precision_mode.isChecked());
        editor.putBoolean(MainActivity.DARK_MODE, theme_mode.isChecked());
        editor.apply();
    }

    private void loadData() {
        //Loads data when activity is created
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, 0);
        precision_boolean = sharedPreferences.getBoolean(MainActivity.DECIMAL_MODE, false);
        theme_boolean = sharedPreferences.getBoolean(MainActivity.DARK_MODE, false);
    }

    private void updateViews() {
        //Updates switches state
        precision_mode.setChecked(precision_boolean);
        theme_mode.setChecked(theme_boolean);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
