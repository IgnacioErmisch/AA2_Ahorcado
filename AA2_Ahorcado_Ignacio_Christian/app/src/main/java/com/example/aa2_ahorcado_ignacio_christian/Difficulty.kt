// Generado con asistencia de IA (Claude)
package com.example.aa2_ahorcado_ignacio_christian

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// Máximo de letras (incluido) de cada dificultad, según el enunciado
private const val EASY_MAX_LETTERS = 4
private const val MEDIUM_MAX_LETTERS = 7

enum class Difficulty(
    val maxLetters: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val labelRes: Int
) {
    EASY(EASY_MAX_LETTERS, R.drawable.img_difficulty_easy, R.string.difficulty_easy),
    MEDIUM(MEDIUM_MAX_LETTERS, R.drawable.img_difficulty_medium, R.string.difficulty_medium),
    HARD(Int.MAX_VALUE, R.drawable.img_difficulty_hard, R.string.difficulty_hard);

    companion object {
        fun fromLetterCount(letterCount: Int): Difficulty =
            entries.first { letterCount <= it.maxLetters }
    }
}
