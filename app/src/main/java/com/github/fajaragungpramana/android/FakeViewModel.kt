package com.github.fajaragungpramana.android

import com.github.fajaragungpramana.paginghelper.PagingHelperConfig

class FakeViewModel(private val mFakeRepository: FakeRepository) {

    val listDay by lazy {
        PagingHelperConfig.pageConfig(pagingSource = mFakeRepository.getListDay())
    }

}