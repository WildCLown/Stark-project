package com.stark.paymentforward.paymentforwardcontroller

import com.stark.paymentforward.fixture.WebhookEventFixture
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

class WebhookCallbackTest : ControllerBaseTest() {
    @Test
    fun `returns status ok for non-invoice calls`(){
        val expected = HttpStatus.OK
        val response = impl.webhookCallback(
            payload = NON_INVOICE_WEBHOOK
        )

        assertEquals(expected, response.statusCode)
    }

    @Test
    fun `returns status ok if not credited invoice event`(){
        val expected = HttpStatus.OK
        val response = impl.webhookCallback(
            payload = INVOICE_NON_CREDIT_WEBHOOk
        )

        assertEquals(expected, response.statusCode)
    }

    @Test
    fun `returns internal if transferService throws exception`(){
        val expected = HttpStatus.INTERNAL_SERVER_ERROR
        every {
            transferServiceMock.processTransfer(
                amountE2 = INVOICE_WEBHOOK.event?.log?.invoice?.amount,
                feeE2 = INVOICE_WEBHOOK.event?.log?.invoice?.fee
            )
        } throws Exception("Error")

        val response = impl.webhookCallback(
            payload = INVOICE_WEBHOOK
        )

        assertEquals(expected, response.statusCode)
        verify {
            transferServiceMock.processTransfer(
                amountE2 = INVOICE_WEBHOOK.event?.log?.invoice?.amount,
                feeE2 = INVOICE_WEBHOOK.event?.log?.invoice?.fee
            )
        }
    }

    @Test
    fun `happy path`(){
        val expected = HttpStatus.OK
        every {
            transferServiceMock.processTransfer(
                amountE2 = INVOICE_WEBHOOK.event?.log?.invoice?.amount,
                feeE2 = INVOICE_WEBHOOK.event?.log?.invoice?.fee
            )
        } returns Unit

        val response = impl.webhookCallback(
            payload = INVOICE_WEBHOOK
        )

        assertEquals(expected, response.statusCode)
        verify {
            transferServiceMock.processTransfer(
                amountE2 = INVOICE_WEBHOOK.event?.log?.invoice?.amount,
                feeE2 = INVOICE_WEBHOOK.event?.log?.invoice?.fee
            )
        }
    }

    companion object {
        private val INVOICE_WEBHOOK = WebhookEventFixture.webhookEvent
        private val INVOICE_NON_CREDIT_WEBHOOk = WebhookEventFixture.webhookEvent.copy(
            event = WebhookEventFixture.webhookEvent.event?.copy(
                log = WebhookEventFixture.webhookEvent.event?.log?.copy(
                    type = "non-credited"
                )
            )
        )
        private val NON_INVOICE_WEBHOOK = WebhookEventFixture.webhookEvent.copy(
            event = WebhookEventFixture.webhookEvent.event?.copy(
                subscription = "non-invoice"
            )
        )
    }
}