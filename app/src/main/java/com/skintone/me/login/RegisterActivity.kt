package com.skintone.me.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.skintone.me.R
import com.skintone.me.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[RegisterViewModel::class.java]

        binding.btnRegister.setOnClickListener {
            val name = binding.edtNameRegister.text.toString()
            val gender = binding.edtGenderRegister.text.toString()
            val email = binding.edtEmailRegister.text.toString()
            val password = binding.edtPasswordRegister.text.toString()

            viewModel.isLoading.observe(this) { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }


            Log.d("RegisterActivity", "Before registrasi: $name, $gender, $email, $password")
            viewModel.register(name, gender, email, password)
            Log.d("RegisterActivity", "After registrasi")
            viewModel.registration.observe(this) { response ->
                if (response.error == false) {
                    Log.d("RegisterActivity", "onCreate: ${response.message}")
                    binding.edtNameRegister.text?.clear()
                    binding.edtGenderRegister.text?.clear()
                    binding.edtEmailRegister.text?.clear()
                    binding.edtPasswordRegister.text?.clear()

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Register Success")
                    builder.setMessage("Success Register")
                    builder.setPositiveButton("Yay") { _, _ ->
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    val dialog = builder.create()
                    dialog.show()
                }
            }

        }

        viewModel.isRegist.observe(this) { response ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Register Failed")
            builder.setMessage(response)
            builder.setNegativeButton("OK") { _, _ ->

            }
            val dialog = builder.create()
            dialog.show()

        }
    }

    @SuppressLint("Recycle")
    private fun playAnimation() {
        val heading = ObjectAnimator.ofFloat(binding.textView, View.ALPHA, 1F).setDuration(500)
        val nameTextLayout =
            ObjectAnimator.ofFloat(binding.edtNameRegister, View.ALPHA, 1F).setDuration(500)
        val genderTextLayout =
            ObjectAnimator.ofFloat(binding.edtGenderRegister, View.ALPHA, 1F).setDuration(500)
        val emailTextLayout = ObjectAnimator.ofFloat(binding.edtEmailRegister, View.ALPHA, 1F).setDuration(500)
        val passwordTextLayout = ObjectAnimator.ofFloat(binding.edtPasswordRegister, View.ALPHA, 1F).setDuration(500)
        val btRegist = ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1F).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                heading,
                nameTextLayout,
                genderTextLayout,
                emailTextLayout,
                passwordTextLayout,
                btRegist,
            )
            start()
        }
    }




}