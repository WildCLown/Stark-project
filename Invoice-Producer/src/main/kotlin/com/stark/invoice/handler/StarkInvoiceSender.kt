package com.stark.invoice.handler

import com.starkbank.Invoice

interface StarkInvoiceSender {
    fun createInvoice(
        name: String,
        amount: Long,
        taxId: String
    )
}