package com.alison.forkduel

// Immutable model for the challenge being duelled.
// Shared between MainActivity (where the duel starts) and ResultActivity
// (where the result is shown) - it's the "dados exibidos" the two screens
// pass between each other.
//
// `difficulty` is optional: not every challenge is guaranteed to have one
// set, so it's nullable instead of defaulting to a fake value like "".
data class ChallengeInfo(
    val title: String,
    val opponentUsername: String,
    val difficulty: String? = null
)
