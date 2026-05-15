package com.nammamistri.ui.ai

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nammamistri.R
import com.nammamistri.databinding.FragmentAiBinding

class AiFragment : Fragment(R.layout.fragment_ai) {

    private var _binding: FragmentAiBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAiBinding.bind(view)

        binding.btnAskAI.setOnClickListener {

            val question =
                binding.etQuestion.text.toString().lowercase()

            val response = when {

                question.contains("cement") -> {
                    "Use high-quality cement and store it in dry areas."
                }

                question.contains("sand") -> {
                    "River sand is commonly used for strong construction."
                }

                question.contains("brick") -> {
                    "Clay bricks are durable and cost-effective."
                }

                question.contains("cost") -> {
                    "Reduce costs by buying materials in bulk."
                }

                question.contains("paint") -> {
                    "Use weather-resistant paint for longer durability."
                }

                else -> {
                    "Please ask a construction-related question."
                }
            }

            binding.tvAIResponse.text = response
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}