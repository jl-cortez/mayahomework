package com.jimleocortez.mayasendmoney.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimleocortez.mayasendmoney.databinding.FragmentTransactionHistoryBinding
import com.jimleocortez.mayasendmoney.network.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionHistoryFragment : Fragment() {

    private var _binding: FragmentTransactionHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var adapter: TransactionAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val transactionHistoryViewModel =
            ViewModelProvider(this).get(TransactionHistoryViewModel::class.java)

        val inflate = FragmentTransactionHistoryBinding.inflate(inflater, container, false)
        _binding = inflate
        val root: View = binding.root


        transactionHistoryViewModel.transactionHistoryResponse.observe(viewLifecycleOwner) {
            if(adapter == null) {
                adapter = TransactionAdapter(requireActivity(), transactionHistoryViewModel.transactionHistoryResponse.value.transactions)
            }
            binding.rvTransactions.layoutManager = LinearLayoutManager(requireActivity())
            binding.rvTransactions.adapter = adapter
        }

        lifecycleScope.launch(Dispatchers.IO) {
            transactionHistoryViewModel.getTransactionHistory(12345)
        }


//        val textView: TextView = binding.textNotifications
//        transactionHistoryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}