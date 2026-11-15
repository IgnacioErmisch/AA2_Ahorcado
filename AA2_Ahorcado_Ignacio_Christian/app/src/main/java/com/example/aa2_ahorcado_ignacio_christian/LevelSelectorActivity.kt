// Generado con asistencia de IA (Claude)
package com.example.aa2_ahorcado_ignacio_christian

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.MaterialToolbar

/**
- Selector de niveles: lista con scroll vertical de todos los niveles definidos
- en [LevelRepository]. Cada fila muestra el número de nivel, la palabra (para debug),
- la cantidad de letras y una imagen con la dificultad.
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
        toolbar.setTitle(R.string.level_selector_title)
        toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_toggle_theme) {
                val currentMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
                val isNight = currentMode == Configuration.UI_MODE_NIGHT_YES
                AppCompatDelegate.setDefaultNightMode(
                    if (isNight) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
                )
                true
            } else {
                false
            }
        }

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

        // Acción de clic para iniciar el juego
        levelView.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("WORD_TO_GUESS", level.word)
            startActivity(intent)
        }

        return levelView
    }
}