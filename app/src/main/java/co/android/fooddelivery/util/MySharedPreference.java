package co.android.fooddelivery.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created on 7/19/19.
 */

public class MySharedPreference {

    private static MySharedPreference INSTANCE;
    private static Object object = new Object();

    private static final String MY_PRIVATE = "my_pref";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefEditor;

    public MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(MY_PRIVATE, Context.MODE_PRIVATE);
    }

    public static MySharedPreference getInstance(Context context) {
        synchronized (object) {
            if (INSTANCE == null) {
                INSTANCE = new MySharedPreference(context);
            }
        }
        return INSTANCE;
    }

    public void savePreference(long key, boolean value) {
        // check for boundry conditions
        prefEditor = sharedPreferences.edit();
        prefEditor.putBoolean(String.valueOf(key), value);
        prefEditor.commit();
    }

    public boolean getPreference(long key) {
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.getBoolean(String.valueOf(key), false);
    }

}
