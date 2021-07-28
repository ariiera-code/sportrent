package com.alnite.sportskuy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        tvLgAkun2.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            finish()
        }
        btnSignIn.setOnClickListener {
            dologin()
        }
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this@LoginActivity, BerandaActivity::class.java))
            finish()
        }
        supportActionBar?.hide()
        window.decorView.setOnSystemUiVisibilityChangeListener(object :
            View.OnSystemUiVisibilityChangeListener {
            fun OnSystemUiVisibilityChange(visibility: Int) {
                if (visibility == 0) window.decorView.setSystemUiVisibility(hideSystemBar())
            }

            override fun onSystemUiVisibilityChange(visibility: Int) {
                if (visibility == 0)
                    window.decorView.setSystemUiVisibility(hideSystemBar())
            }
        })
    }

    private fun dologin() {
        if (eTEmail.text.toString().isEmpty()) {
            eTEmail.error = "Masukkan email anda"
            eTEmail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(eTEmail.text.toString()).matches()) {
            eTEmail.error = "Email tidak cocok"
            eTEmail.requestFocus()
            return
        }
        if (eTPassword.text.toString().isEmpty()) {
            eTPassword.error = "Tolong masukkan password"
            eTPassword.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(eTEmail.text.toString(), eTPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                    // ...
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this@LoginActivity, BerandaActivity::class.java))
                finish()
            } else {
                Toast.makeText(baseContext, "Anda belum verifikasi e-mail anda", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.setSystemUiVisibility(hideSystemBar())
        }
    }

    private fun hideSystemBar(): Int {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
}