package com.github.fajaragungpramana.paginghelper

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow

object PagingHelperConfig {

    fun <E : Any> pageConfig(
        pageLimit: Int = 15,
        pagingSource: PagingSource<Int, E>
    ): Flow<PagingData<E>> =
        Pager(PagingConfig(pageLimit), pagingSourceFactory = { pagingSource }).flow

}