package com.example.androidstudy_sh

import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidstudy_sh.databinding.ActivityMainBinding
import com.example.androidstudy_sh.databinding.MessageItemBinding

class RecyclerViewAdapter(val MainBinding: MessageItemBinding, val itemDatas : MutableList<itemData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {





    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        MyViewHolder(MessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun getItemCount(): Int {
        return itemDatas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).ItemBinding
        binding.titleTv.text = itemDatas[position].title
        binding.subscriptTv.text = itemDatas[position].subscript
        Glide.with(holder.itemView.context)
            .load(itemDatas[position].imgUrl)
            .into(binding.mainImageIv)
    }
}

class MyViewHolder(val ItemBinding: MessageItemBinding) : RecyclerView.ViewHolder(ItemBinding.root) {
//        private var imageView : ImageView = view.findViewById(R.id.mainImage_iv)
//        private var titleTextView : TextView = view.findViewById(R.id.title_tv)
//        private var scriptTextView : TextView = view.findViewById(R.id.subscript_tv)

    fun bind(dataMap: itemData) {
        ItemBinding.titleTv.text = dataMap.title
        ItemBinding.subscriptTv.text = dataMap.subscript
        Glide.with(itemView)
            .load(dataMap.imgUrl)
            .into(ItemBinding.mainImageIv)

        Log.d("로그", "이미지 : ${dataMap.imgUrl} , 제목 : ${dataMap.title} , 설명 : ${dataMap.subscript}")

    }
}