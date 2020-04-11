package com.example.aula8_mvvm

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


class Operation(val expresssion : String, val result: Double){

    var uuid: String = UUID.randomUUID().toString()
}