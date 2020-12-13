package dev.daerich.gprecision

/* ------------------------------------------
			COPYRIGHT © DAERICH 2020
ALL RIGHTS RESERVED EXCEPT OTHERWISE STATED IN COPYRIGHT.TXT
   ------------------------------------------ */

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity

class FragmentManage : AppCompatActivity(R.layout.fragment_about) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      if (savedInstanceState == null && intent.getIntExtra("viewmodel", -1) == 1) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.aboutView, About())
                .commitNow()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainnav, menu)
        return true
    }
}