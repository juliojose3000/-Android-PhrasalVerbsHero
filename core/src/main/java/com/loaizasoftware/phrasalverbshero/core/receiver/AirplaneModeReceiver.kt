package com.loaizasoftware.phrasalverbshero.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.loaizasoftware.phrasalverbshero.core.R

class AirplaneModeReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isAirplaneModeOn = intent.getBooleanExtra("state", false) //Airplane mode state
            val message = if (isAirplaneModeOn) context?.getString(R.string.airplane_mode_on) else context?.getString(R.string.airplane_mode_off)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

}