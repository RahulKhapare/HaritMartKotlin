package com.rak_developer.haritmartkotlin.activity

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.ActivityUserProfileBinding
import com.rak_developer.haritmartkotlin.util.Click
import com.rak_developer.haritmartkotlin.util.Toast
import com.rak_developer.haritmartkotlin.util.Validation.validEmail
import com.rak_developer.haritmartkotlin.util.WindowBar
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException

class UserProfileActivity : AppCompatActivity() {

    val activity = this@UserProfileActivity;
    lateinit var binding: ActivityUserProfileBinding

    private val REQUEST_GALLARY = 9
    private val REQUEST_CAMERA = 10
    private val READ_WRIRE = 11
    private var cameraURI: Uri? = null
    private var click = 0
    private val cameraClick = 0
    private val galleryClick = 1
    private var base64Image = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);
        init()
    }

    fun init() {
        binding.toolbar.title = "User Profile"
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        onClick()
    }

    fun onClick() {
        binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
            } else {
            }
        }

        binding.btnProcess.setOnClickListener { v ->
            Click.preventTwoClick(v)
            if (checkValidation()) {
                hitSaveProfileApi()
            }
        }

        binding.lnrImage.setOnClickListener { v ->
            Click.preventTwoClick(v)
            onUploadDialog()
        }

    }

    private fun checkValidation(): Boolean {
        var value = true
        if (TextUtils.isEmpty(binding.etxName.text.toString().trim())) {
            Toast.showMessage(activity, "Please Enter Name");
            value = false
        } else if (TextUtils.isEmpty(binding.etxNumber.text.toString().trim())) {
            Toast.showMessage(activity, "Please Enter Mobile Number");
            value = false
        } else if (binding.etxNumber.text.toString().trim()
                .length > 10 || binding.etxNumber.text.toString().trim().length < 10
        ) {
            Toast.showMessage(activity, "Please Enter 10 Digit Mobile Number");
            value = false
        } else if (TextUtils.isEmpty(binding.etxEmail.text.toString().trim())) {
            Toast.showMessage(activity, "Please Enter Email Id");
            value = false
        } else if (!validEmail(binding.etxEmail.text.toString().trim())) {
            Toast.showMessage(activity, "Please Enter Valid Email Id");
            value = false
        }
        return value
    }


    fun hitSaveProfileApi() {
        onBackPressed()
    }

    fun onUploadDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.activity_upload_dialog)
        dialog.findViewById<View>(R.id.txtCamera).setOnClickListener { v ->
            Click.preventTwoClick(v)
            dialog.cancel()
            click = cameraClick
            getPermission()
        }
        dialog.findViewById<View>(R.id.txtGallary).setOnClickListener { v ->
            Click.preventTwoClick(v)
            dialog.cancel()
            click = galleryClick
            getPermission()
        }
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun getPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            READ_WRIRE
        )
    }

    private fun jumpToSetting() {
        Toast.showMessage(activity, "Please allow permission from setting");
        try {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            activity.startActivity(intent)
        } catch (e: Exception) {
        }
    }

    private fun openCamera() {
        try {
            val builder = VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
            val fileName = String.format("%d.jpg", System.currentTimeMillis())
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            )
            val file = File(path, fileName)

            cameraURI = Uri.fromFile(file)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraURI)
            startActivityForResult(intent, REQUEST_CAMERA)
        } catch (e: Exception) {
            Log.e("TAG", "openCameraError: " + e.message)
        }
    }

    private fun openGallery() {
        try {
            val i = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(i, REQUEST_GALLARY)
        } catch (e: java.lang.Exception) {
            Log.e("TAG", "openGalleryError: " + e.message)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            READ_WRIRE -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    if (click == cameraClick) {
                        openCamera()
                    } else if (click == galleryClick) {
                        openGallery()
                    }
                } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    jumpToSetting()
                } else {
                    getPermission()
                }
                return
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_GALLARY -> if (resultCode == RESULT_OK && data != null) {
                try {
                    val selectedImage = data.data
                    setImageData(selectedImage!!)
                } catch (e: java.lang.Exception) {
                }
            }
            REQUEST_CAMERA -> if (resultCode == RESULT_OK) {
                try {
                    setImageData(cameraURI!!)
                } catch (e: java.lang.Exception) {
                }
            }
        }
    }

    private fun setImageData(uri: Uri) {
        base64Image = ""
        try {
            val imageStream = contentResolver.openInputStream(uri)
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            base64Image = encodeImage(selectedImage)
            binding.imgUser.setImageURI(uri)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Log.e("TAG", "imageError: " + e.message)
            Toast.showMessage(activity, "Unable to get image, try again.")
        }
    }


    private fun encodeImage(bm: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return false
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}