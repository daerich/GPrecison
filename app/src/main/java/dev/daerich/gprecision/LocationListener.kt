package dev.daerich.gprecision

/* ------------------------------------------
			COPYRIGHT © DAERICH 2020
ALL RIGHTS RESERVED EXCEPT OTHERWISE STATED IN COPYRIGHT.TXT
   ------------------------------------------ */

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

internal class LocationLocalManager(val context : AppCompatActivity) {
    private val locMan = context.getSystemService(AppCompatActivity.LOCATION_SERVICE) as? LocationManager
    private val isGPS = locMan?.isProviderEnabled(LocationManager.GPS_PROVIDER)
    private lateinit var applocationListener: LocationListn;

    fun startMeasure(@IdRes textid: Int) {
        if (isGPS == true && locMan != null) {
            applocationListener = LocationListn(context, textid)
            // locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3L, 10F) { location -> context.findViewById<TextView>(textid).setText((location.speed.times(3.6)).roundToInt().toString()) }
            locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3L, 10F, applocationListener)
        } else {
            AlertDialog.Builder(context).let {
                it.setMessage("Please enable gps services and restart application")
                it.setCancelable(false)
                it.setTitle("Error: No GPS Services")
                it.setNeutralButton("Restart") { _, _ ->
                    Handler(Looper.getMainLooper()).postDelayed({ context.recreate() }, 2000)}
                    it.create().show()
                }
            }
        }

        fun stop() {
            if (isGPS == true && locMan != null) {
                locMan.removeUpdates(applocationListener) //Clean Exit
            }
        }
    }

    internal class LocationListn(val context: AppCompatActivity, @IdRes val textid: Int) : LocationListener {
        override fun onLocationChanged(location: Location) {
            context.findViewById<TextView>(textid).text = (location.speed.times(3.6)).roundToInt().toString()
        }

        override fun onProviderDisabled(provider: String) {
            showException()
        }

        override fun onProviderEnabled(provider: String) {
            showException()
        }

        private fun showException() {
            context.findViewById<TextView>(textid).text = "No speed" //Moaning Compiler
            AlertDialog.Builder(context).let {
                it.setMessage("Please enable gps services and restart application")
                it.setCancelable(false)
                it.setTitle("Error: No GPS Services")
                it.setNeutralButton("Restart") { _, _ ->
                     Handler(Looper.getMainLooper()).postDelayed({ context.recreate() }, 2000) }
                it.create().show()
            }
        }
    }