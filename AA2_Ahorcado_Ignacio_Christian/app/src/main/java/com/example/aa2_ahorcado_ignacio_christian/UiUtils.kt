// Generado con asistencia de IA (Claude)
package com.example.aa2_ahorcado_ignacio_christian

import android.content.res.Configuration
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Con edge-to-edge las barras del sistema tapan la UI; se añade su tamaño como padding
fun applySystemBarsPadding(view: View) {
    ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }
}