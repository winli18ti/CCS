package com.winli.ccs.algorithm

object Affine {
    fun coPrime(a: Int, b: Int): Boolean {
        return modularMultiplicativeInverse(a, b) != 1
    }

    fun encrypt(input: String, multi: Int, add: Int, encrypt: Boolean = true): String {
        val inverse = modularMultiplicativeInverse(multi, 26)
        var output = ""
        var c = ' '
        for (value in input) {
            if (value.isLetter()) {
                if (value.isUpperCase()) {
                    c = num(value).let {
                        if (encrypt) (it * multi + add) % 26 else (inverse * (it - add)) % 26
                    }.let {
                        'A' + it
                    }.let {
                        if (it < 'A') it + 26 else it
                    }
                } else if (value.isLowerCase()) {
                    c = num(value).let {
                        if (encrypt) (it * multi + add) % 26 else (inverse * (it - add)) % 26
                    }.let {
                        'a' + it
                    }.let {
                        if (it < 'a') it + 26 else it
                    }
                }
            } else {
                c = value
            }

            output += c
        }
        return output
    }

    fun decrypt(input: String, multi: Int, add: Int): String {
        return encrypt(input, multi, add, false)
    }

    private fun modularMultiplicativeInverse(a: Int, m: Int): Int {
        for (i in 1 until m)
            if (((a % m) * (i % m)) % m == 1)
                return i
        return 1
    }

    private fun num(char: Char): Int {
        if (char.isUpperCase()) return char - 'A'
        else if (char.isLowerCase()) return char - 'a'
        return 0
    }
}