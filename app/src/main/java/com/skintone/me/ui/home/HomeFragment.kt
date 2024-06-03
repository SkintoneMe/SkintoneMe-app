package com.skintone.me.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skintone.me.databinding.FragmentHomeBinding
import com.skintone.me.ui.camera.CameraActivity
import com.skintone.me.ui.readmore.ReadMoreActivity
import com.skintone.me.ui.readmore.ReadMoreActivity2
import com.skintone.me.ui.readmore.ReadMoreActivity3

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivFavorite.setOnClickListener {
            startActivity(Intent(requireContext(), FavoriteActivity::class.java))
        }

        binding.analyze.setOnClickListener {
            startActivity(Intent(requireContext(), CameraActivity::class.java))
        }

        binding.btnReadmore.setOnClickListener {
            startActivity(Intent(requireContext(), ReadMoreActivity::class.java))
        }

        binding.btnReadmore2.setOnClickListener {
            startActivity(Intent(requireContext(), ReadMoreActivity2::class.java))
        }

        binding.btnReadmore3.setOnClickListener {
            startActivity(Intent(requireContext(), ReadMoreActivity3::class.java))
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}