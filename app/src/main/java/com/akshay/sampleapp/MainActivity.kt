package com.akshay.sampleapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.akshay.ogtagparser.LinkSourceContent
import com.akshay.ogtagparser.LinkViewCallback
import com.akshay.ogtagparser.OgTagParser
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            editText.text.isNullOrBlank().apply {
                when {
                    this -> Toast.makeText(this@MainActivity, "Please Enter url", Toast.LENGTH_LONG).show()
                    else -> {
                        val linkArray = pullLinks(editText.text.toString().trim())
                        OgTagParser().execute(linkArray[0], callback)
                    }
                }
            }
        }

    }

    var callback: LinkViewCallback = object : LinkViewCallback {
        override fun onBeforeLoading() {
            progress.visibility = View.VISIBLE
        }

        override fun onAfterLoading(linkSourceContent: LinkSourceContent) {
            progress.visibility = View.GONE
            textView2.text = "Title - " + linkSourceContent.ogTitle +
                    "\n\n Description - " + linkSourceContent.ogDescription +
                    "\n\n Url - " + linkSourceContent.ogUrl +
                    "\n\n SiteName - " + linkSourceContent.ogSiteName +
                    "\n\n Type - " + linkSourceContent.ogType +
                    "\n\n Image - " + linkSourceContent.images

            tvTitle.text = linkSourceContent.ogTitle
            tvUrl.text = linkSourceContent.ogUrl
            tvDescription.text = linkSourceContent.ogDescription
            Glide.with(this@MainActivity)
                .load(linkSourceContent.images)
                .into(drop_preview.ivImage)
        }

    }

    private fun pullLinks(input: String): ArrayList<String> {
        val containedUrls = ArrayList<String>()
        val urlRegex = "(?:(?:https?|ftp):\\/\\/)?[\\w/\\-?=%.]+\\.[\\w/\\-?=%.]+"
        val pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE)
        val urlMatcher = pattern.matcher(input)
        while (urlMatcher.find()) {
            containedUrls.add(
                input.substring(
                    urlMatcher.start(0),
                    urlMatcher.end(0)
                )
            )
        }
        return containedUrls
    }

}
