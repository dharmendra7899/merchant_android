package com.example.vendor_android.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.vendor_android.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class AddProductActivity : AppCompatActivity() {

    private lateinit var btnSelectImage: ImageView
    private lateinit var imageView: ImageView

    private val CAMERA_REQUEST_CODE = 100
    private val GALLERY_REQUEST_CODE = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        val back = findViewById<ImageView>(R.id.back_btn_add)
        val spinner: Spinner = findViewById(R.id.category_spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.category_items,
            R.layout.spinner_layout
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0, false)
        spinner.prompt = "Select Category"

        btnSelectImage = findViewById(R.id.uploadImage)
        imageView = findViewById(R.id.product_imageView)

        btnSelectImage.setOnClickListener {
            showBottomSheetDialog()
        }
        back.setOnClickListener(
            View.OnClickListener {
                finish()
            }
        )
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.camera_bottomsheet_dialog, null)

        bottomSheetView.findViewById<TextView>(R.id.tv_camera).setOnClickListener {
            bottomSheetDialog.dismiss()
            if (checkPermission()) {
                openCamera()
            } else {
                requestPermission()
            }
        }

        bottomSheetView.findViewById<TextView>(R.id.tv_gallery).setOnClickListener {
            bottomSheetDialog.dismiss()
            if (checkPermission()) {
                openGallery()
            } else {
                requestPermission()
            }
        }

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    // Check for camera and storage permissions
    private fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
    }

    // Request the necessary permissions
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE),
            CAMERA_REQUEST_CODE
        )
    }

    // Open Camera
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    // Open Gallery
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    // Handle the result from Camera or Gallery
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imageView.setImageBitmap(imageBitmap)
                }

                GALLERY_REQUEST_CODE -> {
                    val imageUri: Uri? = data?.data
                    imageView.setImageURI(imageUri)
                }
            }
        }
    }

    // Handle permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            }
        }
    }
}