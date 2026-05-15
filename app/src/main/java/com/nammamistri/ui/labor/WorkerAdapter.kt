package com.nammamistri.ui.labor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nammamistri.data.model.Worker
import com.nammamistri.databinding.WorkerItemBinding

class WorkerAdapter(
    private val workers: List<Worker>
) : RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {

    inner class WorkerViewHolder(
        val binding: WorkerItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkerViewHolder {

        val binding = WorkerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return WorkerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: WorkerViewHolder,
        position: Int
    ) {

        val worker = workers[position]

        holder.binding.tvWorkerName.text =
            worker.name

        holder.binding.tvWorkerSalary.text =
            "Total Salary : ₹${worker.totalSalary()}"
    }

    override fun getItemCount(): Int {

        return workers.size
    }
}