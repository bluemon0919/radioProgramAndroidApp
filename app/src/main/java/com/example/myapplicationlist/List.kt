import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplicationlist.model.RowData
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class SelectorDialogFragment(private val rowdata: RowData) :
    DialogFragment() {
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val strList = arrayOf("聴く","聴き終えた")
        return AlertDialog.Builder(activity)
            .setTitle("操作を選択してください")
            .setItems(strList, { dialog, which ->

                when (which) {
                    // "聴く"を選択
                    0 -> {
                        val url = rowdata.getURL()
                        val intent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    }
                    // "聴き終えた"を選択
                    1 -> {
                        //Toast.makeText(activity, "${rowdata.getTitle()}がタップされました", Toast.LENGTH_LONG).show()
                        val url = "http://10.0.2.2:8080/api/program?programId=${rowdata.getID()}"
                        WebAPITask().execute(url)
                    }else -> {
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

    inner class WebAPITask: AsyncTask<String, List<RowData>, MutableList<RowData>>() {

        override fun doInBackground(vararg params: String?): MutableList<RowData>? {
            // WebAPIコールをバックグラウンドで実行
            var connection: HttpURLConnection? = null
            var reader: BufferedReader? = null
            val buffer: StringBuffer

            try {
                val url = URL(params[0])
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "PUT"
                connection.instanceFollowRedirects = false
                connection.setRequestProperty("Accept-Language", "jp")
                connection.doOutput = true
                connection.connectTimeout = 5000
                connection.readTimeout = 5000
                //connection.connect()

                val out = OutputStreamWriter(
                    connection.getOutputStream()
                )
                out.write("Resource content")
                out.close()
                connection.getInputStream()
                return null

            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            finally {
                    connection?.disconnect()
                    try {
                        reader?.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
            }
            return null
        }

        // ビューに反映させる処理
        override fun onPostExecute(results: MutableList<RowData>?) {
            super.onPostExecute(results)
        }
    }
}