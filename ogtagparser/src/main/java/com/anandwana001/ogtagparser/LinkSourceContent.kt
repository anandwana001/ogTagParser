package com.anandwana001.ogtagparser

/**
 * Created by akshaynandwana on
 * 26, January, 2019
 **/

/**
 * Data class required for parsing.
 */
data class LinkSourceContent(
    var ogTitle: String,
    var ogDescription: String,
    var ogUrl: String,
    var images: String,
    var ogSiteName: String,
    var ogType: String
) {
    constructor() : this("", "", "", "", "", "")
}
