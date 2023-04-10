package com.swj.quicksearchplace.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.swj.quicksearchplace.activities.PlaceUrlActivity
import com.swj.quicksearchplace.databinding.RecyclerItemListFragmentBinding
import com.swj.quicksearchplace.model.Place

class PlaceListRecyclerAdapter(val context: Context, val documents:MutableList<Place>)
    : Adapter<PlaceListRecyclerAdapter.VH>() {
    inner class VH(val binding:RecyclerItemListFragmentBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
        = VH(RecyclerItemListFragmentBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = documents.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val place = documents[position]
        holder.binding.tvPlaceName.text = place.place_name
        holder.binding.tvDistance.text = "${place.distance}m"
        holder.binding.tvAddress.text =
            if(place.road_address_name == "") place.address_name
            else place.road_address_name

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, PlaceUrlActivity::class.java)
            intent.putExtra("place_url",  place.place_url)
            context.startActivity(intent)
        }
    }
}