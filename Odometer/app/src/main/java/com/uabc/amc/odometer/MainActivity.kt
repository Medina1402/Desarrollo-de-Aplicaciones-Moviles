package com.uabc.amc.odometer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.uabc.amc.odometer.OdometerService.OdometerBinder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 698
    private lateinit var odometer: OdometerService
    private var bound = false

    private val connection: ServiceConnection = object: ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val odometerBinder = p1 as OdometerBinder
            odometer = odometerBinder.odometer
            bound = true
        }
        override fun onServiceDisconnected(p0: ComponentName?) {
            bound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        watchMileage()
    }

    override fun onStart() {
        super.onStart()

        if (ContextCompat.checkSelfPermission(this, OdometerService.PERMISSION_STRING) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(OdometerService.PERMISSION_STRING),
                PERMISSION_REQUEST_CODE
            )
        }
        
        val intent = Intent(this, OdometerService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
        bound = false
    }

    override fun onStop() {
        super.onStop()
        if(bound) {
            unbindService(connection)
            bound = false
        }
    }

    private fun watchMileage() {
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                var distanceTemp = 0.0
                if (bound && odometer != null) distanceTemp = odometer.distance
                val distanceStr = String.format("%1$,.2f meters", distanceTemp)
                distance.text = distanceStr
                handler.postDelayed(this, 1000)
            }
        })
    }
}