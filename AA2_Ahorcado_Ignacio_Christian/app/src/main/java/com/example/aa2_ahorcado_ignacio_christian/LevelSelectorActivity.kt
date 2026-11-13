// Generado con asistencia de IA (Claude)
package com.example.aa2_ahorcado_ignacio_christian

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * Selector de niveles: lista con scroll vertical de todos los niveles definidos
 * en [LevelRepository]. Cada fila muestra el número de nivel, la palabra (para debug),
 * la cantidad de letras y una imagen con la dificultad.
 */
class LevelSelectorActivity : AppCompatActivity() {

    private lateinit var rootLayout: LinearLayout
    private lateinit var toolbar: Toolbar
    private lateinit var levelContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level_selector)

        rootLayout = findViewById(R.id.main)
        applySystemBarsPadding(rootLayout)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        levelContainer = findViewById(R.id.levelContainer)
        populateLevels()
    }

    /** Crea una fila por cada nivel y la añade al contenedor del ScrollView. */
    private fun populateLevels() {
        for (level in LevelRepository.levels) {
            levelContainer.addView(createLevelView(level))
        }
    }

    /** Infla la plantilla item_level y la rellena con los datos de [level]. */
    private fun createLevelView(level: Level): View {
        val levelView = layoutInflater.inflate(R.layout.item_level, levelContainer, false)

        val levelNumberText: TextView = levelView.findViewById(R.id.levelNumberText)
        val levelWordText: TextView = levelView.findViewById(R.id.levelWordText)
        val levelLettersText: TextView = levelView.findViewById(R.id.levelLettersText)
        val difficultyImage: ImageView = levelView.findViewById(R.id.difficultyImage)

        levelNumberText.text = getString(R.string.level_number, level.number)
        levelWordText.text = getString(R.string.level_word, level.word)
        levelLettersText.text = getString(R.string.level_letters, level.letterCount)

        difficultyImage.setImageResource(level.difficulty.imageRes)
        difficultyImage.contentDescription = getString(level.difficulty.labelRes)

        return levelView
    }
}
