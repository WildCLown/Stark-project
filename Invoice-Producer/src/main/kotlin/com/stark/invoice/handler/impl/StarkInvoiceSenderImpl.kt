package com.stark.invoice.handler.impl

import com.stark.invoice.handler.StarkInvoiceSender
import com.starkbank.Invoice

class StarkInvoiceSenderImpl: StarkInvoiceSender {
    override fun createInvoice(
        name: String,
        amount: Long,
        taxId: String
    ) {
        val invoices = mutableListOf<Invoice>()
        val dataInvoice = hashMapOf<String, Any>(
            "amount" to amount,
            "taxId" to taxId,
            "name" to name,
        )

        invoices.add(Invoice(dataInvoice))
        val createdInvoices = Invoice.create(invoices)
        for (invoice in createdInvoices) {
            println(invoice)
        }
    }
}