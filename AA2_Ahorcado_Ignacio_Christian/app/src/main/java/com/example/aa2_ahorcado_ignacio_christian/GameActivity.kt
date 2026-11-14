package com.example.aa2_ahorcado_ignacio_christian

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private lateinit var wordTextView: TextView
    private lateinit var keyboardLayout: GridLayout
    private lateinit var overlayView: View

    // Lista para guardar las 6 partes del cuerpo
    private val hangmanParts = mutableListOf<ImageView>()

    private var wordToGuess = ""
    private val guessedLetters = mutableSetOf<Char>()
    private var mistakes = 0
    private val maxMistakes = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Enlazar las vistas principales del XML
        wordTextView = findViewById(R.id.wordTextView)
        keyboardLayout = findViewById(R.id.keyboardLayout)
        overlayView = findViewById(R.id.overlayView)

        // Bucle para buscar dinámicamente img_hangman_0 hasta img_hangman_5 y guardarlos
        for (i in 0..5) {
            val resourceId = resources.getIdentifier("img_hangman_$i", "id", packageName)
            if (resourceId != 0) {
                val imageView = findViewById<ImageView>(resourceId)
                hangmanParts.add(imageView)
            }
        }

        // Recibir la palabra a adivinar desde LevelSelectorActivity
        wordToGuess = intent.getStringExtra("WORD_TO_GUESS")?.uppercase() ?: "KOTLIN"

        setupKeyboard()
        updateWordUI()
        updateHangmanUI()
    }

    private fun setupKeyboard() {
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        for (char in alphabet) {
            val button = Button(this).apply {
                text = char.toString()
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    setMargins(4, 4, 4, 4)
                }
                setOnClickListener { onLetterGuessed(char, this) }
            }
            keyboardLayout.addView(button)
        }
    }

    private fun onLetterGuessed(letter: Char, button: Button) {
        // Deshabilitar el botón y ponerlo gris
        button.isEnabled = false
        button.setBackgroundColor(Color.GRAY)

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
        val displayWord = StringBuilder()
        for (char in wordToGuess) {
            if (guessedLetters.contains(char)) {
                displayWord.append("$char ")
            } else {
                displayWord.append("_ ") // Oculta la letra con el guión
            }
        }
        wordTextView.text = displayWord.toString().trim()
    }

    private fun updateHangmanUI() {
        // Si hay 1 error, muestra el índice 0 de la lista. Si hay 6, el índice 5.
        if (mistakes > 0 && mistakes <= hangmanParts.size) {
            hangmanParts[mistakes - 1].visibility = View.VISIBLE
        }
    }

    private fun checkWinCondition() {
        if (wordToGuess.all { guessedLetters.contains(it) }) {
            endGame("You Win")
        }
    }

    private fun checkLoseCondition() {
        if (mistakes >= maxMistakes) {
            endGame("You Lose")
            // Revelar la palabra al perder
            wordTextView.text = wordToGuess.map { "$it " }.joinToString("").trim()
        }
    }

    private fun endGame(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        // Bloquear todos los botones del teclado
        for (i in 0 until keyboardLayout.childCount) {
            keyboardLayout.getChildAt(i).isEnabled = false
        }

        // Activar la capa invisible para capturar el tap final y volver atrás
        overlayView.visibility = View.VISIBLE
        overlayView.setOnClickListener {
            finish()
        }
    }
}