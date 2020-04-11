package com.example.polyclinic.polyclinic.android.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.polyclinic.polyclinic.android.BaseActivity
import com.example.polyclinic.polyclinic.android.BaseToolbarActivity
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.api.RetrofitFactory
import com.example.polyclinic.polyclinic.android.api.UserApiService
import com.example.polyclinic.polyclinic.android.data.Result
import com.example.polyclinic.polyclinic.android.data.User
import com.example.polyclinic.polyclinic.android.polyclinic.PolyclinicMainToolbarActivity
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {

    private var usernameInput: AutoCompleteTextView? = null
    private var passwordInput: EditText? = null
    private var confirmPasswordLayout: TextInputLayout? = null
    private var confirmPasswordInput: EditText? = null
    private var loginButton: Button? = null
    private var signUpTv: TextView? = null
    private var incorrectUsername: TextView? = null
    private var incorrectPassword: TextView? = null
    private var incorrectConfirmPassword: TextView? = null
    private var errorLogin: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        usernameInput = findViewById(R.id.input_username)
        passwordInput = findViewById(R.id.password_input)
        confirmPasswordLayout = findViewById(R.id.confirm_password_input_layout)
        confirmPasswordInput = findViewById(R.id.confirm_password_input)
        loginButton = findViewById(R.id.login_button)
        signUpTv = findViewById(R.id.sign_up_tv)
        incorrectUsername = findViewById(R.id.incorrect_login)
        incorrectPassword = findViewById(R.id.incorrect_password)
        incorrectConfirmPassword = findViewById(R.id.incorrect_confirm_password)
        errorLogin = findViewById(R.id.error_login_tv)

        signUpTv?.setOnClickListener { setOnSignUpClickListener() }
        loginButton?.setOnClickListener { setOnLoginClickListener() }
    }

    private fun setOnSignUpClickListener() {
        resetErrorsView()
        if (signUpTv?.text == getString(R.string.sign_up)) {
            setupRegisterForm()
        } else {
            setupLoginForm()
        }
    }

    private fun setupLoginForm() {
        signUpTv?.text = getString(R.string.sign_up)
        loginButton?.text = getString(R.string.login)
        confirmPasswordLayout?.visibility = View.GONE
    }

    private fun setupRegisterForm() {
        signUpTv?.text = getString(R.string.cancel)
        loginButton?.text = getString(R.string.register)
        confirmPasswordLayout?.visibility = View.VISIBLE
    }

    private fun setOnLoginClickListener() {
        resetErrorsView()
        val username = usernameInput?.text.toString()
        val password = passwordInput?.text.toString()
        val usersApi = RetrofitFactory.create(UserApiService::class.java)
        val user = User(-1, username, password)
        val result: Call<Result>
        if (loginButton?.text == getString(R.string.login)) {
            val correctType = LoginHelper.handleLoginData(username, password)
            if (correctType == LoginFormCorrectType.CORRECT) {
                signUpTv?.isClickable = false
                loginButton?.isClickable = false
                result = usersApi.login(user)
            } else {
                when (correctType) {
                    LoginFormCorrectType.INCORRECT_USERNAME -> incorrectUsername?.visibility =
                        View.VISIBLE
                    LoginFormCorrectType.INCORRECT_PASSWORD -> incorrectPassword?.visibility =
                        View.VISIBLE
                    else -> Log.d("login", "Unsupported section when try to login")
                }
                return
            }
        } else {
            val confirmPassword = confirmPasswordInput?.text.toString()
            val correctType = LoginHelper.handleRegisterData(username, password, confirmPassword)
            if (correctType == LoginFormCorrectType.CORRECT) {
                signUpTv?.isClickable = false
                loginButton?.isClickable = false
                result = usersApi.registerUser(user)
            } else {
                when (correctType) {
                    LoginFormCorrectType.INCORRECT_USERNAME -> incorrectUsername?.visibility =
                        View.VISIBLE
                    LoginFormCorrectType.INCORRECT_PASSWORD -> incorrectPassword?.visibility =
                        View.VISIBLE
                    LoginFormCorrectType.INCORRECT_CONFIRM_PASSWORD -> incorrectConfirmPassword?.visibility =
                        View.VISIBLE
                    else -> Log.d("register", "Unsupported section when try to register")
                }
                return
            }
        }
        doRequest(result, this)
    }

    private fun doRequest(result: Call<Result>, context: Context) {
        result.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(context, "something went wrong ${t.message}", Toast.LENGTH_LONG)
                    .show()
                resetErrorsView()
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                resetErrorsView()
                if (!response.isSuccessful) {
                    errorLogin?.visibility = View.VISIBLE
                    Toast.makeText(context, "code ${response.code()}", Toast.LENGTH_LONG).show()
                    return
                }

                val intent = Intent(context, PolyclinicMainToolbarActivity::class.java)
                intent.putExtra(
                    PolyclinicMainToolbarActivity.USERNAME_KEY,
                    usernameInput?.text.toString()
                )
                resetTextViews()
                setupLoginForm()

                startActivity(intent)
            }
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login;
    }

    private fun resetErrorsView() {
        incorrectUsername?.visibility = View.INVISIBLE
        incorrectPassword?.visibility = View.INVISIBLE
        incorrectConfirmPassword?.visibility = View.INVISIBLE
        errorLogin?.visibility = View.INVISIBLE
        signUpTv?.isClickable = true
        loginButton?.isClickable = true
    }

    private fun resetTextViews() {
        usernameInput?.text?.clear()
        passwordInput?.text?.clear()
        confirmPasswordInput?.text?.clear()
        usernameInput?.requestFocus()
    }
}


