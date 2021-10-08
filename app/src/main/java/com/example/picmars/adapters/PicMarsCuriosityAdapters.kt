package com.example.picmars.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.PopupWindow
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.picmars.R
import com.example.picmars.models.Camera
import com.example.picmars.models.Photo
import com.squareup.picasso.Picasso
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.item_article_preview_curiosity.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.coroutineContext


class PicMarsCuriosityAdapters: RecyclerView.Adapter<PicMarsCuriosityAdapters.PicMarsViewHolder>(){


    inner class PicMarsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    //DiffUtil, RecyclerView adapterındaki verilerin daha verimli bir şekilde güncellenmesi için kullanılır.
    private val differCallback = object: DiffUtil.ItemCallback<Photo>(){

        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return  oldItem.hashCode() == newItem.hashCode()
        }

    }

    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicMarsViewHolder {
        return PicMarsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview_curiosity,
                parent,
                false

            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
//
    override fun onBindViewHolder(holder: PicMarsViewHolder, position: Int) {

        val curiosityPhoto = differ.currentList[position]
        holder.itemView.apply {
//            Picasso.get().load(curiosityPhoto.imgSrc).into(ivCuriosityImage);
//            val url = curiosityPhoto.imgSrc
            Glide.with(this)
                .load(curiosityPhoto.imgSrc)
                .override(200, 200)
                .into(ivCuriosityImage)


            tvCuriosityCamera.text = curiosityPhoto.camera.name
            tvCuriosityRoverName.text = curiosityPhoto.rover.name
            tvCuriosityEarthDate.text = curiosityPhoto.earthDate
            setOnClickListener {

            }
        }
    }

}