package com.my.testproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.testproject.R
import com.my.testproject.viewmodel.LoginViewModel
import com.s.testproject.datamodel.base.Status
import androidx.lifecycle.Observer
import com.my.testproject.core.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener {
            initObserve()
        }

    }

    private fun initObserve() {
        loginViewModel.fetchLogin(et_username.text.toString(), et_password.text.toString())
        loginViewModel.login()?.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    toast("Ok")
                    startActivity(Intent(this, MainActivity::class.java))
                }
                Status.LOADING -> {
                    //Show ProgressBar
                }
                Status.ERROR -> {
                    toast("Error")
                    //Handle Error
                }
            }
        })
    }
}