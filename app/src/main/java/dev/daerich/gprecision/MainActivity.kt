package dev.daerich.gprecision

/* ------------------------------------------
			COPYRIGHT © DAERICH 2020
ALL RIGHTS RESERVED EXCEPT OTHERWISE STATED IN COPYRIGHT.TXT
   ------------------------------------------ */

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

object GPSAPPGLOBALS{
    val VERSION = "v. 0.0.6"
}

class MainActivity : AppCompatActivity() {

    private lateinit var requestPermissionLauncher : ActivityResultLauncher<String>
    private lateinit var gpsLocationListener : LocationLocalManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        gpsLocationListener = LocationLocalManager(this)
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) {isGranted : Boolean -> if(!isGranted){
            Toast.makeText(this, "Failure!", Toast.LENGTH_SHORT).show()
        }
            else if(isGranted){
                gpsLocationListener.startMeasure(R.id.GPSfront)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.mainnav, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutitem -> {
             startActivity(Intent(this, FragmentManage::class.java).apply {
                     putExtra("viewmodel", 1 )
                 })
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroy() {
        super.onDestroy()
        gpsLocationListener.stop()
    }

}