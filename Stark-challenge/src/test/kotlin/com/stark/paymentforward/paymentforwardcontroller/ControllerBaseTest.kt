package com.stark.paymentforward.paymentforwardcontroller

import com.stark.paymentforward.service.TransferService
import com.stark.paymentforward.service.controller.PaymentForwardController
import io.mockk.clearMocks
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach

open class ControllerBaseTest {
    val transferServiceMock = mockk<TransferService>()

    val impl = PaymentForwardController(
        transferService = transferServiceMock
    )

    @AfterEach
    fun tearDown() {
        clearMocks(
            transferServiceMock
        )
    }
}