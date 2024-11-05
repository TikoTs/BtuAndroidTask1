package com.example.project1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val basicEmailRegex = Regex("^\\S+@\\S+\\.\\S+$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendMessage.setOnClickListener {
            if (!isValidMail(binding.etEmail.text.toString())) {
                Toast.makeText(this, "Email is Not Valid!", Toast.LENGTH_SHORT).show()
            } else if (isValidMessage(binding.etMessage.text.toString())) {
                Toast.makeText(this, "Message text is too long!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ResultActivity::class.java).putExtras(
                    Bundle().apply {
                        putString(EMAIL, binding.etEmail.text.toString())
                        putString(MESSAGE, binding.etMessage.text.toString())
                    }
                )
                startActivity(intent)
            }
        }
    }

    companion object {
        const val EMAIL = "email"
        const val MESSAGE = "message"
    }

    private fun isValidMail(mail: String): Boolean {
        return !(mail.isBlank() || mail.matches(basicEmailRegex))

    }
    private fun isValidMessage(message:String):Boolean{
        return message.length in 0..250

    }


}