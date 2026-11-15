package com.example.aa2_ahorcado_ignacio_christian

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

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
        setupTopBar(this, toolbar)

        levelContainer = findViewById(R.id.levelContainer)
        populateLevels()
    }

    private fun populateLevels() {
        for (level in LevelRepository.getLevels(this)) {
            levelContainer.addView(createLevelView(level))
        }
    }

    // Rellena una fila de item_level con los datos del nivel
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

        levelView.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(GameActivity.EXTRA_WORD_TO_GUESS, level.word)
            startActivity(intent)
        }

        return levelView
    }
}
