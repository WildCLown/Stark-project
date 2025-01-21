package com.stark.paymentforward.service.models

data class BankTransferOrder(
    val code: String,
    val branch: String,
    val accountNumber: String,
    val institutionName: String,
    val taxId: String,
    val accountType: String
)
