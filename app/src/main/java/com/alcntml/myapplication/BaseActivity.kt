package com.alcntml.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alcntml.myapplication.component.snackbar.BottomSnackbarComponent
import com.alcntml.myapplication.component.LogoutComponent
import com.alcntml.myapplication.component.snackbar.TopSnackbarComponent
import com.alcntml.myapplication.extention.setSafeOnClickListener
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.content_base.*

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        toolbar.setNavigationOnClickListener { finish() }

        //add response to component for create navigationbar and fragments
        trayNavComp.setFragmentManager(supportFragmentManager)
        trayNavComp.initView()

        topSnackBtn.setSafeOnClickListener {
            mvaTopSnackbarComp.showSnack(
                "TOP Bilgileri tekrar güncellemek için lütfen 50 saniye daha bekleyin",
                TopSnackbarComponent.Companion.SHORT
            )
        }
        bottomSnackBtn.setSafeOnClickListener {
            mvaBottomSnackbarComp.showSnack(
                "BOTTOM Bilgileri tekrar güncellemek için lütfen 50 saniye daha bekleyin",
                BottomSnackbarComponent.Companion.SHORT
            )
        }

        logoutBtn.setSafeOnClickListener {
            mvaLogoutComp.show(object : LogoutComponent.OnLogoutListener {
                override fun onLogout() {
                    Toast.makeText(this@BaseActivity,"Logged out!", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
