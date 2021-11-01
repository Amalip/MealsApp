package com.amalip.exam2.presentation.categories

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amalip.exam2.databinding.RowCategoriesBinding
import com.amalip.exam2.domain.model.Category

/**
 * Created by Amalip on 9/28/2021.
 */

@SuppressLint("NotifyDataSetChanged")
class CategoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<Category> = mutableListOf()
    private lateinit var listener: (category: Category) -> Unit

    fun addData(list: List<Category>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    fun setListener(listener: (category: Category) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderItem(
        RowCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolderItem).bind(list[position], listener)

    override fun getItemCount() = list.size

    class ViewHolderItem(private val binding: RowCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Category, listener: (category: Category) -> Unit) {
            binding.item = data

            binding.root.setOnClickListener { listener(data) }
        }
    }

}