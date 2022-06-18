package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel : MainViewModel by viewModel()

    private val mAdapter : MainAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        setObserverAdapter()
        setObserverViewBindingLoading()
        initAdapter()

    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(this)
        mViewBinding.recyclerView.layoutManager = layoutManager
        mViewBinding.recyclerView.adapter = mAdapter
        if(mViewModel.is_loaded.value == false) {
            mViewModel.getUsersFromServer()
        }
    }

    private fun setObserverAdapter() {
        mViewModel.usersList.observe(this) {
            mAdapter.setUsers(it)
        }
    }

    private fun setObserverViewBindingLoading() {
        mViewModel.loading.observe(this, Observer {
            if(it) {
                mViewBinding.progressBar.visibility = View.VISIBLE
            }
            else {
                mViewBinding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

}