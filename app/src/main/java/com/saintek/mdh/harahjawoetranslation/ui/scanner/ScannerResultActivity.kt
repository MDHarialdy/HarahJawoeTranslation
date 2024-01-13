package com.saintek.mdh.harahjawoetranslation.ui.scanner

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.saintek.mdh.harahjawoetranslation.databinding.ActivityScannerResultBinding
import com.saintek.mdh.harahjawoetranslation.ui.ViewModelFactory
import com.saintek.mdh.harahjawoetranslation.ui.util.getRotatedBitmap
import com.saintek.mdh.harahjawoetranslation.ui.util.showToast
import java.io.File

class ScannerResultActivity : AppCompatActivity() {

    private var _binding: ActivityScannerResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var scannerViewModel: ScannerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityScannerResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Hasil Scan"

        val viewModelFactory = ViewModelFactory.getInstance(this)
        scannerViewModel = ViewModelProvider(this, viewModelFactory)[ScannerViewModel::class.java]

        var imageUrlCamera = intent.getStringExtra(ScannerActivity.EXTRA_CAMERA_IMAGE)?.toUri()
        var imageUrlGallery = intent.getStringExtra(ScannerActivity.EXTRA_GALLERY_IMAGE)?.toUri()
        val scanResult = intent.getStringExtra(ScannerActivity.EXTRA_RESULT_SCANNER) ?: "Apapun"

        if (imageUrlCamera != null) {
            val bitmap = BitmapFactory.decodeFile(imageUrlCamera.path)
            val rotatedBitmap = bitmap.getRotatedBitmap(File(imageUrlCamera.path.toString()))
            binding.ivPhotoResultScan.setImageBitmap(rotatedBitmap)
            binding.ivPhotoResultScan.scaleType = ImageView.ScaleType.CENTER_CROP
            scannerViewModel.insertHistory(imageUrlCamera, scanResult, contentResolver)

        } else if (imageUrlGallery != null) {
            binding.ivPhotoResultScan.setImageURI(imageUrlGallery)
            binding.ivPhotoResultScan.scaleType = ImageView.ScaleType.CENTER_CROP
            scannerViewModel.insertHistory(imageUrlGallery, scanResult, contentResolver)

        } else {
            showToast(this, "Gambar Tidak Ada")
        }

        binding.btnDeteksiUlang.setOnClickListener {
            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
            _binding = null
            finish()
        }

        if (scanResult != null) {
            binding.tvScanResult.text = scanResult.toString()
        } else {
            showToast(this, "Scan Gagal")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }
}

