package com.akshay.ogtagparser

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anandwana001.ogtagparser.LinkSourceContent
import com.anandwana001.ogtagparser.LinkViewCallback
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
                val linkArray = getUrls(editText.text.toString().trim())
                OgTagParser().getContents(
                    linkArray[0],
                    object : LinkViewCallback {
                        override fun onAfterLoading(linkSourceContent: LinkSourceContent) {
                            textView2.text = String.format(
                                getString(R.string.display_data),
                                linkSourceContent.ogTitle,
                                linkSourceContent.ogDescription,
                                linkSourceContent.ogUrl,
                                linkSourceContent.ogSiteName,
                                linkSourceContent.ogType,
                                linkSourceContent.images
                            )
                            tvTitle.text = linkSourceContent.ogTitle
                            tvUrl.text = linkSourceContent.ogUrl
                            tvDescription.text = linkSourceContent.ogDescription
                            tvSiteName.text = linkSourceContent.ogSiteName
                            Glide.with(this@MainActivity)
                                .load(linkSourceContent.images)
                                .into(drop_preview.ivImage)
                        }
                    }
                )
            }
        }
    }

    private fun getUrls(input: String): ArrayList<String> {
        val containedUrls = ArrayList<String>()
        val urlMatcher = getPatternMatcher(
            "(?:(?:https?|ftp):\\/\\/)?[\\w/\\-?=%.]+\\.[\\w/\\-?=%.]+",
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
        return containedUrls
    }

    private fun getPatternMatcher(urlRegex: String, input: String): Matcher {
        val pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(input)
    }
}
