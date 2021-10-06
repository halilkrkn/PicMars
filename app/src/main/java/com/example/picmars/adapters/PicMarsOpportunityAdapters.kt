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
import kotlinx.android.synthetic.main.item_article_preview_opportunity.view.*

class PicMarsOpportunityAdapters: RecyclerView.Adapter<PicMarsOpportunityAdapters.PicMarsViewHolder>() {

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
                R.layout.item_article_preview_opportunity,
                parent,
                false

            )
        )
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PicMarsViewHolder, position: Int) {

        val  opportunityPhoto = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load("https://mars.nasa.gov/mer/gallery/all/1/n/1000/1N216958451EFF76ZFP1950R0M1-BR.JPG").into(ivOpportunityImage)
            tvOpportunityCamera.text = opportunityPhoto.camera.name
            tvOpportunityRoverName.text = opportunityPhoto.rover.name
            tvOpportunityEarthDate.text = opportunityPhoto.earthDate
            setOnClickListener {
                onItemClickListener?.let {
                    it(opportunityPhoto)
                }
            }
        }
    }

    private var onItemClickListener: ((Photo) -> Unit)? = null

    fun setOnItemClickListener(listener:(Photo) -> Unit){
        onItemClickListener = listener
    }

}