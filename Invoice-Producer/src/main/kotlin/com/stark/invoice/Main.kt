package org.example.com.stark.invoice

import com.stark.invoice.handler.impl.NameGeneratorImpl
import com.stark.invoice.handler.impl.StarkInvoiceSenderImpl
import com.stark.invoice.service.InvoiceEmitterProcessor
import com.starkbank.Project
import com.starkbank.Settings
import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    starkConfig()
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

private fun starkConfig(){
    val privateKeyPath = "src/main/resources/private_key.pem"
    val privateKey = Files.readString(Paths.get(privateKeyPath)) // mudar para System.getenv("STARKBANK_PRIVATE_KEY")

    val projectId = "6040968771928064" // mudar para System.getenv("STARKBANK_PROJECT_ID")
    val environment = "sandbox" // mudar para System.getenv("STARKBANK_ENVIRONMENT")

    val user = Project(
        environment,
        projectId,
        privateKey
    )

    Settings.user = user
}
