package com.github.fajaragungpramana.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fajaragungpramana.android.databinding.ActivityMainBinding
import com.github.fajaragungpramana.android.databinding.ItemListDayBinding
import com.github.fajaragungpramana.paginghelper.PagingHelperAdapterImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("SetTextI18n")
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