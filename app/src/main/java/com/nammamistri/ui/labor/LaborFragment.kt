package com.nammamistri.ui.labor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nammamistri.R
import com.nammamistri.data.model.Worker
import com.nammamistri.databinding.FragmentLaborBinding

class LaborFragment : Fragment(R.layout.fragment_labor) {

    private var _binding: FragmentLaborBinding? = null
    private val binding get() = _binding!!

    private val workerList = mutableListOf<Worker>()

    private lateinit var adapter: WorkerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLaborBinding.bind(view)

        adapter = WorkerAdapter(workerList)

        binding.recyclerWorkers.layoutManager =
            LinearLayoutManager(requireContext())

        binding.recyclerWorkers.adapter = adapter

        binding.btnAddWorker.setOnClickListener {

            val name =
                binding.etWorkerName.text.toString()

            val wage =
                binding.etDailyWage.text.toString().toIntOrNull() ?: 0

            val days =
                binding.etDaysWorked.text.toString().toIntOrNull() ?: 0

            val worker = Worker(name, wage, days)

            workerList.add(worker)

            adapter.notifyDataSetChanged()

            binding.etWorkerName.text?.clear()
            binding.etDailyWage.text?.clear()
            binding.etDaysWorked.text?.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}