package com.engineerskasa.newsappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_reader.*

class ReaderActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)

        activateToolbar(true, "")
        val news = intent.extras?.getParcelable<NewsItem>("NEWS_ITEM") as NewsItem

        articleHeadline.text = news.title
        articleAuthor.text = news.author
        articleSource.text = news.source
        articleDate.text = news.datePublished
        articleContent.text = news.description
        Picasso.get().load(news.imageUrl)
            .placeholder(R.drawable.news_placeholder)
            .error(R.drawable.news_placeholder)
            .into(app_bar_image)

        read_more.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, WebReader::class.java)
            intent.putExtra("SITE", news.articleUrl)
            startActivity(intent)
        })
    }

}