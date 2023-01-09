package com.santoso.androidroom.database.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@androidx.room.Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "desc")
    var desc: String,
): Parcelable