package com.example.androidstudy_sh

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.androidstudy_sh.DB.PostDB
import com.example.androidstudy_sh.DB.PostData
import com.example.androidstudy_sh.databinding.ActivityMainBinding
import com.example.androidstudy_sh.databinding.MessageItemBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ItemBinding: MessageItemBinding

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    // Firebase 접속
    private val db = FirebaseFirestore.getInstance()
    // Room
    private lateinit var postDB: PostDB

    private var itemDatas = mutableListOf<itemData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        ItemBinding = MessageItemBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // RoomDB 생성
        // postDB = Room.databaseBuilder(this, PostDB::class.java, "PostDB").allowMainThreadQueries().build()

        getPost()
        // getRecyclerView()

        recyclerViewAdapter = RecyclerViewAdapter(ItemBinding, itemDatas)
        binding.messageBoardRv.adapter = recyclerViewAdapter
        binding.messageBoardRv.layoutManager = LinearLayoutManager(this)
    }

    // 한번만 가져오기
    private fun getPost() {
        db.collection("post").document("lists")
            .get()
            .addOnSuccessListener { result ->

                var imgUrl = result.get("ImgUrl").toString()
                var title = result.get("Title").toString()
                var script = result.get("Script").toString()

                if (itemData(imgUrl, title, script) in itemDatas) {
                } else {
                    itemDatas.apply {
                        add(itemData(imgUrl, title, script))
                        binding.messageBoardRv.adapter = RecyclerViewAdapter(ItemBinding, itemDatas)
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.d("로그", "데이터 받아오기 실패 : $e")
            }
    }

    // 리사이클러뷰 갱신
    private fun getRecyclerView() {

        itemDatas.clear()

        for (postData in postDB.getDao().getAll()) {
            itemDatas.add(itemData(postData.imgUrl, postData.title, postData.script))
        }
        binding.messageBoardRv.adapter = RecyclerViewAdapter(ItemBinding, itemDatas)
    }

    // 실시간 업데이트
    private fun getSnapShot() {
        db.collection("post").document("lists")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.e("로그", "업데이트 실패 : $e")
                }

                if (snapshot != null && snapshot.exists()) {
                    var imgUrl = snapshot.get("ImgUrl").toString()
                    var title = snapshot.get("Title").toString()
                    var script = snapshot.get("Script").toString()
                    itemDatas.add(itemData(imgUrl, title, script))
                    binding.messageBoardRv.adapter = RecyclerViewAdapter(ItemBinding, itemDatas)

                }
            }
    }


    private fun savePost(newItem: itemData) {
        val newId = SimpleDateFormat("HHmmss").format(Date().time).toInt()
        val newTitle = newItem.title
        val newScript = newItem.subscript
        val newImage = newItem.imgUrl

        postDB.getDao().insertPost(PostData(newId, newTitle, newScript, newImage))


    }

}