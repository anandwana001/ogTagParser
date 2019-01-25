package com.akshay.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akshay.ogtagparser.OgTagParser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ogTagParser = OgTagParser()
        ogTagParser.execute("http://www.facebook.com")
        textView2.text = "Title = "+ogTagParser.getTitle() +
                            "\n Description = "+ogTagParser.getDescription() +
                "\n Site Name = "+ogTagParser.getSiteName() +
                "\n Image Link = "+ogTagParser.getImage() +
                "\n Url = "+ogTagParser.getUrl() +
                "\n Type = "+ogTagParser.getType()

    }
}
