package sr.mls.com.corebase.Utils

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.os.StatFs


object SystemUtil {

    /**
     * @return the current device disk space in MB
     */
    @JvmStatic
    fun getAvailableDiskPlaceMB(): Long {
        val stat = StatFs(Environment.getExternalStorageDirectory().path)
        val bytesAvailable =
            stat.blockSizeLong * stat.blockCountLong
        return bytesAvailable / 1048576
    }

    /**
     * @return true if there is more or equal space as the given value
     */
    @JvmStatic
    fun isDiskSpaceAvailable(requiredMB: Long): Boolean {
        return getAvailableDiskPlaceMB() >= requiredMB
    }

    @JvmStatic
    fun isComponentEnabled(name: ComponentName, context: Context): Boolean {
        val status = context.packageManager.getComponentEnabledSetting(name)
        return status == PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    }
}