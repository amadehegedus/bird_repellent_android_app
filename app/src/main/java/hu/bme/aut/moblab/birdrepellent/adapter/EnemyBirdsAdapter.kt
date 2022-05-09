package hu.bme.aut.moblab.birdrepellent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.moblab.birdrepellent.databinding.EnemyBirdBinding
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird

class EnemyBirdsAdapter(private val listener: OnItemClickListener): ListAdapter<String, EnemyBirdsAdapter.EnemyBirdViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnemyBirdViewHolder {
        val binding = EnemyBirdBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return EnemyBirdViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EnemyBirdViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class EnemyBirdViewHolder(private val binding: EnemyBirdBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                //Set simple click listener
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val enemy = getItem(position)
                        listener.onItemClick(enemy)
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

        fun bind(enemyBird: String){
            binding.apply {
                tvName.text = enemyBird
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(enemyBird: String)
        fun onItemDelete(enemyBird: String)
    }

    class DiffCallback : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}