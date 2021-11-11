package com.wuhome.firebaseauthdemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private var mContext : Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mContext = applicationContext
        tv_login.setOnClickListener {
            //startActivity(Intent(this, LoginActivity::class.java))
            onBackPressed()
        }

        val btnLogin = findViewById<Button>(R.id.btnRegister)
        val etRegisterEmail = findViewById<EditText>(R.id.et_register_email)
        val etRegisterPassword = findViewById<EditText>(R.id.et_register_password)
        btnRegister.setOnClickListener {
            when{
                TextUtils.isEmpty(etRegisterEmail.text.toString().trim{it<=' '})->{
                    Toast.makeText(
                        mContext,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    )
                }
                TextUtils.isEmpty(etRegisterPassword.text.toString().trim{it<=' '})->{
                    Toast.makeText(
                        mContext,
                        "Please enter password.",
                        Toast.LENGTH_SHORT
                    )
                }
                else->{
                    val email: String = etRegisterEmail.text.toString().trim {it<=' '}
                    val password: String = etRegisterPassword.text.toString().trim {it<=' '}
                    
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult>{task ->
                                if(task.isSuccessful){
                                    var firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        mContext,
                                        "You are registered successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent =
                                        Intent(mContext, MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                }else{
                                    Toast.makeText(
                                        mContext,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                }
            }
                  
        }
    }
}