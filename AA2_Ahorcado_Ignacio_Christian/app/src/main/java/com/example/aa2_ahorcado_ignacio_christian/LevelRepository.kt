package com.example.aa2_ahorcado_ignacio_christian

import android.content.Context

private const val FIRST_LEVEL_NUMBER = 1

object LevelRepository {

    // Las palabras están en strings.xml para que cambien con el idioma
    fun getLevels(context: Context): List<Level> =
        context.resources.getStringArray(R.array.level_words).mapIndexed { index, word ->
            Level(index + FIRST_LEVEL_NUMBER, word)
        }
}
