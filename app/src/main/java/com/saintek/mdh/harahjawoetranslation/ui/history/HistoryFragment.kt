package com.saintek.mdh.harahjawoetranslation.ui.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.saintek.mdh.harahjawoetranslation.data.database.HistoryEntity
import com.saintek.mdh.harahjawoetranslation.databinding.FragmentHistoryBinding
import com.saintek.mdh.harahjawoetranslation.ui.HistoryAdapter
import com.saintek.mdh.harahjawoetranslation.ui.ViewModelFactory

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var historyViewModel: HistoryViewModel
    private var historyId = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireContext())
        )[HistoryViewModel::class.java]

        historyViewModel.getAllHistory(historyId).observe(viewLifecycleOwner) {
            setupListKamus(it)
            binding.tvCount.text = it.size.toString()
        }

        historyViewModel.getUser(1)
        historyViewModel.responseUser.observe(viewLifecycleOwner){
            if (it != null){
                binding.tvUsername.text = it.name
                Log.d(TAG,"User : $it")
            }
        }
    }

    private fun setupListKamus(history: List<HistoryEntity>) {
        val layoutManager =
            LinearLayoutManager(requireContext())
        binding.rvHistory.layoutManager = layoutManager
        val adapter = HistoryAdapter(history)
        binding.rvHistory.adapter = adapter
    }

    companion object {
        val TAG = "History Fragment"
    }
}