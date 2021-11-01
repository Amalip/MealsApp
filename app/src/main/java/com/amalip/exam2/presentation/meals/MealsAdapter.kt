package com.amalip.exam2.presentation.meals

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amalip.exam2.databinding.RowMealBinding
import com.amalip.exam2.domain.model.Meal

/**
 * Created by Amalip on 9/28/2021.
 */

@SuppressLint("NotifyDataSetChanged")
class MealsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<Meal> = mutableListOf()
    private lateinit var listener: (meal: Meal, executeLike: Boolean) -> Unit

    fun addData(list: List<Meal>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    fun setLike(meal: Meal) {
        list.find { it.idMeal == meal.idMeal }?.liked = meal.liked
        notifyItemChanged(list.indexOf(meal))
    }

    fun setListener(listener: (meal: Meal, executeLike: Boolean) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderItem(
        RowMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolderItem).bind(list[position], listener)

    override fun getItemCount() = list.size

    class ViewHolderItem(private val binding: RowMealBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Meal, listener: (meal: Meal, executeLike: Boolean) -> Unit) {
            binding.apply {
                item = data
                root.setOnClickListener { listener(data, false) }
                imgFavorite.setOnClickListener { listener(data, true) }
            }
        }
    }

}