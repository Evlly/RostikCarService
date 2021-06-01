package com.example.rostik

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel



class InfoViewModel : ViewModel() {


    fun call(context: Context){
        val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1122334455"))

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        context.startActivity(callIntent)
    }
}