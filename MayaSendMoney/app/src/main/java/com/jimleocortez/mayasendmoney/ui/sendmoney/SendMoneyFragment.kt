package com.jimleocortez.mayasendmoney.ui.sendmoney

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimleocortez.mayasendmoney.R
import com.jimleocortez.mayasendmoney.databinding.FragmentSendMoneyBinding
import com.jimleocortez.mayasendmoney.network.model.LoginDetails
import com.jimleocortez.mayasendmoney.network.model.SendMoneyDetails
import com.jimleocortez.mayasendmoney.ui.common.SimpleBottomSheet
import com.jimleocortez.mayasendmoney.ui.transactions.TransactionAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class SendMoneyFragment: Fragment(), View.OnClickListener {

    private var _binding: FragmentSendMoneyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var sendMoneyViewModel: SendMoneyViewModel? = null

    private val mViewModel get() = sendMoneyViewModel!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sendMoneyViewModel =
            ViewModelProvider(this).get(SendMoneyViewModel::class.java)

        _binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        binding.amountEt.setOnClickListener(this)

        val args : SendMoneyFragmentArgs by navArgs()
        mViewModel.accountDetails.value = args.accountDetails

        binding.sendMoneyBtn.setOnClickListener(this)

        val root: View = binding.root

        mViewModel.sendMoneyDetails.observe(viewLifecycleOwner) {
            val msgId = if(mViewModel.validateSendMoneyAmount(mViewModel.sendMoneyDetails.value.amount)) {
                R.string.send_money_success
            } else {
                R.string.send_money_failed
            }
            val bottomSheet = SimpleBottomSheet.newInstance(getString(msgId))
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.send_money_btn -> {
                lifecycleScope.launch(Dispatchers.IO) {
                    with(mViewModel) {
                        val amount = binding.amountEt.text.toString()
                        if(amount.isNotEmpty()) {
                            sendMoneyTransaction(
                                SendMoneyDetails(
                                    amount.toDouble(),
                                    UUID.randomUUID().toString(),
                                    accountDetails.value.accountId
                                )
                            )
                        } else {
                            val bottomSheet = SimpleBottomSheet.newInstance(getString(R.string.send_money_amount_empty))
                            bottomSheet.show(childFragmentManager, bottomSheet.tag)
                        }
                    }
                }
            }
        }
    }
}