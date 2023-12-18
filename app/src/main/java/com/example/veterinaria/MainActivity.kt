package com.example.veterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.veterinaria.SharedPreferens.Shared.Companion.prefs
import com.example.veterinaria.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timer()
        hideSystemUI()
    }

    fun obtenerEstadoUsuario(): Boolean {
        return prefs.getStatusUser()
    }

    fun Timer() {
        var timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                if (obtenerEstadoUsuario()) {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            Registros_Principal_Activity::class.java
                        )
                    )
                } else {
                    startActivity(Intent(this@MainActivity, login_Activity::class.java))
                }
            }
        }
        timer.start()
    }

    fun hideSystemUI() {
        //quita actionBar
        WindowCompat.setDecorFitsSystemWindows(window, false)
        //obtiene la ventana
        WindowInsetsControllerCompat(window, window.decorView).apply {
            //oculta los botones nferiores y actionbar
            hide(WindowInsetsCompat.Type.systemBars())
            //permite volver a la pantalla completa cuando se muestran los botones
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        WindowInsetsControllerCompat(
            window,
            window.decorView
        ).hide(WindowInsetsCompat.Type.systemBars())
    }
}