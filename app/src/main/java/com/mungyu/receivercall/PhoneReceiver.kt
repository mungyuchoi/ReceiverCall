package com.mungyu.receivercall

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.telephony.SmsMessage
import android.util.Log

class PhoneReceiver : BroadcastReceiver() {

    private lateinit var mediaPlayer: MediaPlayer
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == SMS_RECEIVED) {

            val bundle = intent?.extras
            val message = parseMessage(bundle)
            Log.d(TAG, "onReceiver message:$message")

            if (message.size > 0) {
                val contents = message[0]?.messageBody.toString()
                Log.d(TAG, "onReceiver contents:$contents")
                if (contents.contains("@#")) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.sound)
                    mediaPlayer.start()
                    Handler().postDelayed({
                        mediaPlayer.stop()
                        mediaPlayer.release()
                    }, 15000)
                }
            }
        }
    }

    private fun parseMessage(bundle: Bundle?): Array<SmsMessage?> {
        val objs = bundle?.get("pdus") as Array<Any>
        val messages = arrayOfNulls<SmsMessage>(objs.size)

        for (i in 0 until messages.size) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val format = bundle?.getString("format")
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
            } else {
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
            }
        }
        return messages
    }

    companion object {
        private const val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
        private const val TAG = "PhoneReceiver"

    }
}