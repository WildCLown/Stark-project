package com.stark.paymentforward.config

import com.stark.paymentforward.wrapper.TransferClient
import com.starkbank.Transfer
import org.springframework.stereotype.Component

@Component
class StarkTransferClient : TransferClient {
    override fun create(transfers: List<Transfer>): List<Transfer> {
        return Transfer.create(transfers)
    }
}