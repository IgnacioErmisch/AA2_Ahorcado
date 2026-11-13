// Generado con asistencia de IA (Claude)
package com.example.aa2_ahorcado_ignacio_christian

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// Máximo de letras (incluido) de cada dificultad, según el enunciado
private const val EASY_MAX_LETTERS = 4
private const val MEDIUM_MAX_LETTERS = 7

/**
 * Dificultad de un nivel según la cantidad de letras de su palabra.
 *
 * @property maxLetters Máximo de letras (incluido) para pertenecer a esta dificultad.
 * @property imageRes Imagen que representa la dificultad en el selector.
 * @property labelRes Nombre de la dificultad (se usa como descripción de la imagen).
 */
enum class Difficulty(
    val maxLetters: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val labelRes: Int
) {
    EASY(EASY_MAX_LETTERS, R.drawable.img_difficulty_easy, R.string.difficulty_easy),
    MEDIUM(MEDIUM_MAX_LETTERS, R.drawable.img_difficulty_medium, R.string.difficulty_medium),
    HARD(Int.MAX_VALUE, R.drawable.img_difficulty_hard, R.string.difficulty_hard);

    companion object {
        /** Devuelve la primera dificultad (de menor a mayor) cuyo máximo admite [letterCount]. */
        fun fromLetterCount(letterCount: Int): Difficulty =
            entries.first { letterCount <= it.maxLetters }
    }
}
