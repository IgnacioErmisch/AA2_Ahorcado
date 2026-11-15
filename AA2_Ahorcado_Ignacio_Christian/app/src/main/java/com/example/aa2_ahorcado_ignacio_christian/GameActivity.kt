package com.example.aa2_ahorcado_ignacio_christian

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class GameActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_WORD_TO_GUESS = "WORD_TO_GUESS"
        private const val KEY_COLUMN_WEIGHT = 1f
        private const val HIDDEN_LETTER = '_'
        private const val LETTER_SEPARATOR = " "
    }

    private lateinit var toolbar: Toolbar
    private lateinit var wordTextView: TextView
    private lateinit var keyboardLayout: GridLayout
    private lateinit var overlayView: View
    private lateinit var hangmanParts: List<ImageView>

    private var wordToGuess = ""
    private val guessedLetters = mutableSetOf<Char>()
    private var mistakes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        toolbar = findViewById(R.id.toolbar)
        setupTopBar(this, toolbar)

        wordTextView = findViewById(R.id.wordTextView)
        keyboardLayout = findViewById(R.id.keyboardLayout)
        overlayView = findViewById(R.id.overlayView)

        // Partes del cuerpo en el orden en que aparecen al fallar
        hangmanParts = listOf(
            R.id.imgHangman0, R.id.imgHangman1, R.id.imgHangman2,
            R.id.imgHangman3, R.id.imgHangman4, R.id.imgHangman5
        ).map { findViewById(it) }

        val word = intent.getStringExtra(EXTRA_WORD_TO_GUESS)
        if (word == null) {
            finish()
            return
        }
        wordToGuess = word.uppercase()

        setupKeyboard()
        updateWordUI()
    }

    private fun setupKeyboard() {
        val keyMargin = resources.getDimensionPixelSize(R.dimen.key_margin)

        for (letter in 'A'..'Z') {
            val button = Button(this).apply {
                text = letter.toString()
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, KEY_COLUMN_WEIGHT)
                    setMargins(keyMargin, keyMargin, keyMargin, keyMargin)
                }
                setOnClickListener { onLetterGuessed(letter, this) }
            }
            keyboardLayout.addView(button)
        }
    }

    private fun onLetterGuessed(letter: Char, button: Button) {
        button.isEnabled = false
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.key_used))

        guessedLetters.add(letter)

        if (wordToGuess.contains(letter)) {
            updateWordUI()
            checkWinCondition()
        } else {
            mistakes++
            updateHangmanUI()
            checkLoseCondition()
        }
    }

    private fun updateWordUI() {
        wordTextView.text = formatWord(revealAll = false)
    }

    // Muestra las letras acertadas y oculta el resto con '_'
    private fun formatWord(revealAll: Boolean): String =
        wordToGuess.map { letter ->
            if (revealAll || guessedLetters.contains(letter)) letter else HIDDEN_LETTER
        }.joinToString(LETTER_SEPARATOR)

    private fun updateHangmanUI() {
        if (mistakes in 1..hangmanParts.size) {
            hangmanParts[mistakes - 1].visibility = View.VISIBLE
        }
    }

    private fun checkWinCondition() {
        if (wordToGuess.all { guessedLetters.contains(it) }) {
            endGame(getString(R.string.you_win))
        }
    }

    private fun checkLoseCondition() {
        if (mistakes >= hangmanParts.size) {
            endGame(getString(R.string.you_lose))
            wordTextView.text = formatWord(revealAll = true)
        }
    }

    private fun endGame(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        for (i in 0 until keyboardLayout.childCount) {
            keyboardLayout.getChildAt(i).isEnabled = false
        }

        // Capa invisible: el siguiente tap vuelve al selector
        overlayView.visibility = View.VISIBLE
        overlayView.setOnClickListener { finish() }
    }
}
