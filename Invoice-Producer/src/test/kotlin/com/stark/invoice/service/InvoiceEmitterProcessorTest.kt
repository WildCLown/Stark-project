package com.stark.invoice.service

import com.stark.invoice.handler.NameGenerator
import com.stark.invoice.handler.StarkInvoiceSender
import com.stark.invoice.wrapper.RandomProvider
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InvoiceEmitterProcessorTest {
    private val nameGeneratorMockk = mockk<NameGenerator>()
    private val starkInvoiceSenderMockk = mockk<StarkInvoiceSender>()
    private val randomProviderMockk = mockk<RandomProvider>()

    @AfterEach
    fun tearDown() {
        clearMocks(
            nameGeneratorMockk,
            starkInvoiceSenderMockk,
            randomProviderMockk
        )
    }

    @Test
    fun `throws when range is greater than daily run`() {
        val processor = InvoiceEmitterProcessor(
            invoiceSender = starkInvoiceSenderMockk,
            nameGenerator = nameGeneratorMockk,
            emitRange = 5,
            dailyMinutes = 2,
            randomProvider = randomProviderMockk
        )

        assertThrows<IllegalArgumentException> {
            processor.start()
        }
    }

    @Test
    fun `should call createInvoice 3 times with given parameters`() {
        every {
            randomProviderMockk.nextInt(8,12)
        } returns 200

        // Not the ideal, but it will reduce test time
        // would think in a solution later
        // Also, About the RandomProvider, it could use MockStatic or MockObject
        // don't really remember, but in order to speed up, I wrapped it out.

        every {
            nameGeneratorMockk.generateNames(200)
        } returns listOf("Pei", "Pow", "Pum")

        every {
            randomProviderMockk.nextLong(100, 1000)
        } returnsMany listOf(200L, 300L, 400L)

        every { // For a restricted test, avoid using any()
            starkInvoiceSenderMockk.createInvoice(any(), any(), any())
        } returns Unit

        val processor = InvoiceEmitterProcessor(
            invoiceSender = starkInvoiceSenderMockk,
            nameGenerator = nameGeneratorMockk,
            emitRange = 1,
            dailyMinutes = 1,
            randomProvider = randomProviderMockk
        )

        processor.start()

        verifySequence {
            randomProviderMockk.nextInt(8,12)
            nameGeneratorMockk.generateNames(200)
            randomProviderMockk.nextLong(100, 1000)
            starkInvoiceSenderMockk.createInvoice(
                name = "Pei",
                amount = 200L,
                taxId = TAX_ID
            )
            randomProviderMockk.nextLong(100, 1000)
            starkInvoiceSenderMockk.createInvoice(
                name = "Pow",
                amount = 300L,
                taxId = TAX_ID
            )
            randomProviderMockk.nextLong(100, 1000)
            starkInvoiceSenderMockk.createInvoice(
                name = "Pum",
                amount = 400L,
                taxId = TAX_ID
            )
        }
        confirmVerified(starkInvoiceSenderMockk)
    }

    companion object {
        private const val TAX_ID = "26626283081"
    }
}
