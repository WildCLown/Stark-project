package com.stark.paymentforward.service.transferservice

import com.stark.paymentforward.model.StarkBankInfo
import com.stark.paymentforward.service.TransferService
import com.stark.paymentforward.wrapper.TransferClient
import io.mockk.clearMocks
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach

open class ServiceBaseTest {
    val starkBankInfoMock = mockk<StarkBankInfo>()
    val transferClientMock = mockk<TransferClient>()

    val impl = TransferService(
        starkBankInfo = starkBankInfoMock,
        transferClient = transferClientMock
    )

    @AfterEach
    fun tearDown() {
         clearMocks(
             starkBankInfoMock
         )
    }
}