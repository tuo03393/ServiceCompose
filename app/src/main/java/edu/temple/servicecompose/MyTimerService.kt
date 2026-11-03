package edu.temple.servicecompose

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.Lifecycle.Event.Companion.downTo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MyTimerService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
        //Ignore this for now.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.run{
            getIntExtra(TIMER_KEY, 10).let {startValue ->
                //startTimer()
                CoroutineScope(Dispatchers.Default).launch {
                    for (i in startValue downTo 0) {
                        Log.d("Countdown", "$i")
                        delay(1.seconds)
                    }
                }

            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    /*fun startTimer() {

        val scope = CoroutineScope( Dispatchers.Default)

        for (i in 50 downTo 0) {
            Log.d("Countdown", i.toString())
        }
    } */

}