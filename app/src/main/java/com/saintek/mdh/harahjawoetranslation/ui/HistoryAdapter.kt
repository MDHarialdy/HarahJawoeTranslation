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
                binding.progressBar.progress = item.accuration
                binding.tvProfileText.text = item.result
                binding.tvPercentage.text = "${item.accuration}%"
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

//class HistoryAdapter :
//    ListAdapter<HistoryEntity, HistoryAdapter.MyViewHolder>(HistoryDiffCallback()) {
//
//    class MyViewHolder(private val binding: ItemHistoryBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: HistoryEntity) {
//            val bitmap = BitmapFactory.decodeByteArray(
//                item.imageByteArray, 0, item.imageByteArray.size
//            )
//            binding.apply {
//                ivPhotoHistory.setImageBitmap(bitmap)
//                tvProfileText.text = item.result
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val binding =
//            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MyViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val historyEntity = getItem(position)
//        holder.bind(historyEntity)
//    }
//}
//
//class HistoryDiffCallback : DiffUtil.ItemCallback<HistoryEntity>() {
//    override fun areItemsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
//        return oldItem.id == newItem.id
//        // Replace 'id' with the actual unique identifier of your HistoryEntity
//    }
//
//    override fun areContentsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
//        return oldItem == newItem
//    }
//}
