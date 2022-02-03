package com.appsirise.trans4fans.auth.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.appsirise.core.ui.base.BaseFragment
import com.appsirise.core.ui.utils.ViewState
import com.appsirise.trans4fans.auth.ui.AuthViewModel
import com.appsirise.trans4fans.auth.ui.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignupBinding>() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        authViewModel.getBreeds()
    }

    private fun initObservers() {
        authViewModel.getBreedsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Error -> Toast.makeText(
                    requireContext(),
                    it.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
                is ViewState.Success -> binding.listItems.text = it.toString()
            }
        }
    }

    override fun setBinding() = FragmentSignupBinding.inflate(layoutInflater)
}
