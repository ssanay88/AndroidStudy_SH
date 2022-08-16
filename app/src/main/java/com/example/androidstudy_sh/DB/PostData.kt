package com.example.androidstudy_sh.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostData (
    @PrimaryKey val postId: Int?,
    @ColumnInfo val title: String?,
    @ColumnInfo val script: String?,
    @ColumnInfo val imgUrl: String?
        )