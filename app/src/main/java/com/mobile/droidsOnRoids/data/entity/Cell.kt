package com.mobile.droidsOnRoids.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cell(@PrimaryKey(autoGenerate = true) val id: Long? = null, val row: Int, val column: Int, var value: Int, var isEditable: Boolean)