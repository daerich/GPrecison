package dev.daerich.gprecision

/* ------------------------------------------
			COPYRIGHT © DAERICH 2020
ALL RIGHTS RESERVED EXCEPT OTHERWISE STATED IN COPYRIGHT.TXT
   ------------------------------------------ */

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FragmentManage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_about)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.aboutView, About())
                .commitNow()
        }
    }
}