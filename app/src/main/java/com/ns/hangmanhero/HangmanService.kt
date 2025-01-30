package com.ns.hangmanhero

import com.ns.hangmanhero.screens.game.data.KeyboardRow
import com.ns.hangmanhero.screens.game.states.KeyboardKeyState

class HangmanService {
    fun getKeyboardKeys(row: KeyboardRow): List<KeyboardKeyState> {
        return when(row){
            KeyboardRow.TOP -> "qwertyuiop".map { KeyboardKeyState(it, true) }
            KeyboardRow.MIDDLE -> "asdfghjkl".map { KeyboardKeyState(it, true) }
            KeyboardRow.BOTTOM -> "zxcvbnm".map { KeyboardKeyState(it, true) }
        }
    }
}