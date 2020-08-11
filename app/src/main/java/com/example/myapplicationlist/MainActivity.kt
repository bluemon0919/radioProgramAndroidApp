package com.example.myapplicationlist

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationlist.adapter.CasarealRecycleViewAdapter
import com.example.myapplicationlist.model.RowData
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private var llm: LinearLayoutManager? = null
    var adpt: CasarealRecycleViewAdapter? = null
    var myDataSet: MutableList<RowData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val llm = LinearLayoutManager(baseContext)
        val rv = findViewById<RecyclerView>(R.id.myList)
        rv.setLayoutManager(llm)
        rv.setHasFixedSize(true)

        val adptWk = CasarealRecycleViewAdapter(myDataSet)
        //adpt = createDataset()?.let { CasarealRecycleViewAdapter(it)

        // インターフェースの実装
        adptWk.setOnItemClickListener(object:CasarealRecycleViewAdapter.OnItemClickListener{
            override fun onItemClickListener(view: View, position: Int, clickedText: String) {
                Toast.makeText(applicationContext, "${clickedText}がタップされました", Toast.LENGTH_LONG).show()

                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.jp"))
                startActivity(intent)
            }
        })
        adpt = adptWk
        rv.setAdapter(adpt)

        val url = "http://10.0.2.2:8080/api/rowdata"
        val myButton = findViewById<Button>(R.id.button1)
        myButton.setOnClickListener(View.OnClickListener {
            WebAPITask().execute(url)
        })
        
        WebAPITask().execute(url)
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
                connection.connect()

                // WebAPI実行結果を処理
                val stream = connection.inputStream
                reader = BufferedReader(InputStreamReader(stream))
                buffer = StringBuffer()
                var line: String?
                while (true) {
                    line = reader.readLine()
                    if (line == null) {
                        break
                    }
                    buffer.append(line)
                }

                val jsonText = buffer.toString()
                val parentJsonArray = JSONArray(jsonText)

                val dataset: MutableList<RowData> = ArrayList()
                for (i in 0..parentJsonArray.length()-1) {
                    val detailJsonObj = parentJsonArray.getJSONObject(i)
                    val title: String = detailJsonObj.getString("title")
                    val detail: String = detailJsonObj.getString("detail")

                    val data = RowData()
                    data.setTitle(title)
                    data.setDetail(detail)
                    dataset.add(data)
                }
                return dataset

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
            if (results == null) return
            myDataSet.clear()
            myDataSet.addAll(results)
            adpt?.notifyDataSetChanged()
        }
    }
}
