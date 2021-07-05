# Android Paging Helper
[![](https://jitpack.io/v/fajaragungpramana/paging-helper.svg)](https://jitpack.io/#fajaragungpramana/paging-helper)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
</br>
</br>

Library for android. Helping you to use paging library.

## Installation
Add it in your root build.gradle at the end of repositories:
```gradle
allProjects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency:
```gradle
dependencies {
	implementation 'com.github.fajaragungpramana:paging-helper:0.0.1'
}
```

## Usage
First step for example i use fake mvvm flow. You can create remote data source or local data source service and create repository class. For example i create `FakeRepository.kt`.
```kotlin
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
```

Then create view model. And set page config, the return of `pageConfig(pageLimit: Int = 15, pageSource: PagingSource)` is coroutine flow `Flow<PagingData>`. For example i create `FakeViewModel.kt`.
```kotlin
class FakeViewModel(private val mFakeRepository: FakeRepository) {

    val listDay by lazy {
        PagingHelperConfig.pageConfig(pagingSource = mFakeRepository.getListDay())
    }

}
```

Then you can create paging recycler view adapter in your `Activity` or `Fragment` like this. `Note: You must implement ViewBinding library` to use this custom adapter.
```kotlin
private val mDayAdapter by lazy {
        PagingHelperAdapterImpl(
            Day.DIFF_UTIL,
            onCreateViewBinding = { inflater, parent, _ ->
                ItemListDayBinding.inflate(inflater, parent, false)
            },
            onBinding = { binding, entity, position ->
                (binding as ItemListDayBinding).apply {
                    tvDay.text = "${position + 1} ${entity?.name}"
                }
            }
        )
    }
```

This is the full code in activity for example i create `MainActivity.kt`.
```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private val mFakeViewModel by lazy { FakeViewModel(FakeRepository()) }

    private val mDayAdapter by lazy {
        PagingHelperAdapterImpl(
            Day.DIFF_UTIL,
            onCreateViewBinding = { inflater, parent, _ ->
                ItemListDayBinding.inflate(inflater, parent, false)
            },
            onBinding = { binding, entity, position ->
                (binding as ItemListDayBinding).apply {
                    tvDay.text = "${position + 1} ${entity?.name}"
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.rvDay.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mDayAdapter
        }

        GlobalScope.launch {
            mFakeViewModel.listDay.collectLatest { pagingDay ->
                mDayAdapter.submitData(pagingDay)
            }
        }

    }

}
```

## Preview
<a href="url"><img src="https://github.com/fajaragungpramana/assets/blob/master/PagingHelper/PagingHelper.jpg" align="left" height="640" width="320" ></a>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>

## License
Copyright 2021 Fajar Agung Pramana

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
