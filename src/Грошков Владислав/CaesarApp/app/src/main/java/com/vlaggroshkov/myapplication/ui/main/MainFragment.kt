package com.vlaggroshkov.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vlaggroshkov.myapplication.databinding.MainFragmentBinding
import kotlin.collections.ArrayList


private lateinit var binding: MainFragmentBinding


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.ySwitch.isChecked = true
        binding.eSwitch.isChecked = true

        binding.encryptButton.setOnClickListener { encryptOrDecrypt(true) }

        binding.decryptButton.setOnClickListener { encryptOrDecrypt(false) }

        binding.languageSwitch.setOnClickListener {
            if (binding.languageSwitch.isChecked) {
                binding.ySwitch.visibility = View.GONE
                binding.eSwitch.visibility = View.GONE
            } else {
                binding.ySwitch.visibility = View.VISIBLE
                binding.eSwitch.visibility = View.VISIBLE
            }
        }
        return view
    }

    private fun encryptOrDecrypt(isEncrypt: Boolean) {
        val isYON = binding.ySwitch.isChecked
        val isEON = binding.eSwitch.isChecked
        val inputString = binding.inputEditText.text.toString()
        val inputStep = binding.stepEditText.text.toString()
        if (inputString.isNotEmpty() && inputStep.isNotEmpty()) {
            var encryptedText = "" ;
            encryptedText = if (isEncrypt) {
                viewModel.encryptString(inputString.split("") as ArrayList<String>, binding.languageSwitch.isChecked, inputStep.toInt(), isYON, isEON)
            } else {
                viewModel.decryptString(inputString.split("") as ArrayList<String>, binding.languageSwitch.isChecked, inputStep.toInt(), isYON, isEON)
            }
            binding.resultTextView.text = encryptedText
        } else {
            Toast.makeText(requireActivity(), "Не все входные данные введены...", Toast.LENGTH_LONG).show()
        }
    }

}
