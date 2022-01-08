package com.anandwana001.ogtagparser

/**
 * Created by anandwana001 on
 * 26, January, 2019
 **/

/**
 * Data class required for parsing.
 */
data class LinkSourceContent(
    var ogTitle: String,
    var ogDescription: String,
    var ogUrl: String,
    var image: String,
    var ogSiteName: String,
    var ogType: String
) {
    constructor() : this("", "", "", "", "", "")
}
