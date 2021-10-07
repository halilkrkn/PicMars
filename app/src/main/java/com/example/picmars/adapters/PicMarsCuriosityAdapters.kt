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


class PicMarsCuriosityAdapters: RecyclerView.Adapter<PicMarsCuriosityAdapters.PicMarsViewHolder>() {

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
                .load("https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG")
                .override(200, 200)
                .into(ivCuriosityImage)


            tvCuriosityCamera.text = curiosityPhoto.camera.name
            tvCuriosityRoverName.text = curiosityPhoto.rover.name
            tvCuriosityEarthDate.text = curiosityPhoto.earthDate
            setOnClickListener {
                onItemClickListener?.let {
                    it(curiosityPhoto)
                }
            }
        }
    }

    private var onItemClickListener: ((Photo) -> Unit)? = null

    fun setOnItemClickListener(listener:(Photo) -> Unit){
        onItemClickListener = listener
    }

}