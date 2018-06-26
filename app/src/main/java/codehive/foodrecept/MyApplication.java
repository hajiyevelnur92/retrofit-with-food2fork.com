package codehive.foodrecept;

import android.content.Context;
import android.preference.PreferenceManager;

public class MyApplication {
    public static boolean isIntroShown(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("isIntroShown", false);
    }

    public static void setIntroShown(Context context, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("isIntroShown", value).apply();
    }
}
