package com.nammamistri.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nammamistri.databinding.FragmentCalculatorBinding
import kotlin.math.roundToInt

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalculatorBinding.inflate(
            inflater,
            container,
            false
        )

        setupThicknessSpinner()

        binding.btnCalculate.setOnClickListener {

            calculateMaterials()
        }

        return binding.root
    }

    private fun setupThicknessSpinner() {

        val thicknessOptions = arrayOf(
            "4.5 inch",
            "9 inch"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            thicknessOptions
        )

        binding.spinnerThickness.adapter = adapter
    }

    private fun calculateMaterials() {

        val lengthText =
            binding.etLength.text.toString().trim()

        val heightText =
            binding.etHeight.text.toString().trim()

        if (
            lengthText.isEmpty() ||
            heightText.isEmpty()
        ) {

            Toast.makeText(
                requireContext(),
                "Enter all fields",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        // INPUTS

        val lengthFeet = lengthText.toDouble()

        val heightFeet = heightText.toDouble()

        // THICKNESS

        val thicknessFeet = when (
            binding.spinnerThickness.selectedItem.toString()
        ) {

            "4.5 inch" -> 0.375

            else -> 0.75
        }

        // FEET → METERS

        val lengthMeter = lengthFeet * 0.3048

        val heightMeter = heightFeet * 0.3048

        val thicknessMeter =
            thicknessFeet * 0.3048

        // CONCRETE / WALL VOLUME

        val wetVolume =
            lengthMeter *
                    heightMeter *
                    thicknessMeter

        // DRY VOLUME

        val dryVolume =
            wetVolume * 1.54

        // BRICKS

        val brickSize =
            0.20 * 0.10 * 0.10

        val bricks =
            (wetVolume / brickSize).roundToInt()

        // CEMENT

        val cementRatio = 1.0

        val totalRatio = 6.0

        val cementBags =
            ((dryVolume * cementRatio)
                    / totalRatio) * 30

        // SAND

        val sand =
            dryVolume * 0.30

        // STEEL

        val steelKg =
            wetVolume * 120

        showResults(
            wetVolume,
            bricks,
            cementBags,
            sand,
            steelKg
        )
    }

    private fun showResults(
        volume: Double,
        bricks: Int,
        cement: Double,
        sand: Double,
        steel: Double
    ) {

        binding.tvBricks.text =
            "Bricks : $bricks"

        binding.tvCement.text =
            "Cement Bags : %.1f".format(cement)

        binding.tvSand.text =
            "Sand : %.2f m³".format(sand)

        binding.tvSteel.text =
            "Steel : %.1f kg".format(steel)

        binding.tvVolume.text =
            "Concrete Volume : %.2f m³".format(volume)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}