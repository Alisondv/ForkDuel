package com.alison.forkduel

// Imports
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Opens the result screen, passing along the challenge that's being
        // resolved. No real duel logic yet - this will be replaced later.
        findViewById<android.widget.Button>(R.id.button_resolve_challenge).setOnClickListener {
            val challenge = ChallengeInfo(
                title = getString(R.string.challenge_title_mock),
                opponentUsername = getString(R.string.opponent_username_mock),
                difficulty = getString(R.string.difficulty_easy_mock)
            )

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(ResultActivity.EXTRA_CHALLENGE_TITLE, challenge.title)
            intent.putExtra(ResultActivity.EXTRA_OPPONENT_USERNAME, challenge.opponentUsername)
            intent.putExtra(ResultActivity.EXTRA_DIFFICULTY, challenge.difficulty)
            startActivity(intent)
        }
    }
}
