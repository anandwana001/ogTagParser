package com.akshay.ogtagparser


/**
 * Created by akshaynandwana on
 * 26, January, 2019
 **/
class LinkSourceContent {

    private var ogTitle = ""
    private var ogDescription = ""
    private var ogUrl = ""
    private var ogSiteName: String = ""
    private var ogType: String = ""

    private var images: String = ""

    fun getTitle(): String {
        return ogTitle
    }

    fun setTitle(ogTitle: String) {
        this.ogTitle = ogTitle
    }

    fun getDescription(): String {
        return ogDescription
    }

    fun setDescription(ogDescription: String) {
        this.ogDescription = ogDescription
    }

    fun getUrl(): String {
        return ogUrl
    }

    fun setUrl(ogUrl: String) {
        this.ogUrl = ogUrl
    }

    fun getSiteName(): String {
        return ogSiteName
    }

    fun setSiteName(ogSiteName: String) {
        this.ogSiteName = ogSiteName
    }

    fun getType(): String {
        return ogType
    }

    fun setType(ogType: String) {
        this.ogType = ogType
    }

    fun getImage(): String {
        return images
    }

    fun setImage(images: String) {
        this.images = images
    }
}