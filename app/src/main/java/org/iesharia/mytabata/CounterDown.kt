package org.iesharia.mytabata

import android.os.CountDownTimer
import android.util.Log


class CounterDown (var segundos: Int, var loQueCambia: Any) {
    var myCounter : CountDownTimer
    var counterState : Boolean = false

    init {
        myCounter = object : CountDownTimer((segundos * 1000L), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                loQueCambia = millisUntilFinished / 1000
                Log.i("DAM", "Tick")
            }

            override fun onFinish() {
                counterState = false
            }
        }
    }

}
