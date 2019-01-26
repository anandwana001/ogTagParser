package com.akshay.ogtagparser

import android.os.AsyncTask
import org.jsoup.Jsoup

/**
 * Created by akshaynandwana on
 * 25, January, 2019
 **/
class OgTagParser {

    private var callback: LinkViewCallback? = null

    fun execute(urlToParse: String, callback: LinkViewCallback) {
        this.callback = callback
        JsoupOgTagParser(urlToParse).execute()
    }

    inner class JsoupOgTagParser(var urlToParse: String) : AsyncTask<Void, Void, Void?>() {

        private val linkSourceContent = LinkSourceContent()

        override fun onPreExecute() {
            if (callback != null) {
                callback!!.onBeforeLoading()
            }
            super.onPreExecute()
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
                                linkSourceContent.setImage(tag.attr("content"))
                            }
                            "og:description" -> {
                                linkSourceContent.setDescription(tag.attr("content"))
                            }
                            "og:url" -> {
                                linkSourceContent.setUrl(tag.attr("content"))
                            }
                            "og:title" -> {
                                linkSourceContent.setTitle(tag.attr("content"))
                            }
                            "og:site_name" -> {
                                linkSourceContent.setSiteName(tag.attr("content"))
                            }
                            "og:type" -> {
                                linkSourceContent.setType(tag.attr("content"))
                            }
                        }
                    }
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            if (callback != null) {
                callback!!.onAfterLoading(linkSourceContent)
            }
            super.onPostExecute(result)
        }
    }
}