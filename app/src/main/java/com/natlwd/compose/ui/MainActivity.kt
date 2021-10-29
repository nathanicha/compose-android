package com.natlwd.compose.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.natlwd.compose.databinding.ActivityMainBinding
import com.natlwd.compose.extensions.handleStateWithLoading
import com.natlwd.compose.utils.viewBinding

class MainActivity : BasActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObserve()
        setupListener()

        model.getUsers()
    }

    private fun setupObserve() {
        model.userLiveData.observe(this, { state ->
            state.handleStateWithLoading(
                this,
                complete = {
                    binding.mainActivityTextView.text = it?.title
                },
                failed = {
                    binding.mainActivityTextView.text = it.message
                }
            )
        })
    }

    private fun setupListener() {
        binding.mainActivityButton.setOnClickListener {
            model.getUsers()
        }
    }
}