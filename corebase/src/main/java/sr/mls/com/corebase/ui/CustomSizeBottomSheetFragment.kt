package sr.mls.com.corebase.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import sr.mls.com.corebase.R
import sr.mls.com.corebase.Utils.DimensUtil

@SuppressLint("LongLogTag")
abstract class CustomSizeBottomSheetFragment : BottomSheetDialogFragment() {

    enum class DialogHeight {
        P100, P75, P66, P50, P33, P25
    }

    companion object {
        private const val TAG = "CustomSizeBottomSheetFragment"
        const val ARG_DIALOG_HEIGHT_KEY = "dialog-height"
    }

    var dialogHeight: DialogHeight =
        DialogHeight.P100
        private set

    var dialogPeakHeight: Int = 1920
        private set

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "onCreate: ")
        arguments?.let {
            dialogHeight = DialogHeight.values()[it.getInt(
                ARG_DIALOG_HEIGHT_KEY,
                DialogHeight.P100.ordinal
            )]
        }
    }

    @CallSuper
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.v(TAG, "onCreateDialog: ")
        val dialog = super.onCreateDialog(savedInstanceState)
        initDialog(dialog, dialogHeight)
        return dialog
    }

    private fun initDialog(dialog: Dialog, dialogHeight: DialogHeight) {
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val bottomSheet: FrameLayout? = bottomSheetDialog.findViewById(R.id.design_bottom_sheet)

            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(bottomSheet)
                val params = bottomSheet.layoutParams
                val windowHeight = DimensUtil.getWindowHeight(requireActivity())
                val windowFullHeight = DimensUtil.getFullDisplayHeight(requireActivity())

                dialogPeakHeight = when (dialogHeight) {
                    DialogHeight.P25 -> windowFullHeight / 4 * 1
                    DialogHeight.P33 -> windowFullHeight / 3 * 1
                    DialogHeight.P50 -> windowFullHeight / 2 * 1
                    DialogHeight.P66 -> windowFullHeight / 3 * 2
                    DialogHeight.P75 -> windowFullHeight / 4 * 2
                    DialogHeight.P100 -> windowHeight * 1
                }
                params.height = dialogPeakHeight
                bottomSheet.layoutParams = params
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }
}