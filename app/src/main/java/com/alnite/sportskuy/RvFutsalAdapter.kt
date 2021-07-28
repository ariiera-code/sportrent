package com.alnite.sportskuy

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.alnite.sportskuy.fragments.BookActivity
import kotlinx.android.synthetic.main.rv_futsallist.view.*


class RvFutsalAdapter : RecyclerView.Adapter<RvFutsalAdapter.ViewHolder>(){

    private val itemtitle =
        arrayOf("Mulawarman Futsal Court", "Aquarius Futsal Arena", "Moon Futsal")
    private val itemlocation = arrayOf("Mulawarman", "Delanggu", "Klaten")
    private val itemketerangan = arrayOf(
        "Tersedia Makanan & Minuman",
        "Tersedia Makanan & Minuman",
        "Tersedia Makanan & Minuman"
    )
    private val itemrp = arrayOf("Rp.", "Rp.", "Rp.")
    private val itemharga = arrayOf("30.000", "50.000", "100.000")
    private val itemjam = arrayOf("/jam", "/jam", "/jam")
    private val itemimage1 = intArrayOf(
        R.drawable.lpft1mulawarman1,
        R.drawable.lpft2afa1,
        R.drawable.lpft3moon1
    )
    private val itemimage2 = intArrayOf(
        R.drawable.lpft1mulawarman2,
        R.drawable.lpft2afa2,
        R.drawable.lpft3moon2
    )
    private val itemimage3 = intArrayOf(
        R.drawable.lpft1mulawarman3,
        R.drawable.lpft2afa3,
        R.drawable.lpft3moon3
    )

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var image: ImageView
        var image2: ImageView
        var image3: ImageView
        var texttitle: TextView
        var textlocation: TextView
        var textketerangan: TextView
        var textrp: TextView
        var textharga: TextView
        var textjam: TextView
        var btnbook: CardView

        init {
            image = itemView.futsalcv_mulawarman1
            image2 = itemView.futsalcv_mulawarman2
            image3 = itemView.futsalcv_mulawarman3
            texttitle = itemView.tvcvNameMulawarman
            textlocation = itemView.tvLocMulawarman
            textketerangan = itemView.tvFiturMulawarman
            textrp = itemView.tvcvrupiahmulawarman
            textharga = itemView.tvcvHarga
            textjam = itemView.tvcvperjam
            btnbook = itemView.btnBookSekMulawarman
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_futsallist, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.texttitle.text = itemtitle[position]
        holder.textlocation.text = itemlocation[position]
        holder.textketerangan.text = itemketerangan[position]
        holder.textrp.text = itemrp[position]
        holder.textharga.text = itemharga[position]
        holder.textjam.text = itemjam[position]
        holder.image.setImageResource(itemimage1[position])
        holder.image2.setImageResource(itemimage2[position])
        holder.image3.setImageResource(itemimage3[position])
    }
    override fun getItemCount(): Int {
        return itemtitle.size
    }
    class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener {
                val intent = Intent(view.context, BookActivity::class.java)
            }
        }
    }
}