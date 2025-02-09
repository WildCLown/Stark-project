package com.stark.invoice.service

import com.stark.invoice.handler.NameGenerator
import com.stark.invoice.handler.StarkInvoiceSender
import com.stark.invoice.wrapper.RandomProvider
import kotlin.random.Random

class InvoiceEmitterProcessor(
    private val invoiceSender: StarkInvoiceSender,
    private val nameGenerator: NameGenerator,
    private val randomProvider: RandomProvider,
    private val emitRange: Long = 180L,
    private val dailyMinutes: Long = 1440L
) {
    fun start() {
        if (emitRange <= 0 || emitRange > dailyMinutes) {
            throw IllegalArgumentException("Invalid Daily Minutes")
        }
        val loopRange = dailyMinutes / emitRange
        val rangeMs = emitRange * 60 * 1000

        println("We will run $loopRange times for the given range of $emitRange minutes")
        for (i in 1..loopRange) {
            val invoicesQtt = randomProvider.nextInt(8,12)
            val rangeDivided = rangeMs/invoicesQtt
            println("$invoicesQtt will be emmited, in intervals of $rangeDivided ms")
            val names = nameGenerator.generateNames(invoicesQtt)
            println("All names that will be used for this range $names")

            names.forEach{
                invoiceSender.createInvoice(
                    name = it,
                    amount = randomProvider.nextLong(100, 1000),
                    taxId = "26626283081" // Could use random TaxId, but for this example we will use a fixed one
                )

                Thread.sleep(rangeDivided)
            }
        }
    }
}

