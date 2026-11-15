// Generado con asistencia de IA (Claude)
package com.example.aa2_ahorcado_ignacio_christian

data class Level(val number: Int, val word: String) {

    val letterCount: Int
        get() = word.length

    val difficulty: Difficulty
        get() = Difficulty.fromLetterCount(letterCount)
}
