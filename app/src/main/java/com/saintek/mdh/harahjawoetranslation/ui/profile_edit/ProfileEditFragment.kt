package com.saintek.mdh.harahjawoetranslation.ui.profile_edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saintek.mdh.harahjawoetranslation.databinding.FragmentProfileEditBinding
import com.saintek.mdh.harahjawoetranslation.ui.ViewModelFactory
import com.saintek.mdh.harahjawoetranslation.ui.util.CustomAgeEditText
import com.saintek.mdh.harahjawoetranslation.ui.util.CustomCityEditText
import com.saintek.mdh.harahjawoetranslation.ui.util.CustomNameEditText
import com.saintek.mdh.harahjawoetranslation.ui.util.showToast

class ProfileEditFragment : Fragment() {

    private var _binding : FragmentProfileEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FragmentEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext()))[FragmentEditViewModel::class.java]
        _binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)
        binding.ivPhotoProfile.setOnClickListener{
            //TODO
        }
        viewModel.getUser(1)
        viewModel.responseUser.observe(viewLifecycleOwner){
            binding.etFullname.setText("${it.name}")
            binding.etAge.setText("${it.age}")
            binding.etCity.setText("${it.city}")
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etFullname.text.toString()
            val age = binding.etAge.text.toString()
            val city = binding.etCity.text.toString()
            myButtonCheck(name, age, city)
        }
    }


    private fun myButtonCheck(name: String?, age: String, city: String?){
        if (name != null && city != null ) {
            viewModel.updateUser(name, age, city, 1)
            requireActivity().supportFragmentManager.popBackStack()
        } else {
        showToast(requireContext(), "Lengkapi data")
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}