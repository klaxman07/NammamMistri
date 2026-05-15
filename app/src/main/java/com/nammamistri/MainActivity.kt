package com.nammamistri

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nammamistri.databinding.ActivityMainBinding
import com.nammamistri.ui.ai.AiFragment
import com.nammamistri.ui.calculator.CalculatorFragment
import com.nammamistri.ui.labor.LaborFragment
import com.nammamistri.ui.photos.PhotosFragment
import com.nammamistri.ui.dashboard.DashboardFragment
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = getColor(R.color.navDark)
        window.statusBarColor = getColor(R.color.navDark)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Open Dashboard First
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DashboardFragment())
            .commit()

        // Bottom Navigation
        binding.bottomNav.setOnItemSelectedListener {

            val fragment = when (it.itemId) {

                R.id.nav_dashboard -> DashboardFragment()

                R.id.nav_calc -> CalculatorFragment()

                R.id.nav_labor -> LaborFragment()

                R.id.nav_photos -> PhotosFragment()

                R.id.nav_ai -> AiFragment()

                else -> DashboardFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()

            true
        }
    }
}