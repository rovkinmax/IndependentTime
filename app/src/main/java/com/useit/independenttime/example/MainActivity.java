package com.useit.independenttime.example;

import android.app.Activity;
import android.os.Bundle;
import com.useit.independenttime.IndependentTimeHelper;


public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //update server time
        final long someServerTime = System.currentTimeMillis();
        IndependentTimeHelper.setServerTime(this, someServerTime);

        //get current time
        final long localTime = IndependentTimeHelper.getTime(this);
    }
}
