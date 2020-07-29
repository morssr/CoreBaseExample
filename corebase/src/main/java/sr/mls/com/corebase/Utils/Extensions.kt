package sr.mls.com.corebase.Utils

import androidx.viewpager2.widget.ViewPager2

private const val TAG = "Extensions"

/**
 * Jumps to the next slide.
 * @return false if is the last slide.
 */
fun ViewPager2.jumpToNext(): Boolean {
    return if (currentItem == adapter?.itemCount?.minus(1)) false
    else {
        currentItem++
        true
    }
}