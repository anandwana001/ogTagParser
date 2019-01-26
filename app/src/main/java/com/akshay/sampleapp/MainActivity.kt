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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            editText.text.isNullOrBlank().apply {
                when {
                    this -> Toast.makeText(this@MainActivity, "Please Enter url", Toast.LENGTH_LONG).show()
                    else -> OgTagParser().execute(editText.text.toString().trim(), callback)
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
            textView2.text = "Title - " + linkSourceContent.getTitle() +
                    "\n Description - " + linkSourceContent.getDescription() +
                    "\n Url - " + linkSourceContent.getUrl() +
                    "\n SiteName - " + linkSourceContent.getSiteName() +
                    "\n Type - " + linkSourceContent.getType() +
                    "\n Image - " + linkSourceContent.getImage()

            tvTitle.text = linkSourceContent.getTitle()
            tvUrl.text = linkSourceContent.getUrl()
            tvDescription.text = linkSourceContent.getDescription()
            Glide.with(this@MainActivity)
                .load(linkSourceContent.getImage())
                .into(drop_preview.ivImage)
        }

    }

}
