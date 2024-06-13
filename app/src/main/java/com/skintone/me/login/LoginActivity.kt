package com.skintone.me.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.skintone.me.MainActivity
import com.skintone.me.database.User
import com.skintone.me.database.UserRepository
import com.skintone.me.database.api.ApiConfig
import com.skintone.me.database.dataStore
import com.skintone.me.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var preferenceManager: com.skintone.me.database.PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()

        preferenceManager = com.skintone.me.database.PreferenceManager.getInstance(this.dataStore)

        viewModel = LoginViewModel(UserRepository.getInstance(preferenceManager, ApiConfig.getApiService()))

        binding.btnRegisterLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch {
            preferenceManager.getSession().collect{user->
                Log.d("LOGIN", "onCreate: ${user.toString()}")
                if (user.isLogin) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmailLogin.text.toString()
            val password = binding.edtPasswordLogin.text.toString()
            viewModel.isLoading.observe(this) { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }

            viewModel.login(email, password).observe(this) { response ->
                if (response.status == false) {
                    lifecycleScope.launch {
                        preferenceManager.saveSession(
                            User(
                                response.username.toString(),
                                AUTH_KEY + (response.data?.token.toString()),
                                true,
                                response.gender.toString(),
                                response.email.toString(),
                            )
                        )

                        preferenceManager.saveUsername(response.username.toString())

                        Log.d(
                            "Login Activity",
                            "response ${response.data?.userId} ${response.data?.token}"

                        )

                    }

                    val builder = AlertDialog.Builder(this@LoginActivity)

                    builder.setTitle("Login Success!!")
                    builder.setMessage("Hallo, Welcome ${response.username}")
                    builder.setPositiveButton("OK") { _, _ ->
                        startActivity(intent)
                        finish()

                    }
                    val dialog = builder.create() 
                    dialog.show()
                }

            }
        }

        viewModel.login.observe(this) { response ->
            CoroutineScope(Dispatchers.Main).launch {
                val builder = AlertDialog.Builder(this@LoginActivity)
                builder.setTitle("Login Failed")
                builder.setMessage(response)
                builder.setPositiveButton("OK") { _, _ ->

                }
                val dialog = builder.create()
                dialog.show()
            }
        }
    }

    @SuppressLint("Recycle")
    private fun playAnimation() {
        val heading = ObjectAnimator.ofFloat(binding.tvWelcome, View.ALPHA, 1F).setDuration(500)
        val emailTextLayout =
            ObjectAnimator.ofFloat(binding.edtEmailLogin, View.ALPHA, 1F).setDuration(500)
        val passwordTextLayout =
            ObjectAnimator.ofFloat(binding.edtPasswordLogin, View.ALPHA, 1F).setDuration(500)
        val btLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1F).setDuration(500)
        val tvOr = ObjectAnimator.ofFloat(binding.tvOr, View.ALPHA, 1F).setDuration(500)
        val view = ObjectAnimator.ofFloat(binding.view, View.ALPHA, 1F).setDuration(500)
        val view2 = ObjectAnimator.ofFloat(binding.view2, View.ALPHA, 1F).setDuration(500)
        val tvdonthaveacc = ObjectAnimator.ofFloat(binding.tvDontHaveAccount, View.ALPHA, 1F).setDuration(500)
        val btRegis = ObjectAnimator.ofFloat(binding.btnRegisterLogin, View.ALPHA, 1F).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                heading,
                emailTextLayout,
                passwordTextLayout,
                btLogin,
                view,
                view2,
                tvOr,
                tvdonthaveacc,
                btRegis
            )
            start()
        }
    }

    companion object {
        private const val AUTH_KEY = "Bearer "
    }
}