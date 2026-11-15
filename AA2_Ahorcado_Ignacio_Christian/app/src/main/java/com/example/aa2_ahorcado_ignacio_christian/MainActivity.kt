package com.example.aa2_ahorcado_ignacio_christian

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var rootLayout: LinearLayout
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rootLayout = findViewById(R.id.main)
        applySystemBarsPadding(rootLayout)

        toolbar = findViewById(R.id.toolbar)
        setupTopBar(this, toolbar)

        // Toda la pantalla actúa como botón
        rootLayout.setOnClickListener { onScreenTapped() }
    }

    private fun onScreenTapped() {
        startActivity(Intent(this, LevelSelectorActivity::class.java))
        // Para que "atrás" no vuelva a la splash
        finish()
    }
}
