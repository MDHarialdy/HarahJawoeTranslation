package com.saintek.mdh.harahjawoetranslation.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.saintek.mdh.harahjawoetranslation.R
import com.saintek.mdh.harahjawoetranslation.databinding.ActivityMainBinding
import com.saintek.mdh.harahjawoetranslation.ui.onboarding.OnBoardingActivity
import com.saintek.mdh.harahjawoetranslation.ui.scanner.ScannerActivity
import com.saintek.mdh.harahjawoetranslation.ui.util.showToast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[MainViewModel::class.java]

        checkFirstTime()

        binding.cameraButton.setOnClickListener{
            if (allPermissionGranted()) {
                val intent = Intent(this, ScannerActivity::class.java)
                startActivity(intent)
            } else {
                requestCameraPermission.launch(CAMERA_PERMISSION)
            }
        }
    }

    private fun allPermissionGranted() =
        ContextCompat.checkSelfPermission(this, CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED

    private val requestCameraPermission =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){granted ->
            if (granted) {
                showToast(this, "Izin Diberikan")
                val intent = Intent(this, ScannerActivity::class.java)
                startActivity(intent)
            } else {
                showToast(this, "Berikan Izin Kamera Terlebih Dahulu")
            }
        }

    private fun checkFirstTime(){
        if (!mainViewModel.checkIsFirstTimeLaunch()){
            binding.container.visibility = View.VISIBLE

            val bottomNavView: BottomNavigationView = binding.bottomNavView
            val navController = findNavController(R.id.nav_host_fragment_activity_home)
            AppBarConfiguration.Builder(
                R.id.navigation_history,
                R.id.navigation_profile
            ).build()
            bottomNavView.setupWithNavController(navController)
        } else {
            binding.root.visibility = View.GONE
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val CAMERA_PERMISSION = Manifest.permission.CAMERA
    }
}