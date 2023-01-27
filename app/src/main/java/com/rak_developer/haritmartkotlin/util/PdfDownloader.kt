package com.rak_developer.haritmartkotlin.util

import android.app.DownloadManager
import android.content.*
import android.net.Uri
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import java.io.File

object PdfDownloader {

    fun download(context: Context, fileURL: String?, title: String, flag: Int) {
        checkDirectory(context, fileURL, title, flag)
    }

    fun checkDirectory(context: Context, fileURL: String?, title: String, flag: Int) {
        try {
            var destination =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    .toString() + "/HaritMart/Pdf/"
            val fileName = "$title.pdf"
            destination += fileName
            val direct = File(destination)
            if (direct.exists()) {
                if (flag == Config.SHARE) {
                    sharePdf(context, destination)
                } else if (flag == Config.OPEN) {
                    openPdf(context, destination)
                }
            } else {
                startDownload(context, fileURL, title, destination, flag)
            }
        } catch (e: Exception) {
            Toast.showMessage(context, "Something went wrong, try again.")
        }
    }

    fun openPdf(context: Context, filepath: String?) {
        val pdfFile = File(filepath)
        val path = Uri.fromFile(pdfFile)
        val pdfIntent = Intent(Intent.ACTION_VIEW)
        pdfIntent.setDataAndType(path, "application/pdf")
        pdfIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        try {
            context.startActivity(pdfIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.showMessage(context, "No application available to view PDF")
        }
    }

    fun startDownload(
        context: Context,
        fileURL: String?,
        title: String?,
        destination: String,
        flag: Int
    ) {
        Toast.showMessage(context, "Downloading Started....")
        val uri = Uri.parse("file://$destination")
        val request = DownloadManager.Request(Uri.parse(fileURL))
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDescription("Downloading....")
        request.setTitle(title)
        request.setDestinationUri(uri)
        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadId = manager.enqueue(request)
        val onComplete: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(ctxt: Context, intent: Intent) {
                Toast.showMessage(context, "Downloading Completed...")
                context.unregisterReceiver(this)
                if (flag == Config.SHARE) {
                    sharePdf(context, destination)
                } else if (flag == Config.OPEN) {
                    openPdf(context, destination)
                }
            }
        }
        context.registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    fun sharePdf(context: Context, destination: String) {
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
}