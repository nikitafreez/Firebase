package com.example.firebase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        val btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener {
            signInUser()
        }

        val btn_signup = findViewById<Button>(R.id.btn_signup)
        btn_signup.setOnClickListener {
            signUpUser()
        }
    }

    private fun signInUser() {
        var edt_login = findViewById<EditText>(R.id.edt_login)
        var edt_password = findViewById<EditText>(R.id.edt_password)
        auth.signInWithEmailAndPassword(edt_login.text.toString(), edt_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    Toast.makeText(baseContext, "Добро пожаловать, ${edt_login.text.toString()}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(baseContext, "Ошибка", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun signUpUser() {
        var edt_login = findViewById<EditText>(R.id.edt_login)
        var edt_password = findViewById<EditText>(R.id.edt_password)
        auth.createUserWithEmailAndPassword(edt_login.text.toString(), edt_password.text.toString())
            .addOnCompleteListener(this)  { task ->
                if(task.isSuccessful){
                    Toast.makeText(baseContext, "Пользователь зарегестрирован", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(baseContext, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    Log.i("signUpUser: ", task.exception.toString())
                }
            }
    }
}