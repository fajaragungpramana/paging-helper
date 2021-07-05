package com.github.fajaragungpramana.paginghelper

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PagingHelperDataSource<E : Any>(
    inline val onResponse: (pageIndex: Int) -> List<E>,
    inline val onError: (e: Throwable) -> Unit
) : PagingSource<Int, E>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, E> {
        val pageIndex = params.key ?: 1
        return try {
            val result = onResponse(pageIndex)

            LoadResult.Page(
                result,
                if (pageIndex == 1) null else pageIndex,
                if (result.isNullOrEmpty()) null else pageIndex + 1
            )
        } catch (e: Throwable) {
            onError(e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, E>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}