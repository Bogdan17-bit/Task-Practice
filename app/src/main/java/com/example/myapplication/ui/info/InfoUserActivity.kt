package com.example.myapplication.ui.info

import android.os.Bundle
import com.example.myapplication.databinding.ActivityInfoUserBinding
import com.example.myapplication.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoUserActivity : BaseActivity<InfoViewModel, ActivityInfoUserBinding>() {

    override val mViewModel : InfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
    }

    override fun getViewBinding(): ActivityInfoUserBinding {
        return ActivityInfoUserBinding.inflate(layoutInflater)
    }

}