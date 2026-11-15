// Generado con asistencia de IA (Claude)
package com.example.aa2_ahorcado_ignacio_christian

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var rootLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rootLayout = findViewById(R.id.main)
        applySystemBarsPadding(rootLayout)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
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

        // Toda la pantalla actúa como botón
        rootLayout.setOnClickListener { onScreenTapped() }
    }

    // finish() para que "atrás" no vuelva a la splash
    private fun onScreenTapped() {
        startActivity(Intent(this, LevelSelectorActivity::class.java))
        finish()
    }
}
