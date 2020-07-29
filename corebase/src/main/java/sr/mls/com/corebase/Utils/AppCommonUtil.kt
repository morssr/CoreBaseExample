package sr.mls.com.corebase.Utils

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate

class AppCommonUtil {

    companion object {
        private const val TAG = "AppCommonUtil"

        @JvmStatic
        fun openAppPageInStore(context: Context) {
            val packageName = context.packageName
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$packageName")
                    )
                )
            } catch (e: Exception) {
                Log.w(TAG, "shareAppURL: App sharing failed: $e")
            }
        }

        @JvmStatic
        fun shareAppURL(appUrl: String, context: Context) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, appUrl)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }

        //TODO Add description image to rich share dialog
        @JvmStatic
        fun shareRichPreviewAppURL(
            appUrl: String,
            imageResId: Int,
            title: String,
            context: Context
        ) {
            val shareIntent = Intent.createChooser(Intent().apply {
                type = "text/plain"
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, appUrl)
                // (Optional) Here we're setting the title of the content
                putExtra(Intent.EXTRA_TITLE, title)


                val contentUri = Uri.Builder()
                    .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                    .authority(context.resources.getResourcePackageName(imageResId))
                    .appendPath(context.resources.getResourceTypeName(imageResId))
                    .appendPath(context.resources.getResourceEntryName(imageResId))
                    .build()

//                Log.d(TAG, "shareRichPreviewAppURL: $contentUri")

//                data = contentUri
//
//                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }, null)

            context.startActivity(shareIntent)
        }

        @JvmStatic
        fun composeEmail(addresses: Array<String>, subject: String, context: Context) {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, subject)
            }
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }

        @JvmStatic
        fun isUsingNightModeResources(context: Context): Boolean {
            return when (context.resources.configuration.uiMode and
                    Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> true
                Configuration.UI_MODE_NIGHT_NO -> false
                Configuration.UI_MODE_NIGHT_UNDEFINED -> false
                else -> false
            }
        }

        @JvmStatic
        fun forceDarkMode(darkMode: Boolean) {
            if (darkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}

