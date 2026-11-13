// Generado con asistencia de IA (Claude)
package com.example.aa2_ahorcado_ignacio_christian

/**
 * Nivel jugable del ahorcado.
 *
 * @property number Número de nivel que se muestra al jugador.
 * @property word Palabra a adivinar (en mayúsculas).
 */
data class Level(val number: Int, val word: String) {

    /** Cantidad de letras a acertar. */
    val letterCount: Int
        get() = word.length

    /** Dificultad calculada a partir de la cantidad de letras. */
    val difficulty: Difficulty
        get() = Difficulty.fromLetterCount(letterCount)
}
