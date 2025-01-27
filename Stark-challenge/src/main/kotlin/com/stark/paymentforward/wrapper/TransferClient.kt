package com.stark.paymentforward.wrapper

import com.starkbank.Transfer

interface TransferClient {
    fun create(transfers: List<Transfer>): List<Transfer>
}