package sr.mls.com.corebase.Utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

object VectorDrawableUtils {

    fun getDrawable(context: Context, drawableResId: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableResId)
    }

    fun getDrawable(context: Context, drawableResId: Int, colorFilter: Int): Drawable {
        val drawable =
            getDrawable(
                context,
                drawableResId
            )
        drawable!!.setColorFilter(colorFilter, PorterDuff.Mode.SRC_IN)
        return drawable
    }

    fun getBitmap(context: Context, drawableId: Int): Bitmap {
        val drawable =
            getDrawable(
                context,
                drawableId
            )

        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }
}