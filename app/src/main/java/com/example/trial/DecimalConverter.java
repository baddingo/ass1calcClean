package com.example.trial;

import android.os.Build;
import android.text.Html;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.Locale;

public class DecimalConverter {


    @RequiresApi(api = Build.VERSION_CODES.N)
    static void displayTime(MainActivity mainActivity) {
        //Updates the text views on screen based on user inputs

        //Call methods to acquire all relevant variables
        getLayoutVariables(mainActivity);
//        calculateStatements(mainActivity);

        //Initialise used text views
        TextView transfer_statement = mainActivity.findViewById(R.id.output_text);
//        TextView decimal_output_text = mainActivity.findViewById(R.id.output_text);
        String data_statement;
        String calculate_statement;

        //If precision mode on, display data_statement accordingly
        if (Utilities.getPrecisionPref(mainActivity)) {
            data_statement = String.format(Locale.ENGLISH,
                    "<html><font color=blue>%.2f</font> %s at <font color=blue>%.2f</font> %s will take</html>",
                    mainActivity.display_datasize_in_MB, mainActivity.datasize_notation, mainActivity.transferRate, mainActivity.transferRate_notation);
            transfer_statement.setText(Html.fromHtml(data_statement, 0));
        }

        //If precision mode off, display data_statement accordingly
        else {
            data_statement = String.format(Locale.ENGLISH,
                    "<html><font color=blue>%.0f</font> %s at <font color=blue>%.0f</font> %s will take</html>",
                    mainActivity.display_datasize_in_MB, mainActivity.datasize_notation, mainActivity.transferRate, mainActivity.transferRate_notation);
            transfer_statement.setText(Html.fromHtml(data_statement, 0));
        }

        //Display calculated time
        calculate_statement = String.format(Locale.ENGLISH, "<html>~ <b>%d</b> Hours <b>%d</b> Minutes <b>%d</b> Seconds",
                mainActivity.percent_text, mainActivity.amount_text);
        transfer_statement.setText(Html.fromHtml(calculate_statement, 0));
    }

    static void getLayoutVariables(MainActivity mainActivity) {

        //Get current entered inputs
        mainActivity.amount_text = mainActivity.findViewById(R.id.amount_text);
        mainActivity.percent_text = mainActivity.findViewById(R.id.percent_text);
    }
}
