package com.winli.ccs.algorithm

object RailFence {
    fun encrypt(input: String, key: Int): String {
        val rail = Array(key) { CharArray(input.length) { '\n' } }

        var down = false
        var row = 0
        var col = 0

        for (item in input) {
            if (row == 0 || row == key - 1) {
                down = !down
            }

            rail[row][col++] = item
            if (down) row++ else row--
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

        var down = false
        var row = 0
        var col = 0
        for (i in input.indices) {
            if (row == 0 || row == key - 1) {
                down = !down
            }

            rail[row][col++] = '*'
            if (down) row++ else row--
        }

        var index = 0
        for (i in 0 until key)
            for (j in input.indices)
                if (rail[i][j] == '*' && index < input.length)
                    rail[i][j] = input[index++]

        down = false
        row = 0
        col = 0

        var output = ""
        for (i in input.indices) {
            if (row == 0 || row == key - 1) {
                down = !down
            }

            if (rail[row][col] != '*')
                output += rail[row][col++]

            if (down) row++ else row--
        }

        return output
    }
}