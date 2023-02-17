package com.example.baitaptet

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserNameProfile(val username: String, val password: String) : Parcelable {
}