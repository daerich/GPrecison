package dev.daerich.gprecision

/* ------------------------------------------
			COPYRIGHT © DAERICH 2020
ALL RIGHTS RESERVED EXCEPT OTHERWISE STATED IN COPYRIGHT.TXT
   ------------------------------------------ */

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

object GPSAPPGLOBALS{
    val VERSION = "v. 0.0.5"
}

class MainActivity : AppCompatActivity() {

    private lateinit var requestPermissionLauncher : ActivityResultLauncher<String>
    private lateinit var gpsLocationListener : LocationLocalManager
    private lateinit var aboutBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        aboutBtn = findViewById(R.id.abtbtn)
        aboutBtn.setOnClickListener { Intent(this, FragmentManage::class.java).also { startActivity(it) }}
    }

    override fun onResume() {
        super.onResume()
        gpsLocationListener = LocationLocalManager(this)
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) {isGranted : Boolean -> if(!isGranted){
            Toast.makeText(this, "Failure!", Toast.LENGTH_SHORT).show()
        }

        }                                                //dev/null


        when {
            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                gpsLocationListener.startMeasure(R.id.GPSfront)
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                Toast.makeText(this, "This permission is required to run this app efficiently! Please grant it!", Toast.LENGTH_LONG).show()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
        }

    override fun onDestroy() {
        super.onDestroy()
        gpsLocationListener.stop()
    }

}