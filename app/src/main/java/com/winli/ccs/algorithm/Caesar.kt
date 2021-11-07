package com.winli.ccs.algorithm

object Caesar {
    fun encrypt(input: String, key: Int): String {
        val offset = key % 26
        var output = ""
        var c = ' '
        for (value in input) {
            if (value.isLetter()) {
                if (value.isUpperCase()) {
                    c = value + offset
                    if (c > 'Z') {
                        c -= 26
                    }
                } else if (value.isLowerCase()) {
                    c = value + offset
                    if (c > 'z') {
                        c -= 26
                    }
                }
            } else {
                c = value
            }

            output += c
        }
        return output
    }

    fun decrypt(input: String, key: Int): String {
        return encrypt(input, 26 - key)
    }
}