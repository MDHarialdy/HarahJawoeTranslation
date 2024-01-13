package com.saintek.mdh.harahjawoetranslation.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.saintek.mdh.harahjawoetranslation.R
import com.saintek.mdh.harahjawoetranslation.databinding.FragmentProfileBinding
import com.saintek.mdh.harahjawoetranslation.ui.ViewModelFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireContext()))[ProfileViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.getUser(1)
        profileViewModel.responseUser.observe(viewLifecycleOwner){
            binding.tvFullname.text = "Nama: ${it.name}"
            binding.tvAge.text = "Umur: ${it.age}"
            binding.tvCity.text = "Kota: ${it.city}"
        }
        binding.btnEdit.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.navigation_profile_edit)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}