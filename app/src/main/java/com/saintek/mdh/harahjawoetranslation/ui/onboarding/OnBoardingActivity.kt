package com.saintek.mdh.harahjawoetranslation.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.saintek.mdh.harahjawoetranslation.data.database.UserEntity
import com.saintek.mdh.harahjawoetranslation.databinding.ActivityOnBoardingBinding
import com.saintek.mdh.harahjawoetranslation.ui.MainActivity
import com.saintek.mdh.harahjawoetranslation.ui.ViewModelFactory

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var onBoardingViewModel: OnBoardingViewModel
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBoardingViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this))[OnBoardingViewModel::class.java]

        binding.buttonMulai.setOnClickListener {
            val user = UserEntity(1, "MDH210705112", 1, "Banda Aceh")
            onBoardingViewModel.insertUserFirstTime(user)
            onBoardingViewModel.setFirstTimeLaunchToFalse()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}