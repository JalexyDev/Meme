package com.jalexy.meme.dashboard.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jalexy.meme.R

class DashboardItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val ivLogoMeme: ImageView = itemView.findViewById<ImageView>(R.id.ivLogoMeme)
    val tvTopText: TextView = itemView.findViewById<TextView>(R.id.tvTopText)
    val tvBottomText: TextView = itemView.findViewById<TextView>(R.id.tvBottomText)
    val tvName: TextView = itemView.findViewById<TextView>(R.id.tvName)
    val tvTags: TextView = itemView.findViewById<TextView>(R.id.tvTags)
}