package sr.mls.com.corebase.Utils;

import android.util.Log;

public class ConcurrencyUtil {

    private static final String TAG = "ConcurrencyUtil";

    public static void printThreadName() {
        Log.d(TAG, "printThreadName: " + Thread.currentThread().getName());
    }

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
