package com.umut.cryptoapp.model

data class CryptoListItem(
    val currency: String,
    val fullName: String,
    val price: String,
    val imageUrl: String
)