package com.stark.paymentforward.wrapper.impl

import com.stark.paymentforward.wrapper.TransferClient
import com.starkbank.Transfer

class TransferClientImpl : TransferClient {
    override fun create(transfers: List<Transfer>): List<Transfer> {
        return Transfer.create(transfers)
    }
}