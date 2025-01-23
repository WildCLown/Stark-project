package com.stark.invoice.service

import com.stark.invoice.handler.NameGenerator
import com.stark.invoice.handler.StarkInvoiceSender
import kotlin.random.Random

class InvoiceEmitterProcessor(
    private val invoiceSender: StarkInvoiceSender,
    private val nameGenerator: NameGenerator,
    private val emitRange: Long = 180
) {
    private val dailyMinutes = 1440
    fun start() {
        // Normally I try to make my code as legible as possible
        // Although, I'm not that good with variable name, and I used kind of a math.. so I'll write down some comments

        if (emitRange < 0 || emitRange >= dailyMinutes) {
            throw IllegalArgumentException("Invalid Daily Minutes")
        }
        val loopRange = dailyMinutes / emitRange
        val rangeMs = emitRange * 60 * 1000
        // Above we have the range in milliseconds, and how many times we shall run for the given range
        // For 180 (3 hours) we will run 8 times

        for (i in 1..loopRange) {
            val invoicesQtt = Random.nextInt(8,12)
            val rangeDivided = rangeMs/invoicesQtt
            // Range Divided is basically the time we shall wait between each Invoice, in order to separate them
            // evenly during the interval range
            val names = nameGenerator.generateNames(invoicesQtt)
            println("All names that will be used for this range $names")

            names.forEach{
                println("Processing invoice for $it");
                invoiceSender.createInvoice(
                    name = it,
                    amount = Random.nextLong(100, 1000),
                    taxId = "26626283081"
                )
                Thread.sleep(rangeDivided)
            }
        }
    }
}

