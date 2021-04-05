package com.uwaisalqadri.muvi_app.utils

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.uwaisalqadri.muvi_app.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

fun String.formatDate() : String {
    return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O && this.isNotEmpty()) {
        val formatter = DateTimeFormatter.ofPattern(Constants.FORMAT_FROM_API)
        val currentDate = LocalDate.parse(this, formatter)
        currentDate.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT))
    } else {
        if (this.isEmpty()) "Unknown Date" else this
    }
}