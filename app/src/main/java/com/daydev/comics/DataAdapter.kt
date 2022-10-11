package com.daydev.comics

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class DataAdapter(val dataModelList: List<DataModel>) : RecyclerView.Adapter<ViewHolder>(){

    private val TAG = "Comic"
    val mylist = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.model,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataModelList[position]
        holder.textTitle.text = dataModel.title

        var databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.child("data").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                Log.d(TAG, "Count= " + dataSnapshot.childrenCount)
                for (childDataSnapshot in dataSnapshot.children) {
                    Log.d(TAG, "snapshot= " + childDataSnapshot.key!!)
                    mylist.add(childDataSnapshot.key!!)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })


        holder.imageView.setOnClickListener(View.OnClickListener { v ->
            val filePath = dataModel.files
            Log.d(TAG, "filePath=$filePath")

            val readActivity = Intent(v.context, InformationActivity::class.java)
            readActivity.putExtra("filePath", filePath)
            readActivity.putExtra("keys", mylist[position])
            v.context.startActivity(readActivity)
        })


        Picasso.get().load(dataModel.photos)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return dataModelList.size
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textTitle: TextView = itemView.findViewById(R.id.title)
    var imageView: ImageView

    init {
        imageView = itemView.findViewById(R.id.thumbnail)
    }
}

