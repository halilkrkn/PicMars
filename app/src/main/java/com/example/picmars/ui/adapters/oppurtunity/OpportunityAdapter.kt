package com.example.picmars.ui.adapters.curiosity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.picmars.R
import com.example.picmars.data.models.Photo
import com.example.picmars.databinding.ItemPhotoPicmarsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.popup.view.*


class OpportunityAdapter : PagingDataAdapter<Photo, OpportunityAdapter.PicMarsCuriosityViewHolder>(
    PICMARS_COMPORATOR
) {

    inner class PicMarsCuriosityViewHolder(private val binding: ItemPhotoPicmarsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(picMars: Photo) {
            binding.apply {
                Glide.with(itemView)
                    .load(picMars.imgSrc)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                    .into(imageViewPicmars)

                textViewCameraName.text = picMars.camera.fullName
                textViewLaunchDate.text = "Launch Date: ${picMars.rover.launchDate}"

                itemView.apply {
                    cardView.setOnClickListener {

                        val bottomSheetDialog = BottomSheetDialog(
                            it.context, R.style.Theme_MaterialComponents_Light_BottomSheetDialog
                        )
                        val bottomSheetView = LayoutInflater.from(it.context).inflate(
                            R.layout.popup,
                            findViewById<LinearLayout>(R.id.bottomSheet)
                        )

                        bottomSheetView.apply {
                            txTakesDate.text = "Çekilen Tarih: ${picMars.earthDate}"
                            txRoverName.text = "Araç Adı: ${picMars.rover.name}"
                            txCameraName.text = "Kamera Adı: ${picMars.camera.name}"
                            txRoverState.text = "Görev Durumu: ${picMars.rover.status}"
                            txRoverLaunchDate.text =
                                "Fırlatma Tarihi: ${picMars.rover.launchDate}"
                            txRoverLandingDate.text =
                                "İniş Tarihi: ${picMars.rover.landingDate}"
                            Glide.with(this)
                                .load(picMars.imgSrc)
                                .centerCrop()
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .error(R.drawable.ic_baseline_image_not_supported_24)
                                .into(image_view_popup)

                        }
                        bottomSheetDialog.setContentView(bottomSheetView)
                        bottomSheetDialog.show()

                    }
                }

            }
        }

    }

    override fun onBindViewHolder(holder: PicMarsCuriosityViewHolder, position: Int) {
        val currentPosition = getItem(position)
        if (currentPosition != null) {
            holder.bind(currentPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicMarsCuriosityViewHolder {
        val binding =
            ItemPhotoPicmarsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PicMarsCuriosityViewHolder(binding)
    }

    companion object {
        private val PICMARS_COMPORATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }

        }
    }


}
