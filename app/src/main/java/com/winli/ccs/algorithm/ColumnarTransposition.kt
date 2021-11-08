package com.winli.ccs.algorithm

object ColumnarTransposition {
    lateinit var keyMap: MutableMap<Char, Int>

    fun encrypt(input: String, key: String): String {
        setPermutationOrder(key)
        val col = key.length
        var row = input.length / col
        if (input.length % col > 0)
            row++

        val matrix = Array(row) { CharArray(col) }
        var k = 0
        for (i in 0 until row) {
            var j = 0
            while (j < col) {
                if (k >= input.length) {
                    matrix[i][j] = '_'
                    j++
                } else {
                    matrix[i][j] = input[k]
                    j++
                }
                k++
            }
        }

        var output = ""
        for ((_, j) in keyMap) {
            for (i in 0 until row)
                output += matrix[i][j]
        }
        return output
    }

    fun decrypt(input: String, key: String): String {
        setPermutationOrder(key)
        val col = key.length
        val row = input.length / col
        val matrix = Array(row) { CharArray(col) }
        var k = 0
        for (j in 0 until col)
            for (i in 0 until row)
                matrix[i][j] = input[k++]

        var index = 0
        for ((i, _) in keyMap) {
            keyMap[i] = index++
        }

        val dec = Array(row) { CharArray(col) }
        k = 0
        var l = 0
        while (l < key.length) {
            val j = keyMap[key[l++]]
            for (i in 0 until row)
                dec[i][k] = matrix[i][j!!]
            k++
        }

        var output = ""
        for (i in 0 until row)
            for (j in 0 until col)
                if (dec[i][j] != '_')
                    output += dec[i][j]
        return output
    }

    private fun setPermutationOrder(key: String) {
        keyMap = mutableMapOf()
        for (index in key.indices) {
            keyMap[key[index]] = index
        }
    }
}