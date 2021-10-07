package com.example.picmars.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.picmars.R
import com.example.picmars.models.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article_preview_curiosity.view.*
import kotlinx.android.synthetic.main.item_article_preview_spirit.view.*

class PicMarsSpiritAdapters: RecyclerView.Adapter<PicMarsSpiritAdapters.PicMarsViewHolder>() {

    inner class PicMarsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    //DiffUtil, RecyclerView adapterındaki verilerin daha verimli bir şekilde güncellenmesi için kullanılır.
    private val differCallback = object: DiffUtil.ItemCallback<Photo>(){

        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return  oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicMarsViewHolder {
        return PicMarsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview_spirit,
                parent,
                false

            )
        )
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PicMarsViewHolder, position: Int) {

        val spiritPhoto = differ.currentList[position]
        holder.itemView.apply {
//                        Picasso.get().load(spiritPhoto.imgSrc).into(ivSpiritImage);
            Glide.with(this).load("https://mars.nasa.gov/mer/gallery/all/2/r/001/2R126468012EDN0000P1002L0M1-BR.JPG").into(ivSpiritImage)
            tvSpiritCamera.text = spiritPhoto.camera.name
            tvSpiritRoverName.text = spiritPhoto.rover.name
            tvSpiritEarthDate.text = spiritPhoto.earthDate
            setOnClickListener {
                onItemClickListener?.let {
                    it(spiritPhoto)
                }
            }
        }
    }

    private var onItemClickListener: ((Photo) -> Unit)? = null

    fun setOnItemClickListener(listener:(Photo) -> Unit){
        onItemClickListener = listener
    }

}