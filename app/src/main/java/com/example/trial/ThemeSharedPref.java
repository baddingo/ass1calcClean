package com.example.trial;

import android.content.Context;
import android.content.SharedPreferences;

public class ThemeSharedPref {
    private SharedPreferences mySharedPref;

    ThemeSharedPref(Context context) {
        mySharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    //To save the night mode state as true or false.
    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode", state);
        editor.apply();
    }

    //To load the night or day mode.
    public Boolean loadNightModeState() {
        Boolean state = mySharedPref.getBoolean("NightMode", false);
        return state;
    }
}
