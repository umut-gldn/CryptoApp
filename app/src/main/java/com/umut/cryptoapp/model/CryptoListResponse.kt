package com.umut.cryptoapp.model

data class CryptoListResponse(
    val Data: List<CryptoData>,
    val Message: String,
    val Type: Int
)

data class CryptoData(
    val CoinInfo: CoinInfo,
    val RAW: Raw?
)

data class CoinInfo(
    val Name: String,
    val FullName: String,
    val ImageUrl: String
)

data class Raw(
    val USD: USDData?
)

data class USDData(
    val PRICE: Double?
)