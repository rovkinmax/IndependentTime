package com.useit.independenttime;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created UseIT for  IndependentTime
 * User: maxrovkin
 * Date: 09.12.14
 * Time: 17:39
 */
class SettingsMaster
{
    private static final String FILE_SETTINGS = "prop";
    private static final String LOCAL_TIME = "LOCAL_TIME";
    private static final String SYSTEM_TIME = "SYSTEM_TIME";
    private static final String FLASH_BACK = "FLASH_BACK";


    private static SharedPreferences getPreference(final Context context)
    {
        return context.getSharedPreferences(FILE_SETTINGS, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(final Context context)
    {
        return getPreference(context).edit();
    }


    /**
     * Сохраняет вермя
     *
     * @param context контекст приложения для доступа к ресурсам
     * @param mls     время в UTC
     */
    public static void setTime(final Context context, final long mls)
    {
        getEditor(context).putLong(LOCAL_TIME, mls).apply();
    }

    /**
     * Возвращает время
     *
     * @param context контекст приложения для доступа к ресурсам
     *
     * @return возвращает всеря в UTC, если ничего не было записано, то 0
     */
    public static long getTime(final Context context)
    {
        return getPreference(context).getLong(LOCAL_TIME, 0);
    }


    /**
     * Сохраняет системное время
     *
     * @param context контекст приложения для доступа к ресурсам
     * @param mls     системное время в UTC
     */
    public static void setSystemTime(final Context context, final long mls)
    {
        getEditor(context).putLong(SYSTEM_TIME, mls).apply();
    }

    /**
     * Возвращает системное время
     *
     * @param context контекст приложения для доступа к ресурсам
     *
     * @return системное время в UTC, если ничего не было сохранено, то 0
     */
    public static long getSystemTime(final Context context)
    {
        return getPreference(context).getLong(SYSTEM_TIME, 0);
    }


    /**
     * Регистрирует, что время на устройстве было переведено назад
     *
     * @param context     контекст приложения для доступа к ресурсам
     * @param isFlashback
     */
    public static void setFlashBack(final Context context, final boolean isFlashback)
    {
        getEditor(context).putBoolean(FLASH_BACK, isFlashback).apply();
    }

    /**
     * Сообщает было ли переведено время на устройстве
     *
     * @param context контекст приложения для доступа к ресурсам
     *
     * @return
     */
    public static boolean isFlashBack(final Context context)
    {
        return getPreference(context).getBoolean(FLASH_BACK, false);
    }
}
