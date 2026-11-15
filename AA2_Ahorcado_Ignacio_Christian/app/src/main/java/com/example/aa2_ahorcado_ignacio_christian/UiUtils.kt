package com.example.aa2_ahorcado_ignacio_christian

import android.content.res.Configuration
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.os.LocaleListCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private const val LANGUAGE_SPANISH = "es"
private const val LANGUAGE_ENGLISH = "en"

// Evita que las barras del sistema tapen la UI con edge-to-edge
fun applySystemBarsPadding(view: View) {
    ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }
}

// Menú común de la topbar: tema e idioma
fun setupTopBar(activity: AppCompatActivity, toolbar: Toolbar) {
    toolbar.setOnMenuItemClickListener { item ->
        when (item.itemId) {
            R.id.action_toggle_theme -> {
                toggleTheme(activity)
                true
            }
            R.id.action_toggle_language -> {
                toggleLanguage(activity)
                true
            }
            else -> false
        }
    }
}

private fun toggleTheme(activity: AppCompatActivity) {
    val currentMode = activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    val isNight = currentMode == Configuration.UI_MODE_NIGHT_YES
    AppCompatDelegate.setDefaultNightMode(
        if (isNight) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
    )
}

// Alterna entre castellano e inglés (por defecto se usa el idioma del sistema)
private fun toggleLanguage(activity: AppCompatActivity) {
    val currentLanguage = activity.resources.configuration.locales[0].language
    val newLanguage = if (currentLanguage == LANGUAGE_SPANISH) LANGUAGE_ENGLISH else LANGUAGE_SPANISH
    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(newLanguage))
}
