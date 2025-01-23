package org.example.com.stark.invoice

import com.stark.invoice.handler.impl.NameGeneratorImpl
import com.stark.invoice.handler.impl.StarkInvoiceSenderImpl
import com.stark.invoice.service.InvoiceEmitterProcessor

fun main() {
    val nameGenerator = NameGeneratorImpl()
    val sendIntervalMinutes = 1L
    val invoiceSender = StarkInvoiceSenderImpl()

    val processor = InvoiceEmitterProcessor(
        invoiceSender = invoiceSender,
        nameGenerator = nameGenerator,
        emitRange = sendIntervalMinutes
    )

    processor.start()
}
