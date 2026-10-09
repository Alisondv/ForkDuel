package com.alison.forkduel

// Imports
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Result screen - mirrors MainActivity's setup, just pointing at activity_result.xml.
// No logic here yet: this only exists so Tela 2 can be opened and viewed.
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Explicit back navigation. The system Back button/gesture already
        // closes this screen and returns to MainActivity on its own - this
        // button just gives the same action a visible, tappable spot on screen.
        findViewById<android.widget.TextView>(R.id.button_back).setOnClickListener {
            finish()
        }
    }
}
