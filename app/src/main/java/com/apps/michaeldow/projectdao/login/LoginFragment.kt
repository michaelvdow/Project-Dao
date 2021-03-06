package com.apps.michaeldow.projectdao.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.apps.michaeldow.projectdao.R
import com.apps.michaeldow.projectdao.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_signup.*
import android.view.WindowManager
//import android.R



class LoginFragment: Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel


        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.isSignedIn.observe(this, Observer { isSignedIn ->
            if (isSignedIn) {
                Toast.makeText(context, "Logged in", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_chatFragment2)
            }
        })

        viewModel.forgotPass.observe(this, Observer { forgotPass ->
            if (forgotPass) {
                viewModel.forgotPass.value = false
                findNavController().navigate(R.id.action_loginFragment_to_forgotPassFragment)
            }
        })

        viewModel.viewPass.observe(this, Observer { viewPass ->
            if (viewPass) {
                passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            else{
                passwordInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
        })

        viewModel.signInFailed.observe(this, Observer { signInFailed ->
            Toast.makeText(context, "Log in failed", Toast.LENGTH_SHORT).show()
        })

    }

}