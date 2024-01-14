package com.saintek.mdh.harahjawoetranslation.ui.scanner

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.OrientationEventListener
import android.view.Surface
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.saintek.mdh.harahjawoetranslation.R
import com.saintek.mdh.harahjawoetranslation.databinding.ActivityScannerBinding
import com.saintek.mdh.harahjawoetranslation.ui.util.createCustomTempFile
import com.saintek.mdh.harahjawoetranslation.ui.util.showToast

class ScannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScannerBinding
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    private var currentImageURI: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGallery.setOnClickListener { startGallery() }
        binding.cameraButton.setOnClickListener { takePhoto() }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
        startCamera()
    }

    override fun onStart() {
        super.onStart()
        orientationEventListener.enable()
    }
    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private val orientationEventListener by lazy {
        object : OrientationEventListener(this) {
            override fun onOrientationChanged(orientation: Int) {
                if (orientation == ORIENTATION_UNKNOWN) {
                    return
                }

                val rotation = when (orientation) {
                    in 45 until 135 -> Surface.ROTATION_270
                    in 135 until 225 -> Surface.ROTATION_180
                    in 225 until 315 -> Surface.ROTATION_90
                    else -> Surface.ROTATION_0
                }
                imageCapture?.targetRotation = rotation
            }
        }
    }

    private fun startCamera(){
        val cameraProvideFuture = ProcessCameraProvider.getInstance(this)

        cameraProvideFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProvideFuture.get()
                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                    }

                imageCapture = ImageCapture.Builder().build()

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        this,
                        cameraSelector,
                        preview,
                        imageCapture
                    )
                } catch (e: Exception) {
                    showToast(this, e.message.toString())
                    Log.d(TAG, e.message.toString())
                }
            }, ContextCompat.getMainExecutor(this)
        )
    }

    private fun takePhoto(){
        val imageCapture = imageCapture ?: return
        val photoFile = createCustomTempFile(application)
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object :ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    currentImageURI  = outputFileResults.savedUri
                    if (currentImageURI != null){
                        val intent = Intent(this@ScannerActivity, ScannerResultActivity::class.java)
                        intent.putExtra(EXTRA_CAMERA_IMAGE, currentImageURI.toString())
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    showToast(this@ScannerActivity, getString(R.string.fail_to_take_picture))
                    Log.e(TAG, "onError: ${exception.message}")
                }
            }
        )
    }

    private fun startGallery(){
        launchGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launchGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ){uri ->
        if (uri != null){
            currentImageURI = uri
            val intent = Intent(this@ScannerActivity, ScannerResultActivity::class.java)
            intent.putExtra(EXTRA_GALLERY_IMAGE, currentImageURI.toString())
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } else {
            Log.d(TAG, "Image Picker Null: $uri")
        }
    }

    companion object {
        const val TAG = "ScannerActivity"
        const val EXTRA_CAMERA_IMAGE = "CameraImage"
        const val EXTRA_GALLERY_IMAGE = "GalleryImage"
        const val EXTRA_RESULT_SCANNER = "ResultScanner"
    }
}