package com.uwaisalqadri.muvi_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	private val binding: ActivityMainBinding by viewBinding()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		binding.apply {
			navView.apply {
				setupWithNavController(findNavController(R.id.nav_host_fragment))
				itemIconTintList = null
			}
		}
	}
}