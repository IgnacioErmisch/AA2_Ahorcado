// Generado con asistencia de IA (Claude)
package com.example.aa2_ahorcado_ignacio_christian

// Número que recibe el primer nivel de la lista
private const val FIRST_LEVEL_NUMBER = 1

/** Fuente de datos de los niveles. Las palabras están hardcodeadas según pide la práctica. */
object LevelRepository {

    // Palabras sin tildes ni Ñ para que se puedan jugar con un teclado A-Z
    private val words: List<String> = listOf(
        // Fácil (1-4 letras)
        "SOL", "GATO", "ENTI", "MAR", "LUNA", "PAN",
        // Medio (5-7 letras)
        "TOMATE", "KOTLIN", "PLANETA", "ANDROID", "PIRATA", "CABALLO",
        // Difícil (más de 7 letras)
        "CASTILLO", "BALANCEO", "MARIPOSA", "ESQUELETO", "HORIZONTE", "VIDEOJUEGO",
        "INTERNACIONAL", "ADMINISTRADOR", "INCONSTITUCIONAL",
        "DESOXIRRIBONUCLEICO", "ESTERNOCLEIDOMASTOIDEO"
    )

    /** Todos los niveles, numerados en el orden de la lista de palabras. */
    val levels: List<Level> = words.mapIndexed { index, word ->
        Level(index + FIRST_LEVEL_NUMBER, word)
    }
}
