package com.stark.paymentforward.model

data class StarkBankInfo(
    val name: String,
    val branchCode: String,
    val account: String,
    val code: String,
    val taxId: String,
    val accountType: String
)