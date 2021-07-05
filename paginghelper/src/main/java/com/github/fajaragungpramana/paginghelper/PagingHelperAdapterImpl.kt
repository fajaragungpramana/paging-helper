package com.github.fajaragungpramana.paginghelper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import java.lang.NullPointerException

class PagingHelperAdapterImpl<E : Any>(
    diffUtil: DiffUtil.ItemCallback<E>,
    private inline val mOnEachItemType: (entity: E?) -> Int,
    private inline val mOnCreateViewBinding: (inflater: LayoutInflater, parent: ViewGroup, viewType: Int) -> ViewBinding?,
    private inline val mOnBinding: (binding: ViewBinding, entity: E?, position: Int) -> Unit
) : PagingHelperAdapter<ViewBinding, E>(diffUtil) {

    constructor(
        diffUtil: DiffUtil.ItemCallback<E>,
        onCreateViewBinding: (inflater: LayoutInflater, parent: ViewGroup, viewType: Int) -> ViewBinding? = { _, _, _ -> null },
        onBinding: (binding: ViewBinding, entity: E?, position: Int) -> Unit = { _, _, _ -> }
    ) : this(diffUtil, { 0 }, onCreateViewBinding, onBinding)

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewBinding = mOnCreateViewBinding(inflater, parent, viewType) ?: throw NullPointerException(
        "Failed to inflate adapter layout."
    )

    override fun onPrepareBindViewHolder(binding: ViewBinding, entity: E?, position: Int) {
        mOnBinding(binding, entity, position)
    }

    override fun getItemViewType(position: Int) = mOnEachItemType(getItem(position))

}