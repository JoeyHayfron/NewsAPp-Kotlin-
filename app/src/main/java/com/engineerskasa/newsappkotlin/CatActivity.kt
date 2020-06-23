package com.engineerskasa.newsappkotlin

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.content_cat.*

class CatActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat)

        activateToolbar(true, "Categories")

        technology_cardview.setOnClickListener(this)
        sports_cardview.setOnClickListener(this)
        business_cardview.setOnClickListener(this)
        science_cardview.setOnClickListener(this)
        health_cardview.setOnClickListener(this)
        general_cardview.setOnClickListener(this)
        entertainment_cardview.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.technology_cardview -> {
                PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putString(
                    QUERY_VAL, "technology"
                ).apply()
                finish()
            }
            R.id.sports_cardview -> {
                PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putString(
                    QUERY_VAL, "sports"
                ).apply()
                finish()
            }
            R.id.business_cardview -> {
                PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putString(
                    QUERY_VAL, "business"
                ).apply()
                finish()
            }
            R.id.science_cardview -> {
                PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putString(
                    QUERY_VAL, "science"
                ).apply()
                finish()
            }
            R.id.health_cardview -> {
                PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putString(
                    QUERY_VAL, "health"
                ).apply()
                finish()
            }
            R.id.general_cardview -> {
                PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putString(
                    QUERY_VAL, "general"
                ).apply()
                finish()
            }
            R.id.entertainment_cardview -> {
                PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putString(
                    QUERY_VAL, "entertainment"
                ).apply()
                finish()
            }
        }
    }
}