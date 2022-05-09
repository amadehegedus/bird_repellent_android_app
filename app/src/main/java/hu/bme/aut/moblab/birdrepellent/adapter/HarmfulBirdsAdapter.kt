package hu.bme.aut.moblab.birdrepellent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.moblab.birdrepellent.databinding.HarmfulBirdBinding
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird

class HarmfulBirdsAdapter(private val listener: OnItemClickListener): ListAdapter<HarmfulBird, HarmfulBirdsAdapter.HarmfulBirdViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarmfulBirdViewHolder {
        val binding = HarmfulBirdBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return HarmfulBirdViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HarmfulBirdViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class HarmfulBirdViewHolder(private val binding: HarmfulBirdBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                //Set simple click listener
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val bird = getItem(position)
                        listener.onItemClick(bird)
                    }
                }
                //Set toggle listener
                cbEnabled.setOnClickListener{
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val bird = getItem(position)
                        listener.onItemToggle(bird, cbEnabled.isChecked)
                    }
                }
                //Set delete listener
                btnDelete.setOnClickListener{
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val bird = getItem(position)
                        listener.onItemDelete(bird)
                    }
                }
            }
        }

        fun bind(harmfulBird: HarmfulBird){
            binding.apply {
                tvName.text = harmfulBird.name
                cbEnabled.isChecked = harmfulBird.active
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(harmfulBird: HarmfulBird)
        fun onItemDelete(harmfulBird: HarmfulBird)
        fun onItemToggle(harmfulBird: HarmfulBird, active: Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<HarmfulBird>(){
        override fun areItemsTheSame(oldItem: HarmfulBird, newItem: HarmfulBird): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HarmfulBird, newItem: HarmfulBird): Boolean {
            return oldItem.id == newItem.id
        }
    }
}