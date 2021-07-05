package com.github.fajaragungpramana.android

import androidx.recyclerview.widget.DiffUtil

data class Day(

    val name: String? = null

) {
    companion object {

        val DIFF_UTIL = object : DiffUtil.ItemCallback<Day>() {

            override fun areContentsTheSame(oldItem: Day, newItem: Day) = oldItem == newItem

            override fun areItemsTheSame(oldItem: Day, newItem: Day) = oldItem == newItem

        }

    }
}