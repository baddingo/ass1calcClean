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

    private Switch decimal_mode;
    private Switch shorthand_notation;

    private boolean decimal;
    private boolean shorthand;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Find switch views
        decimal_mode = findViewById(R.id.decimal_switch);
//        shorthand_notation = findViewById(R.id.switch_shorthandNotation);

        loadData();
        updateViews();
    }

    private void loadData() {
        //Loads data when activity is created
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, 0);
        decimal = sharedPreferences.getBoolean(MainActivity.DECIMAL_MODE, false);
//        shorthand = sharedPreferences.getBoolean(MainActivity.SHORTHAND_NOTATION, false);
    }

    private void updateViews() {
        //Updates switches state
        decimal_mode.setChecked(decimal);
//        shorthand_notation.setChecked(shorthand);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        //Add back button to toolbar
//        onBackPressed();
//        return true;
//    }

    public void onClickSaveData(View view) {
        //Saves the preferences whenever a switch is clicked
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MainActivity.DECIMAL_MODE, decimal_mode.isChecked());
//        editor.putBoolean(MainActivity.SHORTHAND_NOTATION, shorthand_notation.isChecked());
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
