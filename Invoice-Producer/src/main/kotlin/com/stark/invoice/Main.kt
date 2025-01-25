package org.example.com.stark.invoice

import com.stark.invoice.handler.impl.NameGeneratorImpl
import com.stark.invoice.handler.impl.StarkInvoiceSenderImpl
import com.stark.invoice.service.InvoiceEmitterProcessor
import com.stark.invoice.wrapper.impl.RandomProviderImpl
import com.starkbank.Project
import com.starkbank.Settings

fun main() {
    starkConfig()
    val nameGenerator = NameGeneratorImpl()
    val sendIntervalMinutes = System.getenv("STARKBANK_INVOICE_INTERVAL")?.toLong() ?: 180L
    val dailyMinutes = System.getenv("STARKBANK_RUNTIME_INTERVAL")?.toLong() ?: 1440
    val invoiceSender = StarkInvoiceSenderImpl()
    val randomProvider = RandomProviderImpl()

    val processor = InvoiceEmitterProcessor(
        invoiceSender = invoiceSender,
        nameGenerator = nameGenerator,
        emitRange = sendIntervalMinutes,
        dailyMinutes = dailyMinutes,
        randomProvider = randomProvider
    )

    processor.start()
}

private fun starkConfig() {
    val privateKey = System.getenv("STARKBANK_PRIVATE_KEY")
        ?: throw IllegalArgumentException("STARKBANK_PRIVATE_KEY environment variable not set")
    val projectId = System.getenv("STARKBANK_PROJECT_ID")
        ?: throw IllegalArgumentException("STARKBANK_PROJECT_ID environment variable not set")
    val environment = System.getenv("STARKBANK_ENVIRONMENT")
        ?: throw IllegalArgumentException("STARKBANK_ENVIRONMENT environment variable not set")

    val user = Project(
        environment,
        projectId,
        privateKey
    )

    Settings.user = user
}
