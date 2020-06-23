package com.engineerskasa.newsappkotlin

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class NewsItem(
    var source: String,
    var author: String,
    var title: String,
    var description: String,
    var articleUrl: String,
    var imageUrl: String,
    var datePublished: String,
    var content: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(source)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(articleUrl)
        parcel.writeString(imageUrl)
        parcel.writeString(datePublished)
        parcel.writeString(content)
    }

    override fun toString(): String {
        return """
            source: $source,
            author: $author,
            title: $title,
            description: $description,
            articleURL: $articleUrl,
            imageURL: $imageUrl,
            datePublished: $datePublished
            content: $content
        """.trimIndent()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsItem> {
        override fun createFromParcel(parcel: Parcel): NewsItem {
            return NewsItem(parcel)
        }

        override fun newArray(size: Int): Array<NewsItem?> {
            return arrayOfNulls(size)
        }
    }

}