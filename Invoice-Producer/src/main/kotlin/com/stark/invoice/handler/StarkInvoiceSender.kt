package com.stark.invoice.handler

interface StarkInvoiceSender {
    fun createInvoice(
        name: String,
        amount: Long,
        taxId: String
    )
}