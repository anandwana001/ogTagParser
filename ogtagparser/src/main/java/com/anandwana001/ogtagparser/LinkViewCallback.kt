package com.anandwana001.ogtagparser

/**
 * Created by akshaynandwana on
 * 26, January, 2019
 **/
interface LinkViewCallback {
    fun onBeforeLoading()
    fun onAfterLoading(linkSourceContent: LinkSourceContent)
}