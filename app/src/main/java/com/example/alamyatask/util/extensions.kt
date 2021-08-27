package com.example.alamyatask.util
import android.annotation.SuppressLint
import android.content.Context
 import android.widget.Toast
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.ConnectivityManager
import com.example.alamyatask.R


fun Context.makeToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.makeToastList(msgs: List<String>) {
    for (msg in msgs) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

fun Context.launchActivity(activityClass: Class<*>) {
    startActivity(Intent(this, activityClass))
}

fun Activity.launchActivityFinishCurrent(activityClass: Class<*>) {
    startActivity(Intent(this, activityClass))
    finish()
}


fun Context.launchLoadingDialog(): Dialog {
    return Dialog(this, R.style.FullScreenDialog).apply {
        setContentView(R.layout.dialog_loading)
        show()
    }
}
@SuppressLint("MissingPermission")
fun Context.checkInternetConnectivity(): Boolean {
    val manager: ConnectivityManager? =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return manager?.activeNetworkInfo?.isConnected ?: false
}




/*
*
*  */