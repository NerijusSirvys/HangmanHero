package com.ns.hangmanhero.mappers

import com.ns.hangmanhero.data.Hint
import com.ns.hangmanhero.screens.game.states.HintElementState

fun Hint.toHintElementState(): HintElementState {
    return HintElementState(
        id = this.id,
        text = this.text,
        cost = this.strength.ordinal,
        isEnabled = true
    )
}
