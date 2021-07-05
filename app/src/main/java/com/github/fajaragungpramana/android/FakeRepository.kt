package com.github.fajaragungpramana.android

import com.github.fajaragungpramana.paginghelper.PagingHelperDataSource

class FakeRepository {

    fun getListDay() = PagingHelperDataSource(
        onResponse = { pageIndex ->
            return@PagingHelperDataSource FakeDataSource.getListDay(pageIndex)
        },
        onError = { e ->
            // TODO: Do something here when response get an exception.
        }
    )

}