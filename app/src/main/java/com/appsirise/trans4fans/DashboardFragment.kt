package com.appsirise.trans4fans

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.appsirise.core.ui.base.BaseFragment
import com.appsirise.trans4fans.databinding.FragmentDashboardBinding

class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToSignup()
    }

    private fun navigateToSignup() {
        findNavController().navigate(R.id.action_dashboardFragment_to_nav_graph_auth)
    }

    override fun setBinding() = FragmentDashboardBinding.inflate(layoutInflater)
}
