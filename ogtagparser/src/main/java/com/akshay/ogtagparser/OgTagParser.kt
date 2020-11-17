package com.akshay.ogtagparser

import kotlinx.coroutines.*
import org.jsoup.Jsoup
import kotlin.coroutines.CoroutineContext

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

    inner class JsoupOgTagParser(var urlToParse: String) : CoroutineScope {

        private var job: Job = Job()
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main + job

        fun execute() = launch {
            onPreExecute()
            val result = doInBackground()
            onPostExecute(result)
        }

        private val linkSourceContent = LinkSourceContent()

        private fun onPreExecute() {
            callback?.onBeforeLoading()
        }

        private suspend fun doInBackground(): LinkSourceContent = withContext(Dispatchers.IO) {
            if (!urlToParse.contains("http")) {
                urlToParse = "http://$urlToParse"
            }
            try {
                val response = Jsoup.connect(urlToParse)
                    .ignoreContentType(true)
                    .userAgent("Mozilla")
                    .referrer("http://www.google.com")
                    .timeout(12000)
                    .followRedirects(true)
                    .execute()
                val doc = response.parse()
                val ogTags = doc.select("meta[property^=og:]")
                when {
                    ogTags.size > 0 ->
                        ogTags.forEachIndexed { index, element ->
                            val tag = ogTags[index]
                            val text = tag.attr("property")
                            when (text) {
                                "og:image" -> {
                                    linkSourceContent.images = (tag.attr("content"))
                                }
                                "og:description" -> {
                                    linkSourceContent.ogDescription = (tag.attr("content"))
                                }
                                "og:url" -> {
                                    linkSourceContent.ogUrl = (tag.attr("content"))
                                }
                                "og:title" -> {
                                    linkSourceContent.ogTitle = (tag.attr("content"))
                                }
                                "og:site_name" -> {
                                    linkSourceContent.ogSiteName = (tag.attr("content"))
                                }
                                "og:type" -> {
                                    linkSourceContent.ogType = (tag.attr("content"))
                                }
                            }
                        }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@withContext linkSourceContent
        }

        private fun onPostExecute(linkSourceContent: LinkSourceContent) {
            if (callback != null) {
                callback!!.onAfterLoading(linkSourceContent)
            }
        }
    }
}
