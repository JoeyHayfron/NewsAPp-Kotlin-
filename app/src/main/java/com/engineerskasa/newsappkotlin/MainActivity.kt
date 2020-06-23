package com.engineerskasa.newsappkotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), GetRawData.OnDownloadComplete,
    GetNewsJSON.OnDataAvailable, RecyclerItemClickListener.OnItemClicked {

    private val TAG = "MainActivity"
    val homeRecyclerAdapter = HomeRecyclerAdapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val resultQuery = result.getString(QUERY_VAL, "general")
        activateToolbar(false, "NewsApp(" + resultQuery + ")")
        toolbar.overflowIcon = getDrawable(R.drawable.menu)

        homeRecycler.layoutManager = LinearLayoutManager(this)
        homeRecycler.addOnItemTouchListener(RecyclerItemClickListener(this, homeRecycler, this))
        homeRecycler.adapter = homeRecyclerAdapter

    }

    fun uriBuilder(
        baseUrl: String,
        query: String,
        language: String,
        pageSize: String,
        key: String
    ): String {
        return Uri.parse(baseUrl)
            .buildUpon()
            .appendQueryParameter("q", query)
            .appendQueryParameter("pageSize", pageSize)
            .appendQueryParameter("language", language)
            .appendQueryParameter("apiKey", key)
            .build().toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.categories -> {
                startActivity(Intent(this, CatActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDownloadComplete(status: DownloadStatus, data: String) {
        Log.d(TAG, "ODC: data-> $data")
        val getNewsJson = GetNewsJSON(this)
        getNewsJson.execute(data)
    }

    override fun onError(status: DownloadStatus, data: String) {
        Log.d(TAG, "OE: error-> $data")
    }

    override fun onDataAvailable(data: List<NewsItem>) {
        Log.d(TAG, "ODA: data-> $data")
        homeRecyclerAdapter.loadData(data)
    }

    override fun onJSONError(error: String) {
        Log.d(TAG, "OJE: data-> $error")

    }

    override fun onResume() {
        val result = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val resultQuery = result.getString(QUERY_VAL, "general")
        if (resultQuery != null) {
            val uri = uriBuilder(
                "https://newsapi.org/v2/everything",
                resultQuery,
                "en",
                "100",
                getString(R.string.API_KEY)
            )
            val getNewsData = GetRawData(this)
            getNewsData.execute(uri)
        }
        toolbar.title = "NewsApp($resultQuery)"
        super.onResume()
    }

    override fun onItemClicked(view: View, position: Int) {
        val item: NewsItem? = homeRecyclerAdapter.getNews(position)
        if (item != null) {
            if (item.content != null) {
                val intent = Intent(this, ReaderActivity::class.java)
                intent.putExtra("NEWS_ITEM", item)
                startActivity(intent)
            }
        }
    }
}