package com.akshay.ogtagparser

import android.os.AsyncTask
import org.jsoup.Jsoup

/**
 * Created by akshaynandwana on
 * 25, January, 2019
 **/
class OgTagParser {

    private var ogTitle: String = Constants.UNKNOWN_OG_TITLE
    private var ogSiteName: String = Constants.UNKNOWN_OG_SITE_NAME
    private var ogDescription: String = Constants.UNKNOWN_OG_DESCRIPTION
    private var ogType: String = Constants.UNKNOWN_OG_TYPE
    private var ogImage: String = Constants.UNKNOWN_OG_IMAGE
    private var ogUrl: String = Constants.UNKNOWN_OG_URL

    private var jsoupCallCompleted: Boolean = true

    fun execute(urlToParse: String){
        JsoupOgTagParser(urlToParse).execute()
    }

    fun setTitle(ogTitle: String) {
        this.ogTitle = ogTitle
    }

    fun setSiteName(ogSiteName: String) {
        this.ogSiteName = ogSiteName
    }

    fun setDescription(ogDescription: String) {
        this.ogDescription = ogDescription
    }

    fun setType(ogType: String) {
        this.ogType = ogType
    }

    fun setImage(ogImage: String) {
        this.ogImage = ogImage
    }

    fun setUrl(ogUrl: String) {
        this.ogUrl = ogUrl
    }

    fun getTitle(): String {
        return ogTitle
    }

    fun getSiteName(): String {
        return ogSiteName
    }

    fun getDescription(): String {
        return ogDescription
    }

    fun getType(): String {
        return ogType
    }

    fun getImage(): String {
        return ogImage
    }

    fun getUrl(): String {
        return ogUrl
    }

    inner class JsoupOgTagParser(var urlToParse: String) : AsyncTask<Void, Void, Void?>() {

        override fun onPreExecute() {
            super.onPreExecute()
            jsoupCallCompleted = false
        }

        override fun doInBackground(vararg voids: Void): Void? {
            val con = Jsoup.connect(urlToParse)
            val doc = con.userAgent("Mozilla").get()
            val ogTags = doc.select("meta[property^=og:]")
            when {
                ogTags.size > 0 ->
                    ogTags.forEachIndexed { index, element ->
                        val tag = ogTags[index]
                        val text = tag.attr("property")
                        when (text) {
                            "og:image" -> {
                                ogImage = tag.attr("content")
                            }
                            "og:description" -> {
                                ogDescription = tag.attr("content")
                            }
                            "og:url" -> {
                                ogUrl = tag.attr("content")
                            }
                            "og:title" -> {
                                ogTitle = tag.attr("content")
                            }
                            "og:site_name" -> {
                                ogSiteName = tag.attr("content")
                            }
                            "og:type" -> {
                                ogType = tag.attr("content")
                            }
                        }
                    }
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            jsoupCallCompleted = true
            setTitle(ogTitle)
            setDescription(ogDescription)
            setUrl(ogUrl)
            setImage(ogImage)
            setSiteName(ogSiteName)
            setType(ogType)
        }
    }
}