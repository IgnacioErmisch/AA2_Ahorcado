// Generado con asistencia de IA (Claude)
package com.example.aa2_ahorcado_ignacio_christian

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * Splash screen del juego. Muestra el título y una imagen; al tocar cualquier
 * punto de la pantalla se abre el selector de niveles.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var rootLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rootLayout = findViewById(R.id.main)
        applySystemBarsPadding(rootLayout)

        // Toda la pantalla actúa como botón
        rootLayout.setOnClickListener { onScreenTapped() }
    }

    /** Abre el selector de niveles y cierra la splash para que "atrás" no vuelva a ella. */
    private fun onScreenTapped() {
        startActivity(Intent(this, LevelSelectorActivity::class.java))
        finish()
    }
}
