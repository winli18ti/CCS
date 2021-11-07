package com.winli.ccs.algorithm

object Vigenere {
    fun encrypt(input: String, key: String, encrypt: Boolean = true): String {
        var keyIndex = 0
        var output = ""
        var c = ' '
        for (value in input) {
            if (value.isLetter()) {
                if (value.isUpperCase()) {
                    c = if (encrypt) value + num(key[keyIndex])
                    else value - num(key[keyIndex]) + 26
                    if (c > 'Z') {
                        c -= 26
                    }
                } else if (value.isLowerCase()) {
                    c = if (encrypt) value + num(key[keyIndex])
                    else value - num(key[keyIndex]) + 26
                    if (c > 'z') {
                        c -= 26
                    }
                }
                keyIndex = (keyIndex + 1) % key.length
            } else {
                c = value
            }

            output += c
        }
        return output
    }

    fun decrypt(input: String, key: String): String {
        return encrypt(input, key, false)
    }

    private fun num(char: Char): Int {
        if (char.isUpperCase()) return char - 'A'
        else if (char.isLowerCase()) return char - 'a'
        return 0
    }
}