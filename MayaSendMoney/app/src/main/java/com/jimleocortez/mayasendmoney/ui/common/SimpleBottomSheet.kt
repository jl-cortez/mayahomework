package com.jimleocortez.mayasendmoney.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jimleocortez.mayasendmoney.databinding.BottomSheetLayoutBinding

class SimpleBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetLayoutBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_MESSAGE = "arg_message"

        fun newInstance(message: String): SimpleBottomSheet {
            val fragment = SimpleBottomSheet()
            val args = Bundle()
            args.putString(ARG_MESSAGE, message)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = arguments?.getString(ARG_MESSAGE) ?: "Default message"
        binding.resultMessage.text = message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}