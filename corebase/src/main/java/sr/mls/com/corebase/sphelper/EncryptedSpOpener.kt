package sr.mls.com.corebase.sphelper

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.rx2.rxSingle
import java.io.IOException
import java.security.GeneralSecurityException

object EncryptedSpOpener {

    @JvmStatic
    fun initSpAsync(spName: String, context: Context): Single<SharedPreferences> =
        GlobalScope.rxSingle {
            initSp(
                spName,
                context
            )
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    @JvmStatic
    fun initSp(spName: String, context: Context): SharedPreferences {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            getEncryptedSP(
                spName,
                context
            )
        else getSharedSP(
            spName,
            context
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @Throws(GeneralSecurityException::class, IOException::class)

    private fun getMasterKeyAlias(context: Context): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @Throws(GeneralSecurityException::class, IOException::class)
    private fun getEncryptedSP(spName: String, context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            spName,
            getMasterKeyAlias(
                context
            ),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun getSharedSP(spName: String, context: Context): SharedPreferences {
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }
}
