package com.winli.ccs.algorithm

object Scytale {
    fun encrypt(input: String, key: Int): String {
        val rail = Array(key) { CharArray(input.length) { '\n' } }

        var row = 0
        var col = 0

        for (item in input) {
            rail[row][col++] = item
            row = (row + 1) % key
        }

        var output = ""
        for (i in 0 until key)
            for (j in input.indices)
                if (rail[i][j] != '\n')
                    output += rail[i][j]
        return output
    }

    fun decrypt(input: String, key: Int): String {
        val rail = Array(key) { CharArray(input.length) { '\n' } }

        var row = 0
        var col = 0
        for (i in input.indices) {
            rail[row][col++] = '*'
            row = (row + 1) % key
        }

        var index = 0
        for (i in 0 until key)
            for (j in input.indices)
                if (rail[i][j] == '*' && index < input.length)
                    rail[i][j] = input[index++]

        row = 0
        col = 0

        var output = ""
        for (i in input.indices) {
            if (rail[row][col] != '*')
                output += rail[row][col++]

            row = (row + 1) % key
        }

        return output
    }
}