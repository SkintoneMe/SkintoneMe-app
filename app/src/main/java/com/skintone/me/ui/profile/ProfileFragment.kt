package com.skintone.me.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.skintone.me.databinding.FragmentProfileBinding
import com.skintone.me.login.LoginActivity
import com.skintone.me.login.RegisterActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            Toast.makeText(context, "Logout Succsesfuly", Toast.LENGTH_SHORT).show()
        }

        binding.tvDeleteAccount.setOnClickListener {
            startActivity(Intent(requireContext(), RegisterActivity::class.java))
            Toast.makeText(context, "Delete Account Succsesfuly", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}