import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplicationlist.model.RowData

class SelectorDialogFragment(private val rowdata: RowData) :
    DialogFragment() {
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val strList = arrayOf("聴く","聴き終えた")
        return AlertDialog.Builder(activity)
            .setTitle("操作を選択してください")
            .setItems(strList, { dialog, which ->

                when (which) {
                    0 -> {
                        val intent =
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.jp"))
                        startActivity(intent)
                    }
                    1 -> Toast.makeText(activity, "${rowdata.getDetail()}がタップされました", Toast.LENGTH_LONG).show()
                    else -> {
                    }
                }

            })
            .create()
    }

    override fun onPause() {
        super.onPause()

        // onPause でダイアログを閉じる場合
        dismiss()
    }
}