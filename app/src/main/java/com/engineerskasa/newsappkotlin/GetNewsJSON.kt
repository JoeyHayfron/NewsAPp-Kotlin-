package com.engineerskasa.newsappkotlin

import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import org.json.JSONObject

class GetNewsJSON(private val listener: OnDataAvailable) :
    AsyncTask<String, Void, List<NewsItem>>() {

    private val TAG = "GetNewsJSON"

    interface OnDataAvailable {
        fun onDataAvailable(data: List<NewsItem>)
        fun onJSONError(error: String)
    }

    override fun onPostExecute(result: List<NewsItem>) {
        Log.d(TAG, ".onPostExecute: Data Available $result")
        listener.onDataAvailable(result)
    }

    override fun doInBackground(vararg params: String?): List<NewsItem> {
        val newsList = ArrayList<NewsItem>()

        try {
            val newsJSONObject = JSONObject(params[0])
            val newsJSONArray = newsJSONObject.getJSONArray("articles")

            for (i in 0 until newsJSONArray.length()) {
                val newsItem = newsJSONArray.getJSONObject(i)

                val sourceObject = newsItem.getJSONObject("source")
                val sourceName = sourceObject.getString("name")
                val author = newsItem.getString("author")
                val title = newsItem.getString("title")
                val description = newsItem.getString("description")
                val articleUrl = newsItem.getString("url")
                val imageUrl = newsItem.getString("urlToImage")
                val datePublished = newsItem.getString("publishedAt")
                val content = newsItem.getString("content")

                val news = NewsItem(
                    sourceName,
                    author,
                    title,
                    description,
                    articleUrl,
                    imageUrl,
                    datePublished,
                    content
                )
                newsList.add(news)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.d(TAG, ".doInBackground: JSON Error ${e.message}")
            val errorMessage = ".doInBackground: JSON Error ${e.message}"
            cancel(true)
            listener.onJSONError(errorMessage)
        }
        return newsList
    }
}