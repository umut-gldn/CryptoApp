package com.umut.cryptoapp.repository


import CryptoDetailInfo
import android.util.Log
import com.umut.cryptoapp.model.CryptoDetailItem

import com.umut.cryptoapp.model.CryptoListItem
import com.umut.cryptoapp.service.CryptoAPI
import com.umut.cryptoapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.text.DecimalFormat
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api: CryptoAPI
) {
    private val decimalFormat = DecimalFormat("#,##0.00######")
    suspend fun getCryptoList(): Resource<List<CryptoListItem>> {
        return try {
            val response = api.getCryptoList()
            val cryptoList = response.Data.mapNotNull { data ->
                val price = data.RAW?.USD?.PRICE
                if (price != null) {
                    CryptoListItem(
                        currency = data.CoinInfo.Name,
                        fullName = data.CoinInfo.FullName,
                        price = formatPrice(price),
                        imageUrl = "https://www.cryptocompare.com${data.CoinInfo.ImageUrl}"
                    )
                } else null
            }
            Resource.Success(cryptoList)
        } catch (e: Exception) {
            Resource.Error("Bir hata oluştu: ${e.localizedMessage}")
        }
    }

    suspend fun getCryptoPrice(symbol: String): Resource<String> {
        return try {
            val response = api.getCryptoPrice(symbol)
            val price = response["USD"] ?: 0.0
            Resource.Success(formatPrice(price))
        } catch (e: Exception) {
            Resource.Error("Bir hata oluştu: ${e.localizedMessage}")
        }
    }

    private fun formatPrice(price: Double): String {
        return when {
            price < 0.000001 -> "%.8f".format(price)
            price < 1 -> "%.6f".format(price)
            else -> "%.2f".format(price)
        }
    }
    suspend fun getCryptoInfo(symbol: String): CryptoDetailInfo {
        return try {
            val response = api.getCryptoList()
            val cryptoData = response.Data.find { it.CoinInfo.Name == symbol }
            if (cryptoData != null) {
                CryptoDetailInfo(
                    fullName = cryptoData.CoinInfo.FullName,
                    symbol = cryptoData.CoinInfo.Name,
                    imageUrl = "https://www.cryptocompare.com${cryptoData.CoinInfo.ImageUrl}"
                )
            } else {
                CryptoDetailInfo("Unknown", symbol, "")
            }
        } catch (e: Exception) {
            CryptoDetailInfo("Error", symbol, "")
        }
    }
}
