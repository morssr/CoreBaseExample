package sr.mls.com.corebase.Utils

/*Used as a wrapper for data that is exposed via a LiveData that represents an
 event.*/

//https://proandroiddev.com/livedata-with-single-events-2395dea972a8

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}