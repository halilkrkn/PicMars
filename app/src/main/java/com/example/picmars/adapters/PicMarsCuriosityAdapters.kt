package com.example.picmars.adapters

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.picmars.R
import com.example.picmars.models.Camera
import com.example.picmars.models.Photo
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.item_article_preview_curiosity.view.*
import kotlinx.android.synthetic.main.popup.*
import kotlinx.android.synthetic.main.popup.view.*
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
                .load("https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01004/opgs/edr/fcam/FLB_486615455EDR_F0481570FHAZ00323M_.JPG")
                .override(200, 200)
                .into(ivCuriosityImage)


            tvCuriosityCamera.text = curiosityPhoto.camera.name
            tvCuriosityRoverName.text = curiosityPhoto.rover.name
            tvCuriosityEarthDate.text = curiosityPhoto.earthDate

//                txCameraPopup.text = curiosityPhoto.camera.name
//                txCameraPopup1.text = curiosityPhoto.rover.name
//                txCameraPopup2.text = curiosityPhoto.earthDate

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
                    txTakesDate.text = "Çekilen Tarih: ${curiosityPhoto.earthDate}"
                    txRoverName.text = "Araç Adı: ${curiosityPhoto.rover.name}"
                    txCameraName.text = "Kamera Adı: ${curiosityPhoto.camera.name}"
                    txRoverState.text = "Görev Durumu: ${curiosityPhoto.rover.status}"
                    txRoverLaunchDate.text = "Fırlatma Tarihi: ${curiosityPhoto.rover.launchDate}"
                    txRoverLandingDate.text = "İniş Tarihi: ${curiosityPhoto.rover.landingDate}"
                    Glide.with(this)
                        .load("https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01004/opgs/edr/fcam/FLB_486615455EDR_F0481570FHAZ00323M_.JPG")
                        .override(375, 175)
                        .into(imagePopup)

                }
                bottomSheetDialog.setContentView(bottomSheetView)
                bottomSheetDialog.show()
            }
        }
    }
}
