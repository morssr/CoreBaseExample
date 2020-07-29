package sr.mls.com.corebase

import android.app.Application
import androidx.annotation.Keep
import com.facebook.stetho.Stetho

@Keep
open class CoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }
}