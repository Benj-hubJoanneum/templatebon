package com.ssi.schaefer.lunchbon.service

import org.apache.commons.text.StringEscapeUtils
import org.springframework.stereotype.Service

@Service
class HelperMethods {

    fun removeQuotesAndUnescape(uncleanJson: String): String {
        val noQuotes = uncleanJson.replace("^\"|\"$".toRegex(), "")

        return StringEscapeUtils.unescapeJava(noQuotes)
    }
}