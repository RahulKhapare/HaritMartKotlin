package com.rak_developer.haritmartkotlin.activity

import android.Manifest
import android.app.ActionBar
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.adapter.OrderDetailsAdapter
import com.rak_developer.haritmartkotlin.databinding.ActivityOrderDetailsBinding
import com.rak_developer.haritmartkotlin.model.MyOrderModel
import com.rak_developer.haritmartkotlin.util.*
import java.io.File

class TrackOrderActivity : AppCompatActivity(), View.OnClickListener {

    val activity = this@TrackOrderActivity;
    lateinit var binding: ActivityOrderDetailsBinding
    private val rs = "₹ "
    private var userRating: String? = null
    private var clickInvoice = 0
    private val shareInvoice = 1
    private val viewInvoice = 2
    private val pdf_url = "https://www.scribd.com/document/468418164/dummy-1-pdf"
    private val READ_WRITE = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_details);
        init()
    }

    fun init() {
        binding.toolbar.title = "Order Details"
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        getAccess()
        setOrderData()
        setOrderStatus("Packed")
        setAddressData()
        setSubTotalData()


        binding.imgStar1.setOnClickListener(this)
        binding.imgStar2.setOnClickListener(this)
        binding.imgStar3.setOnClickListener(this)
        binding.imgStar4.setOnClickListener(this)
        binding.imgStar5.setOnClickListener(this)
        binding.btnShareInvoice.setOnClickListener(this)
        binding.btnDownloadInvoice.setOnClickListener(this)
        binding.btnCancelOrder.setOnClickListener(this)

        binding.btnCancelOrder.setOnClickListener {
            commentDialog()
        }
    }

    fun setOrderData() {
        val orderModelList = ArrayList<MyOrderModel>()
        orderModelList.add(MyOrderModel())
        val adapter = OrderDetailsAdapter(activity, orderModelList)
        binding!!.recyclerOrderProduct.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding!!.recyclerOrderProduct.setHasFixedSize(true)
        binding!!.recyclerOrderProduct.adapter = adapter
    }

    private fun setAddressData() {
        binding.txtAddressName.text = "Billing & Shipping Address"
        binding.txtTitle.setText("Home Address")
        binding.txtName.setText("Rahul Khapare")
        binding.txtAddress.setText(
            "Contact - 0000000000\nEmail - rahul@gmail.com\n" +
                    "Address - Something,Near Road,India\nCity-20377"
        )
    }

    fun setSubTotalData() {
        binding!!.txtSubTotal.text = rs + " 42"
        binding!!.txtTaxCharge.text = rs + " 12"
        binding!!.txtDeliverCharge.text = rs + " 10"
        binding!!.txtCouponDiscount.text = rs + " 20"
        binding!!.txtTotalAMount.text = rs + " 64"
    }


    private fun commentDialog(
    ) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.activity_comment_dialog)
        val etxComment = dialog.findViewById<EditText>(R.id.etxComment)
        val txtCancel = dialog.findViewById<TextView>(R.id.txtCancel)
        val txtSubmit = dialog.findViewById<TextView>(R.id.txtSubmit)
        txtSubmit.setOnClickListener { v ->
            Click.preventTwoClick(v)
            val comment = etxComment.text.toString().trim { it <= ' ' }
            if (!TextUtils.isEmpty(comment)) {
                if (comment.length > 10) {
                    dialog.cancel()
                } else {
                    Toast.showMessage(activity, "Enter minimum 10 character")
                }
            } else {
                Toast.showMessage(activity, "Please enter comment")
            }
        }
        txtCancel.setOnClickListener { v ->
            Click.preventTwoClick(v)
            dialog.cancel()
        }
        dialog.setCancelable(true)
        dialog.show()
        val window = dialog.window
        window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
    }

    private fun updateRating(value: Int) {
        if (value == 1) {
            userRating = "1"
            binding.imgStar1.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar2.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_24))
            binding.imgStar3.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_24))
            binding.imgStar4.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_24))
            binding.imgStar5.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_24))
        } else if (value == 2) {
            userRating = "2"
            binding.imgStar1.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar2.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar3.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_24))
            binding.imgStar4.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_24))
            binding.imgStar5.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_24))
        } else if (value == 3) {
            userRating = "3"
            binding.imgStar1.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar2.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar3.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar4.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_24))
            binding.imgStar5.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_24))
        } else if (value == 4) {
            userRating = "4"
            binding.imgStar1.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar2.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar3.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar4.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar5.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_24))
        } else if (value == 5) {
            userRating = "5"
            binding.imgStar1.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar2.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar3.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar4.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
            binding.imgStar5.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_star_green_24))
        }
    }

    private fun setOrderStatus(status: String) {
        val alpha = 0.3f
        if (status.contains("Pending") || status.contains("Processing") || status.contains("Received")) {
            binding.lnrStatus2.alpha = alpha
            binding.lnrStatus3.alpha = alpha
            binding.lnrStatus4.alpha = alpha
        } else if (status.contains("Packed")) {
            binding.lnrStatus3.alpha = alpha
            binding.lnrStatus4.alpha = alpha
        } else if (status.contains("Shipped") || status.contains("On Hold")) {
            binding.lnrStatus4.alpha = alpha
        } else if (status.contains("Delivered")) {
        }
    }

    fun checkInvoice() {
        if (TextUtils.isEmpty(pdf_url) || pdf_url == "null") {
            Toast.showMessage(activity, "No pdf path found")
        } else {
            getPermission()
        }
    }

    private fun getAccess() {
        try {
            val builder = VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
        } catch (e: Exception) {
        }
    }

    private fun getPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            READ_WRITE
        )
    }

    fun jumpToSetting() {
        Toast.showMessage(activity, "Please allow permission from setting.")
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        activity.startActivity(intent)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            READ_WRITE -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    checkDirectory(activity, pdf_url, "010101")
                } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    jumpToSetting()
                } else {
                    getPermission()
                }
                return
            }
        }
    }

    private fun checkDirectory(context: Context, fileURL: String, title: String) {
        try {
            var destination =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    .toString() + "/Grocery/Pdf/"
            val fileName = "$title.pdf"
            destination += fileName
            val direct = File(destination)
            if (direct.exists()) {
                if (clickInvoice == shareInvoice) {
                    sharePdf(context, destination)
                } else if (clickInvoice == viewInvoice) {
                    openPdf(context, destination)
                }
            } else {
                if (clickInvoice == shareInvoice) {
                    PdfDownloader.download(activity, fileURL, title, Config.SHARE)
                } else if (clickInvoice == viewInvoice) {
                    PdfDownloader.download(activity, fileURL, title, Config.OPEN)
                }
            }
        } catch (e: java.lang.Exception) {
            Log.e("TAG", "checkDirectory: " + e.message)
            Toast.showMessage(activity, "Something went wrong, try again.")
        }
    }


    private fun openPdf(context: Context, filepath: String) {
        val pdfFile = File(filepath)
        val path = Uri.fromFile(pdfFile)
        val pdfIntent = Intent(Intent.ACTION_VIEW)
        pdfIntent.setDataAndType(path, "application/pdf")
        pdfIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        try {
            context.startActivity(pdfIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.showMessage(activity, "No application available to view PDF")
        }
    }

    private fun sharePdf(context: Context, destination: String) {
        val intentShareFile = Intent(Intent.ACTION_SEND)
        val fileWithinMyDir = File(destination)
        if (fileWithinMyDir.exists()) {
            val builder = VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
            intentShareFile.type = "application/pdf"
            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://$destination"))
            intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Sharing File...")
            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...")
            context.startActivity(Intent.createChooser(intentShareFile, "Share File"))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return false
    }


    override fun onClick(v: View) {
        Click.preventTwoClick(v)
        when (v.id) {
            R.id.imgStar1 -> {
                updateRating(1)
            }
            R.id.imgStar2 -> {
                updateRating(2)
            }
            R.id.imgStar3 -> {
                updateRating(3)
            }
            R.id.imgStar4 -> {
                updateRating(4)
            }
            R.id.imgStar5 -> {
                updateRating(5)
            }
            R.id.btnShareInvoice -> {
                clickInvoice = shareInvoice
                checkInvoice()
            }
            R.id.btnDownloadInvoice -> {
                clickInvoice = viewInvoice
                checkInvoice()
            }
            R.id.btnCancelOrder -> {
                commentDialog()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}