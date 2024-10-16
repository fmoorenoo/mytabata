package org.iesharia.mytabata

import android.os.CountDownTimer
import android.util.Log


class CounterDown (var segundos: Int, var loQueHaceAlHacerTick: (Long) -> Unit) {
    var myCounter : CountDownTimer
    var counterState : Boolean = false

    init {
        myCounter = object : CountDownTimer((segundos * 1000L), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                loQueHaceAlHacerTick(millisUntilFinished / 1000)
            }

            override fun onFinish() {
                counterState = false
            }
        }
    }

}
