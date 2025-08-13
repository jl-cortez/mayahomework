package com.jimleocortez.mayasendmoney.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.jimleocortez.mayasendmoney.MainActivity
import com.jimleocortez.mayasendmoney.R
import com.jimleocortez.mayasendmoney.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mViewModel: HomeViewModel
        get() {
            val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
            return homeViewModel
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        val homeViewModel =
            mViewModel

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome

//        (requireActivity() as MainActivity).findViewById<NavigationView>(R.id.nav_view).visibility = View.VISIBLE

        homeViewModel.accountDetails.observe(viewLifecycleOwner) {
            binding.amountTv.text = homeViewModel.formatAmount(requireActivity(), homeViewModel.accountDetails.value.accountBalance)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            mViewModel.getAccountDetails(12345)
        }
        binding.sendMoneyBtn.setOnClickListener(this)
        binding.transactionHistoryBtn.setOnClickListener(this)
        binding.showHideBtn.setOnClickListener(this)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.send_money_btn -> {
                val action = HomeFragmentDirections.homeToSendMoney(mViewModel.accountDetails.value)
                findNavController().navigate(action)
            }
            R.id.transaction_history_btn -> {
                findNavController().navigate(R.id.navigation_transaction_history)
            }
            R.id.show_hide_btn -> {
                binding.amountTv.text = when(binding.showHideBtn.isChecked) {
                    true -> {
                        mViewModel.maskString(binding.amountTv.text.toString())
                    }
                    false -> {
                        mViewModel.formatAmount(requireActivity(), mViewModel.accountDetails.value.accountBalance)
                    }
                }
            }
        }
    }
}