package com.example.androidstudy_sh.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidstudy_sh.DB.PostData
import com.example.androidstudy_sh.itemData

@Dao
interface DAO {

    @Insert
    fun insertPost(postData: PostData)

    @Query("SELECT * FROM PostData")
    fun getAll() : List<PostData>


}