package com.alcntml.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alcntml.myapplication.component.snackbar.MvaBottomSnackbarComponent
import com.alcntml.myapplication.component.snackbar.MvaTopSnackbarComponent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener { finish() }

        //add response to component for create navigationbar and fragments
        trayNavComp.setFragmentManager(supportFragmentManager)
        trayNavComp.initView()

        topSnackBtn.setOnClickListener{
            mvaTopSnackbarComp.showSnack("TOP Bilgileri tekrar güncellemek için lütfen 50 saniye daha bekleyin", MvaTopSnackbarComponent.Companion.SHORT)
        }
        bottomSnackBtn.setOnClickListener{
            mvaBottomSnackbarComp.showSnack("BOTTOM Bilgileri tekrar güncellemek için lütfen 50 saniye daha bekleyin", MvaBottomSnackbarComponent.Companion.SHORT)
        }
    }
}
