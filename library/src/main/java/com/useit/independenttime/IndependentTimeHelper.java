package com.useit.independenttime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Класс, который отвечает за ведение независимого времени на устройстве внутри приложения
 */
public class IndependentTimeHelper
{
    public static void setServerTime(final Context context, final long serverTime)
    {
        stopTimer(context);
        SettingsMaster.setTime(context, serverTime);
        SettingsMaster.setSystemTime(context,System.currentTimeMillis());
        SettingsMaster.setFlashBack(context, false);
        startTimer(context);
    }

    /**
     * Запускает счетчик, который будет вести вермя на устройстве
     *
     * @param context контест для доступа к ресурсам
     */
    static void startTimer(final Context context)
    {
        final Intent intent = new Intent(context, TimeReceiver.class);
        intent.setAction(TimeReceiver.ACTION_TO_UPDATE_TIME);

        if (PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE) == null)
        {
            final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + TimeReceiver.TIME_PERIOD, TimeReceiver.TIME_PERIOD, pendingIntent);
        }
    }

    /**
     * Останавливает счетчичк, который ведет время на устройстве
     *
     * @param context контест для доступа к ресурсам
     */
    static void stopTimer(final Context context)
    {
        final Intent intent = new Intent(context, TimeReceiver.class);
        intent.setAction(TimeReceiver.ACTION_TO_UPDATE_TIME);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE);
        if (pendingIntent != null)
        {
            final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    /**
     * get independent time.
     * Если было зафиксирована попытка перевести время назад, то будет возвращено отрицательное значение
     *
     * @param context контест для доступа к ресурсам
     */
    public static long getTime(final Context context)
    {
        if (SettingsMaster.isFlashBack(context))
            return -1;

        return SettingsMaster.getTime(context);
    }

}
