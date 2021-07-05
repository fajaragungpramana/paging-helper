package com.github.fajaragungpramana.paginghelper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @param VB - Is view binding layout.
 * @param E - Is entity or data class.
 */
abstract class PagingHelperAdapter<VB : ViewBinding, E : Any>(diffUtil: DiffUtil.ItemCallback<E>) :
    PagingDataAdapter<E, PagingHelperAdapter.ViewHolder<VB>>(diffUtil) {

    protected abstract fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): VB

    protected abstract fun onPrepareBindViewHolder(binding: ViewBinding, entity: E?, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<VB> {
        return ViewHolder(onCreateViewHolder(LayoutInflater.from(parent.context), parent, viewType))
    }

    override fun onBindViewHolder(holder: ViewHolder<VB>, position: Int) {
        onPrepareBindViewHolder(holder.binding, getItem(position), position)
    }

    class ViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)

}