package com.akshay.ogtagparser

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anandwana001.ogtagparser.OgTagParser
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            if (TextUtils.isEmpty(editText.text.toString())) {
                Toast.makeText(
                    this@MainActivity,
                    "Please Enter URL",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val linkArray = getFirstUrl(editText.text.toString().trim())
                val content = OgTagParser().getContents(linkArray)
                content?.let {
                    textView2.text = String.format(
                        getString(R.string.display_data),
                        content.ogTitle,
                        content.ogDescription,
                        content.ogUrl,
                        content.ogSiteName,
                        content.ogType,
                        content.image
                    )
                    tvTitle.text = content.ogTitle
                    tvUrl.text = content.ogUrl
                    tvDescription.text = content.ogDescription
                    tvSiteName.text = content.ogSiteName
                    Glide.with(this@MainActivity)
                        .load(content.image)
                        .into(drop_preview.ivImage)
                }
            }
        }
    }

    private fun getFirstUrl(input: String): String {
        val containedUrls = ArrayList<String>()
        val urlMatcher = getPatternMatcher(
            urlRegex = "(?:(?:https?|ftp):\\/\\/)?[\\w/\\-?=%.]+\\.[\\w/\\-?=%.]+",
            input
        )
        while (urlMatcher.find()) {
            containedUrls.add(
                input.substring(
                    urlMatcher.start(0),
                    urlMatcher.end(0)
                )
            )
        }
        return containedUrls[0]
    }

    private fun getPatternMatcher(urlRegex: String, input: String): Matcher {
        val pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(input)
    }
}
