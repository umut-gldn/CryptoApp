package com.umut.cryptoapp.viewmodel

import CryptoDetailInfo
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umut.cryptoapp.repository.CryptoRepository
import com.umut.cryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    private val _cryptoPrice = MutableStateFlow<Resource<String>>(Resource.Loading())
    val cryptoPrice: StateFlow<Resource<String>> = _cryptoPrice

    private val _cryptoInfo = MutableStateFlow(CryptoDetailInfo("", "", ""))
    val cryptoInfo: StateFlow<CryptoDetailInfo> = _cryptoInfo

    fun getCryptoPrice(symbol: String) {
        viewModelScope.launch {
            _cryptoPrice.value = repository.getCryptoPrice(symbol)
        }
    }

    fun getCryptoInfo(symbol: String) {
        viewModelScope.launch {
            val info = repository.getCryptoInfo(symbol)
            _cryptoInfo.value = info
        }
    }
}