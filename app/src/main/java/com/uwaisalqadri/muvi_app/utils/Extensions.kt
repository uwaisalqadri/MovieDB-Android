package com.uwaisalqadri.muvi_app.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.uwaisalqadri.muvi_app.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Uwais Alqadri on April 05, 2021
 */

fun Context.showToast(msg: String) {

    Toast.makeText(
        this,
        msg,
        Toast.LENGTH_SHORT
    ).show()

}

fun View.snackBar(msg: String) {

    Snackbar.make(
        this,
        msg,
        Snackbar.LENGTH_SHORT
    ).show()

}

fun ImageView.loadImage(url: String) {

    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.placeholder_image)
        .fallback(R.drawable.placeholder_image)
        .into(this)

}

fun getCurrentDate(format: String): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(Date().time)
}

fun Context.openLink(url: String) {
    val openLink = Intent(Intent.ACTION_VIEW)
    openLink.data = Uri.parse(url)
    this.startActivity(openLink)
}

fun String.formatDate() : String {
    return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O && this.isNotEmpty()) {
        val formatter = DateTimeFormatter.ofPattern(Constants.FORMAT_FROM_API)
        val currentDate = LocalDate.parse(this, formatter)
        currentDate.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT))
    } else {
        if (this.isEmpty()) "Unknown Date" else this
    }
}