package com.mmcs.todolist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoModel internal constructor(
    val id: String = "00000000-0000-0000-0000-000000000000",
    val title: String = "",
    val description: String = "",
    val checked: Boolean = false
) : Parcelable