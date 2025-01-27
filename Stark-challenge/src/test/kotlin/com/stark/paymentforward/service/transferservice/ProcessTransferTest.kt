package com.stark.paymentforward.service.transferservice

import io.mockk.every
import io.mockk.verifySequence
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProcessTransferTest: ServiceBaseTest() {
    @Test
    fun `throws IllegalArgumentException when given amount is negative or null`() {
        assertThrows<IllegalArgumentException> {
            impl.processTransfer(null, 0)
        }

        assertThrows<IllegalArgumentException> {
            impl.processTransfer(-1, 0)
        }
    }

    @Test
    fun `throws IllegalArgumentException when fees are greater than amount`() {
        assertThrows<IllegalArgumentException> {
            impl.processTransfer(2, 500)
        }
    }

    @Test
    fun `happy path`() {

        every { starkBankInfoMock.name } returns "Test Bank"
        every { starkBankInfoMock.branchCode } returns "0001"
        every { starkBankInfoMock.account } returns "12345-6"
        every { starkBankInfoMock.code } returns "001"
        every { starkBankInfoMock.taxId } returns "12345678900"
        every { starkBankInfoMock.accountType } returns "checking"
        every { transferClientMock.create(any()) } returns emptyList()

        impl.processTransfer(1000, 100)

        verifySequence {
            starkBankInfoMock.name
            starkBankInfoMock.taxId
            starkBankInfoMock.code
            starkBankInfoMock.branchCode
            starkBankInfoMock.account
            starkBankInfoMock.accountType
            transferClientMock.create(any())
        }
    }
}