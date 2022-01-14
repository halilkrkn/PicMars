package com.example.picmars.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.picmars.R
import com.example.picmars.data.models.Photo
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.item_photo_picmars.view.*
import kotlinx.android.synthetic.main.popup.view.*

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
                R.layout.item_photo_picmars,
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
            Glide.with(this).load("https://mars.nasa.gov/mer/gallery/all/1/n/001/1N128285132EDN0000P1500R0M1-BR.JPG").into(image_view_picmars)
            text_view_camera_name.text = opportunityPhoto.camera.name
            text_view_launch_date.text = opportunityPhoto.rover.launchDate
            setOnClickListener {


//               Snackbar.make(it,"Giriş Yapıldı: ${curiosityPhoto.camera.fullName}",Snackbar.LENGTH_LONG).show()
//                Toast.makeText(it.getContext(), "Görüntüler Yükleniyor", Toast.LENGTH_SHORT).show();
                val bottomSheetDialog = BottomSheetDialog(
                    it.context,R.style.Theme_MaterialComponents_Light_BottomSheetDialog
                )
                val bottomSheetView = LayoutInflater.from(it.context).inflate(R.layout.popup,
                    findViewById<LinearLayout>(R.id.bottomSheet)
                )

                bottomSheetView.apply {
                    txTakesDate.text = "Çekilen Tarih: ${opportunityPhoto.earthDate}"
                    txRoverName.text = "Araç Adı: ${opportunityPhoto.rover.name}"
                    txCameraName.text = "Kamera Adı: ${opportunityPhoto.camera.name}"
                    txRoverState.text = "Görev Durumu: ${opportunityPhoto.rover.status}"
                    txRoverLaunchDate.text = "Fırlatma Tarihi: ${opportunityPhoto.rover.launchDate}"
                    txRoverLandingDate.text = "İniş Tarihi: ${opportunityPhoto.rover.landingDate}"
                    Glide.with(this)
                        .load("https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01004/opgs/edr/fcam/FLB_486615455EDR_F0481570FHAZ00323M_.JPG")
                        .override(375, 175)
                        .into(image_view_popup)

                }
                bottomSheetDialog.setContentView(bottomSheetView)
                bottomSheetDialog.show()

                }
            }
        }
    }
