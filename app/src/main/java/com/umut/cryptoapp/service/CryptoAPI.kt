package com.umut.cryptoapp.service

import com.umut.cryptoapp.model.CryptoDetailItem
import com.umut.cryptoapp.model.CryptoListItem
import com.umut.cryptoapp.model.CryptoListResponse
import com.umut.cryptoapp.util.Constants
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CryptoAPI {

    @GET(Constants.CRYPTO_LIST_ENDPOINT)
    suspend fun getCryptoList(
        @Query("limit") limit: Int = 100,
        @Query("tsym") targetSymbol: String = "USD",
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): CryptoListResponse

    @GET(Constants.CRYPTO_PRICE_ENDPOINT)
    suspend fun getCryptoPrice(
        @Query("fsym") fromSymbol: String,
        @Query("tsyms") toSymbol: String = "USD",
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Map<String, Double>
}