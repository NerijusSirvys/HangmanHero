package com.ns.hangmanhero.utils

import com.ns.hangmanhero.R

class FrameHelpers {
    companion object {
        fun updateFrame(remainingGuesses: Int): Int {
            return when (remainingGuesses) {
                0 -> R.drawable.hangman_0
                1 -> R.drawable.hangman_1
                2 -> R.drawable.hangman_2
                3 -> R.drawable.hangman_3
                4 -> R.drawable.hangman_4
                5 -> R.drawable.hangman_5
                else -> R.drawable.hangman_6
            }
        }
    }
}
