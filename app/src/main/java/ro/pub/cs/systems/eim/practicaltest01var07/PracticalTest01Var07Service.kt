package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import kotlin.random.Random

class PracticalTest01Var07Service : Service() {

    private val handler = Handler()
    private var running = false

    companion object {
        const val ACTION_BROADCAST = "ro.pub.cs.systems.eim.practicaltest01var07.RANDOM_NUMBERS"
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // Nu avem nevoie de legare (bound service)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        running = true
        generateAndBroadcastNumbers()
        return START_STICKY // Serviciul continuă să ruleze până este oprit explicit
    }

    private fun generateAndBroadcastNumbers() {
        handler.post(object : Runnable {
            override fun run() {
                if (!running) return

                // Generăm 4 numere întregi aleatorii
                val randomNumbers = List(4) { Random.nextInt(0, 100) }

                // Creăm și trimitem un Intent cu difuzare
                val broadcastIntent = Intent(ACTION_BROADCAST).apply {
                    putExtra("randomNumbers", randomNumbers.toIntArray())
                }
                sendBroadcast(broadcastIntent)

                Log.d("PracticalTest01Var07Service", "Broadcast sent: $randomNumbers")

                // Replanificăm rularea la fiecare 10 secunde
                handler.postDelayed(this, 10_000)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        running = false
        handler.removeCallbacksAndMessages(null)
        Log.d("PracticalTest01Var07Service", "Service stopped")
    }
}
