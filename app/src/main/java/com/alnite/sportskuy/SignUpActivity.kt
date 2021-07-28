package com.alnite.sportskuy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

@Suppress("DEPRECATION")
class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        btnDaftar.setOnClickListener {
            signUpUser()
        }
        tvRgAkun2.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
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

    private fun signUpUser() {
        if (etEmail.text.toString().isEmpty()) {
            etEmail.error = "Masukkan email anda"
            etEmail.requestFocus()
            return
        }
        if (etUsername.text.toString().isEmpty()) {
            etUsername.error = "Masukkan username"
            etUsername.requestFocus()
            return
        }
        if (eTPhone.text.toString().isEmpty()) {
            eTPhone.error = "Masukkan nomor HP anda"
            eTPhone.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
            etEmail.error = "Email tidak cocok"
            etEmail.requestFocus()
            return
        }
        if (etPassword.text.toString().isEmpty()) {
            etPassword.error = "Tolong masukkan password"
            etPassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val currentuser = auth.currentUser
                    val currentUSerDb = databaseReference?.child((currentuser?.uid!!))
                    currentUSerDb?.child("phone")?.setValue(eTPhone.text.toString())
                    currentUSerDb?.child("username")?.setValue(etUsername.text.toString())
                    currentuser!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            }
                        }
                    Toast.makeText(baseContext, "Silahkan verifikasi email anda", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        baseContext, "Gagal! Cek email dan password anda!",
                        Toast.LENGTH_SHORT
                    ).show()
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