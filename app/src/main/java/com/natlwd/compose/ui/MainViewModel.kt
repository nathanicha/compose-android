package com.natlwd.compose.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natlwd.compose.data.repository.UserRepository
import com.natlwd.compose.model.NetworkResult
import com.natlwd.compose.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = UserRepository.instance

    private val _userLiveData: MutableLiveData<NetworkResult<UserResponse?>> = MutableLiveData()
    val userLiveData: LiveData<NetworkResult<UserResponse?>> = _userLiveData

    fun getUsers() {
        viewModelScope.launch(Dispatchers.Main) {
            _userLiveData.value = NetworkResult.Started
            _userLiveData.value = repository.getUserReturnResp("1")
        }
    }
}