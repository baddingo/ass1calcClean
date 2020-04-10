package com.example.trial;

import android.widget.EditText;
import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testPercentCalculations() {
        //assign local values
        String percent_text = "10";
        String amount_text = "100";
        //test the percent maths is correct
        float percentage = Float.parseFloat(percent_text);
        float dec = percentage / 100;
        float total = dec * Float.parseFloat(amount_text);
        assertEquals(10.0, total, 0);
    }

    @Test
    public void testDisplay() {
        float percentage = 10;
        int amount_text = 100;
//        Create a sting of what is outputted in the app
        String test_string = String.format("%s %% of %s is ", percentage, amount_text);
//        Test the string is what we want it to be based on the variables provided
        assertEquals("10.0 % of 100 is ", test_string);
    }
}