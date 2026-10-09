package com.alison.forkduel

// Imports
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Result screen - mirrors MainActivity's setup, pointing at activity_result.xml.
// Receives a ChallengeInfo (via Intent extras) from MainActivity and uses it
// to fill in the parts of the screen that depend on which challenge was resolved.
class ResultActivity : AppCompatActivity() {

    // Keys for the data passed in from MainActivity. Declared here (on the
    // receiving side) so MainActivity can reference them when building the Intent.
    companion object {
        const val EXTRA_CHALLENGE_TITLE = "extra_challenge_title"
        const val EXTRA_OPPONENT_USERNAME = "extra_opponent_username"
        const val EXTRA_DIFFICULTY = "extra_difficulty"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Rebuild the ChallengeInfo from what MainActivity sent.
        // title and opponentUsername are required, so a sane mock is used if
        // they're somehow missing. difficulty is genuinely optional - it stays
        // nullable, no fallback value is invented for it.
        val challenge = ChallengeInfo(
            title = intent.getStringExtra(EXTRA_CHALLENGE_TITLE)
                ?: getString(R.string.challenge_title_mock),
            opponentUsername = intent.getStringExtra(EXTRA_OPPONENT_USERNAME)
                ?: getString(R.string.opponent_username_mock),
            difficulty = intent.getStringExtra(EXTRA_DIFFICULTY)
        )

        findViewById<TextView>(R.id.text_challenge_subtitle).text =
            "${challenge.title} · ${getString(R.string.duel_date_mock)}"

        findViewById<TextView>(R.id.text_opponent_panel_header).text =
            challenge.opponentUsername

        // Optional value handled explicitly: show the row only when a
        // difficulty actually came with the challenge, hide it otherwise.
        val difficultyLabel = findViewById<TextView>(R.id.text_result_difficulty)
        if (challenge.difficulty != null) {
            difficultyLabel.text =
                getString(R.string.result_difficulty_format, challenge.difficulty)
            difficultyLabel.visibility = View.VISIBLE
        } else {
            difficultyLabel.visibility = View.GONE
        }

        // Explicit back navigation. The system Back button/gesture already
        // closes this screen and returns to MainActivity on its own - this
        // button just gives the same action a visible, tappable spot on screen.
        findViewById<TextView>(R.id.button_back).setOnClickListener {
            finish()
        }
    }
}
