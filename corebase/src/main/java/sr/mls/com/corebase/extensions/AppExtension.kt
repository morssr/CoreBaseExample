package sr.mls.com.corebase.extensions

import android.app.Activity
import android.preference.PreferenceManager

private const val KEY_FIRST_START = "first-start"

fun Activity.isAppFirstStart(): Boolean {
    val sp = PreferenceManager.getDefaultSharedPreferences(this)
    val firstStart = sp.getBoolean(KEY_FIRST_START, true)
    if (firstStart) {
        sp.edit().putBoolean(KEY_FIRST_START, false).apply()
    }
    return firstStart
}