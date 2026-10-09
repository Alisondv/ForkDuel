# ForkDuel

ForkDuel is a GitHub-themed, 1v1 "logic duel" Android app concept: two developers
are matched against the same daily coding challenge, and the app shows how their
solutions compare (execution time, complexity, and a set of performance
attributes — Logic, Speed, Accuracy, Consistency, Reading) along with the duel's
outcome and the resulting ranking change.

This delivery covers the **Parcial: Android Views (XML) e navegação + intent**
milestone only: two screens built with traditional Android Views/XML, explicit
`Intent` navigation with data passing, and mocked data modeled as an immutable
`data class`. There is no backend, database, or Compose UI yet — that is planned
for a later milestone (Etapa 2).

## What's implemented

- **Tela 1 — `MainActivity` / `activity_main.xml`**: the day's opponent and
  challenge, the player's attributes, and current ranking. Tapping
  "Resolve challenge" opens Tela 2.
- **Tela 2 — `ResultActivity` / `activity_result.xml`**: the duel result —
  side-by-side code comparison, attributes comparison, verdict, and updated
  ranking. A "← Back" button (and the system Back gesture) returns to Tela 1.
- **Navigation**: `MainActivity` opens `ResultActivity` with an explicit
  `Intent`, passing the challenge being resolved as extras (title, opponent
  username, and an optional difficulty).
- **Data model**: `ChallengeInfo` is an immutable `data class`
  (`title: String`, `opponentUsername: String`, `difficulty: String?`) shared
  by both screens. `difficulty` is nullable on purpose, and the UI hides its
  label when it's absent instead of faking a value.
- **Mock data**: all content (challenge, opponent, attributes, duel result) is
  static/mocked in `strings.xml` and in the `ChallengeInfo` instances built in
  code — no API or database calls.

## Running the project locally

1. Clone the repository:
   ```
   git clone https://github.com/Alisondv/ForkDuel.git
   ```
2. Open the project folder in Android Studio (a recent stable version; the
   project targets `compileSdk`/`targetSdk` 37, `minSdk` 26).
3. Let Gradle sync — there are no API keys, secrets, or extra setup steps
   required for this stage.
4. Run the `app` configuration on an emulator or physical device running
   Android 8.0 (API 26) or newer.

## External libraries used

All of the following are the standard AndroidX/Material libraries included by
Android Studio's default project template — nothing extra was added for this
milestone:

- **androidx.appcompat** — provides `AppCompatActivity`, the base class both
  screens extend from, for consistent behavior across Android versions.
- **androidx.activity-ktx** — Kotlin extensions for `Activity`, including
  `enableEdgeToEdge()`, used so both screens draw edge-to-edge.
- **androidx.core-ktx** — Kotlin extensions for core Android APIs; used here
  for `ViewCompat`/`WindowInsetsCompat` when applying system bar insets as
  padding.
- **androidx.constraintlayout** — used on Tela 1 to position the five
  attribute labels around the radar chart icon.
- **com.google.android.material** — Material Components, used for the app's
  base theme (`Theme.ForkDuel`).
