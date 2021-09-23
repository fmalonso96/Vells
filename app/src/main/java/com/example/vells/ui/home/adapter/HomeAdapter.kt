package com.example.vells.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vells.R
import com.example.vells.data.model.TablePOJO
import com.example.vells.databinding.ItemRecyclerBinding
import kotlinx.android.synthetic.main.item_recycler.view.*

class HomeAdapter(
    private val tables: List<TablePOJO>,
    private val onClick: (TablePOJO) -> Unit,
    private var optionsMenuClickListener: OptionsMenuClickListener
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    // create an interface for onClickListener
    // so that we can handle data most effectively in MainActivity.kt
    interface OptionsMenuClickListener {
        fun onOptionsMenuClicked(position: Int)
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemRecyclerBinding.bind(view)

        fun bind(table: TablePOJO) {
            binding.tvTableTitle.text = table.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeViewHolder(layoutInflater.inflate(R.layout.item_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val table = tables[position]
        holder.bind(table)
        holder.itemView.setOnClickListener {
            onClick(table)
        }
        holder.itemView.moreIcon.setOnClickListener {
            optionsMenuClickListener.onOptionsMenuClicked(position)
        }
    }

    override fun getItemCount(): Int = tables.size
}