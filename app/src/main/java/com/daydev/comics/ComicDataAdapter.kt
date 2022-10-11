package com.daydev.comics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ComicDataAdapter (private val dataModelList: List<DataModel>) : RecyclerView.Adapter<DataViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pages,parent,false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataModel = dataModelList[position]

        Picasso.get().load(dataModel.photo)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return dataModelList.size
    }

}

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView
    init {
        imageView = itemView.findViewById(R.id.imageView)
    }
}
