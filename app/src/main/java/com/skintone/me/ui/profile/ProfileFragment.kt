package com.skintone.me.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.skintone.me.database.PreferenceManager
import com.skintone.me.database.dataStore
import com.skintone.me.databinding.FragmentProfileBinding
import com.skintone.me.login.LoginActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var preferenceManager: PreferenceManager

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
        preferenceManager = PreferenceManager.getInstance(requireContext().dataStore)

        lifecycleScope.launch {
            preferenceManager.getSession().first().let { user ->
                binding.tvNameProfile.text = user.name
                binding.tvEmailProfile.text = user.email
                binding.tvGenderProfile.text = user.gender
                binding.tvUsernameProfile.text = user.name
            }
            Log.d("Test", preferenceManager.getSession().first().toString())
        }

        binding.btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Make Sure if You Want to Logout")
            builder.setMessage("Want To Logout?")
            builder.setPositiveButton("Yes") { _, _ ->
                lifecycleScope.launch {
                    preferenceManager.logout()
                }

                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
                Toast.makeText(context, "Logout Succesfully", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
