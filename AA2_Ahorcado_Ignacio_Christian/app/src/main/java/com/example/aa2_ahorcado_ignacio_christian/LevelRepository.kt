// Generado con asistencia de IA (Claude)
package com.example.aa2_ahorcado_ignacio_christian

private const val FIRST_LEVEL_NUMBER = 1

object LevelRepository {

    // Palabras sin tildes ni Ñ para que se puedan jugar con un teclado A-Z
    private val words: List<String> = listOf(
        "SOL", "GATO", "ENTI", "LUNA",
        "TOMATE", "KOTLIN", "ANDROID", "PIRATA",
        "CASTILLO", "MARIPOSA", "VIDEOJUEGO", "INTERNACIONAL", "ESTERNOCLEIDOMASTOIDEO"
    )

    val levels: List<Level> = words.mapIndexed { index, word ->
        Level(index + FIRST_LEVEL_NUMBER, word)
    }
}
