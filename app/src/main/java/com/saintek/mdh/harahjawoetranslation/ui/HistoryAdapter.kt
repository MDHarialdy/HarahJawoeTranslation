package com.saintek.mdh.harahjawoetranslation.ui

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saintek.mdh.harahjawoetranslation.data.database.HistoryEntity
import com.saintek.mdh.harahjawoetranslation.databinding.ItemHistoryBinding

class HistoryAdapter(private val historyEntity: List<HistoryEntity>): RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {
    class MyViewHolder(private val binding: ItemHistoryBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: HistoryEntity) {
                val bitmap = BitmapFactory.decodeByteArray(
                    item.imageByteArray, 0, item.imageByteArray.size
                )
                binding.apply {
                    ivPhotoHistory.setImageBitmap(bitmap)
                }
                binding.tvProfileText.text = item.result
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return historyEntity.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val historyEntity = historyEntity[position]
        holder.bind(historyEntity)
    }

}