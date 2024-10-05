package com.umut.cryptoapp.model

data class CryptoDetailItem(
    val id: String,
    val name: String,
    val currency: String,
    val price: String,
    val logo_url: String  // Tüm kripto paralar için aynı logo URL'sini kullanacağız
)