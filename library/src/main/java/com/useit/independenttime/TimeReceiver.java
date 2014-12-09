package com.useit.independenttime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeReceiver extends BroadcastReceiver
{
    public static final String ACTION_TO_UPDATE_TIME = "com.useit.independenttime.ACTION_TO_UPDATE_TIME";
    public static final long TIME_PERIOD = 30 * 1000;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (SettingsMaster.getTime(context) <= 0)
        {
            IndependentTimeHelper.stopTimer(context);
            return;
        }


        final String action = intent.getAction();
        if (action.equals(Intent.ACTION_BOOT_COMPLETED))
            startReceiverAfterBootComplete(context);

        if (action.equals(Intent.ACTION_SHUTDOWN))
            SettingsMaster.setSystemTime(context, System.currentTimeMillis());

        if (action.equals(ACTION_TO_UPDATE_TIME))
            incrementTimeAndSaveSystemTime(context);
    }


    private void startReceiverAfterBootComplete(final Context context)
    {

        final long systemTime = SettingsMaster.getSystemTime(context);
        if (systemTime > 0)
        {
            final long offTime = System.currentTimeMillis() - systemTime;

            if (offTime <= 0)
                SettingsMaster.setFlashBack(context, true);

            final long localTime = SettingsMaster.getTime(context);
            final long newLocalTime = localTime + offTime;
            SettingsMaster.setTime(context, newLocalTime);
            IndependentTimeHelper.startTimer(context);
        }

    }


    private void incrementTimeAndSaveSystemTime(final Context context)
    {
        final long localTime = SettingsMaster.getTime(context) + TIME_PERIOD;
        SettingsMaster.setTime(context, localTime);
        SettingsMaster.setSystemTime(context, System.currentTimeMillis());
    }
}
