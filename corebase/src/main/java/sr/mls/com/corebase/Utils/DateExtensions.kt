package sr.mls.com.corebase.Utils

import android.text.format.DateUtils
import java.util.*

private const val TAG = "Extensions"

fun Date.isToday(): Boolean {
    return DateUtils.isToday(this.time)
}

fun Date.isTomorrow(): Boolean {
    return DateUtils.isToday(this.time - DateUtils.DAY_IN_MILLIS)
}

fun Date.isYesterday(): Boolean {
    return DateUtils.isToday(this.time + DateUtils.DAY_IN_MILLIS)
}