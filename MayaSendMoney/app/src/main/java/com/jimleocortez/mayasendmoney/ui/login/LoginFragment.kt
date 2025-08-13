package com.jimleocortez.mayasendmoney.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.jimleocortez.mayasendmoney.MainActivity
import com.jimleocortez.mayasendmoney.R
import com.jimleocortez.mayasendmoney.databinding.FragmentLoginBinding
import com.jimleocortez.mayasendmoney.network.model.LoginDetails
import com.jimleocortez.mayasendmoney.ui.transactions.TransactionAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        with(viewModel) {
            loginDetails.observe(viewLifecycleOwner) {
                val loginDetails = loginDetails.value
                if (isValidLogin(loginDetails.username, loginDetails.password)) {
                    findNavController().navigate(R.id.action_login_to_mainActivity)
                } else {
                    Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.validateLogin( LoginDetails(username, password))
            }
        }
    }
}