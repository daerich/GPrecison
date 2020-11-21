package dev.daerich.gprecision
/* ------------------------------------------
			COPYRIGHT © DAERICH 2020
ALL RIGHTS RESERVED EXCEPT OTHERWISE STATED IN COPYRIGHT.TXT
   ------------------------------------------ */

import android.location.LocationManager
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt
import kotlin.system.exitProcess

internal class LocationListener(val context : AppCompatActivity) {
    val locMan = context.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
    var isGPS = locMan.isProviderEnabled(LocationManager.GPS_PROVIDER)
    fun startMeasure(@IdRes textid : Int) {
        if (isGPS) {
            locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3L, 10F) { location -> context.findViewById<TextView>(textid).setText((location.speed.times(3.6)).roundToInt().toString()) }
        }
        else{
            AlertDialog.Builder(context).let{
                it.setMessage("Please enable gps services and restart application")
                it.setCancelable(false)
                it.setTitle("Error: No GPS Services")
                it.setNeutralButton("Restart"){ _, _ -> exitProcess(0) }
                it.create().show()
            }
        }
    }
}