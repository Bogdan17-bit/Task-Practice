package com.example.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver.OnScrollChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.response.User
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.dbrepository.UserRepositoryDb
import com.example.myapplication.ui.base.BaseActivity
import com.example.myapplication.ui.info.InfoUserActivity
import com.example.myapplication.utils.Network
import com.example.myapplication.utils.UserConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel : MainViewModel by viewModel()

    private val mAdapter : MainAdapter by inject()

    private val mDatabase : AppDatabase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        setObserverSaveToDatabase()
        setObserverViewBindingLoading()
        initAdapter()
        checkScrolledDownPageListener()
        checkClickedOnUserListener()
        setObserverAdapter()

    }

    private fun checkScrolledDownPageListener() {
        mViewBinding.scrollView.viewTreeObserver.addOnScrollChangedListener(OnScrollChangedListener {
            val scrollY = mViewBinding.scrollView.scrollY
            val measuredHeightAtFirstChild = mViewBinding.scrollView.getChildAt(0).measuredHeight
            val measuredParent = mViewBinding.scrollView.measuredHeight
            if (!mViewModel.loading.value!! && scrollY == measuredHeightAtFirstChild - measuredParent) {
                loadAdditionalUsers()
            }
        })
    }

    private fun loadAdditionalUsers() {
        mViewModel.getUsersFromServer(this)
    }

    private fun checkClickedOnUserListener() {
        mAdapter.setOnItemClickListener(object : MainAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                openInfoUserActivity(mAdapter.getUsers()[position].getFullName())
            }
        })
    }

    private fun openInfoUserActivity(userFullName : String) {
        val intent = Intent(this@MainActivity, InfoUserActivity::class.java)
        with(intent) {
            putExtra("userFullName", userFullName)
            startActivity(this)
        }
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(this)
        mViewBinding.recyclerView.layoutManager = layoutManager
        mViewBinding.recyclerView.adapter = mAdapter
        if (mViewModel.is_loaded.value == false) {
            loadAdditionalUsers()
        }
    }

    private fun setObserverSaveToDatabase() {
        mViewModel.usersList.observe(this) {
            mAdapter.setUsers(it)
            for(user in it) {
                mViewModel.getUsersDatabaseFromServerUsers(user)
            }
        }
    }

    private fun setObserverAdapter() {
        mViewModel.usersFromDb.observe(this) {
            if(!Network.isNetworkAvailable(this)) {
                mAdapter.setUsers(it)
            }
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