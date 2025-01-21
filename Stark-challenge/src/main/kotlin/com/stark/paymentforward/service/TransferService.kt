package com.stark.paymentforward.service

import com.stark.paymentforward.model.BankTransferOrder
import org.springframework.stereotype.Service

@Service
class TransferService() {

    fun processTransfer(invoiceId: Long, amountE2: Long): BankTransferOrder {

        val transfer = BankTransferOrder(
            code = "something",
            branch = "something",
            accountNumber = "something",
            institutionName = "something",
            taxId = "something",
            accountType = "something"
        )

        return transfer
    }
}
