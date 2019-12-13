package com.alcntml.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alcntml.myapplication.component.snackbar.MvaBottomSnackbarComponent
import com.alcntml.myapplication.component.MvaLogoutComponent
import com.alcntml.myapplication.component.snackbar.MvaTopSnackbarComponent
import com.alcntml.myapplication.extention.setSafeOnClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setNavigationOnClickListener { finish() }

        //add response to component for create navigationbar and fragments
        trayNavComp.setFragmentManager(supportFragmentManager)
        trayNavComp.initView(contentRL)

        topSnackBtn.setSafeOnClickListener {
            mvaTopSnackbarComp.showSnack(
                "TOP Bilgileri tekrar güncellemek için lütfen 50 saniye daha bekleyin",
                MvaTopSnackbarComponent.Companion.SHORT
            )
        }
        bottomSnackBtn.setSafeOnClickListener {
            mvaBottomSnackbarComp.showSnack(
                "BOTTOM Bilgileri tekrar güncellemek için lütfen 50 saniye daha bekleyin",
                MvaBottomSnackbarComponent.Companion.SHORT
            )
        }
        logoutBtn.setSafeOnClickListener {
            mvaLogoutComp.show(object : MvaLogoutComponent.OnLogoutListener {
                override fun onLogout() {
                    Toast.makeText(this@MainActivity,"Logged out!", Toast.LENGTH_SHORT).show()
                }
            },contentRL)
        }
    }
}
