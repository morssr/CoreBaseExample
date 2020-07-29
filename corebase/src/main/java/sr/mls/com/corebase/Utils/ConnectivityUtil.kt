package sr.mls.com.corebase.Utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


//https://medium.com/@hissain.khan/android-reachability-check-internet-connection-programmatically-3221805f6587
object ConnectivityUtil {

    private const val TAG = "ConnectivityUtil"

    private const val GOOGLE_SERVER = "https://www.google.com"

    @SuppressLint("MissingPermission")
    fun hasNetworkAvailable(context: Context): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        Log.d(TAG, "hasNetworkAvailable: ${(network != null)}")
        return (network?.isConnected) ?: false
    }

    fun hasInternetConnected(context: Context): Boolean {
        if (hasNetworkAvailable(
                context
            )
        ) {
            try {
                val connection = URL(GOOGLE_SERVER).openConnection() as HttpURLConnection
                connection.setRequestProperty("User-Agent", "Test")
                connection.setRequestProperty("Connection", "close")
                connection.connectTimeout = 1500
                connection.connect()
                Log.d(TAG, "hasInternetConnected: ${(connection.responseCode == 200)}")
                return (connection.responseCode == 200)
            } catch (e: IOException) {
                Log.e(TAG, "Error checking internet connection", e)
            }
        } else {
            Log.w(TAG, "No network available!")
        }
        Log.d(TAG, "hasInternetConnected: false")
        return false
    }

    fun hasServerConnected(context: Context): Boolean {
        if (hasNetworkAvailable(
                context
            )
        ) {
            try {
                val connection = URL(GOOGLE_SERVER).openConnection() as HttpURLConnection
                connection.setRequestProperty("User-Agent", "Test")
                connection.setRequestProperty("Connection", "close")
                connection.connectTimeout = 1500
                connection.connect()
                Log.d(TAG, "hasServerConnected: ${(connection.responseCode == 200)}")
                return (connection.responseCode == 200)
            } catch (e: IOException) {
                Log.e(TAG, "Error checking internet connection", e)
            }
        } else {
            Log.w(TAG, "Server is unavailable!")
        }
        Log.d(TAG, "hasServerConnected: false")
        return false
    }


    fun showInternetConnectionLost(context: Context) {
        try {
            val dialog =
                AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert)
                    .create()

            dialog.setTitle("No Internet Connection")
            dialog.setMessage("Please make sure your Wi-Fi or mobile data is turned on, then try again.")
            dialog.setCancelable(false)
            dialog.setButton(
                DialogInterface.BUTTON_POSITIVE,
                "OK"
            ) { _, _ -> dialog.dismiss() }
            dialog.show()
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }
}