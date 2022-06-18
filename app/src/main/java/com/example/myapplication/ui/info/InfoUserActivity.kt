package com.example.myapplication.ui.info

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivityInfoUserBinding
import com.example.myapplication.models.response.User
import com.example.myapplication.ui.base.BaseActivity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoUserActivity : BaseActivity<InfoViewModel, ActivityInfoUserBinding>() {

    override val mViewModel : InfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        getUserFromIntent()
        setObserverUserData()
    }

    private fun getUserFromIntent() {
        val userStr : String = intent.getStringExtra("user").toString()
        val user : User = Json.decodeFromString(userStr)
        mViewModel.setUserData(user)
    }

    private fun setObserverUserData() {
        mViewModel.user.observe(this, Observer {
            mViewBinding.nameTextView.text = it.getFullName()
            mViewBinding.emailTextView.text = it.getEmail()
            mViewBinding.phoneTextView.text = it.getPhone()
            mViewBinding.locationTextView.text = it.getFullLocation()
            mViewBinding.registerTextView.text = it.getRegisteredData()
            it.setImageBitmap(mViewBinding.avatarImageView)
        })
    }

    override fun getViewBinding(): ActivityInfoUserBinding {
        return ActivityInfoUserBinding.inflate(layoutInflater)
    }

}