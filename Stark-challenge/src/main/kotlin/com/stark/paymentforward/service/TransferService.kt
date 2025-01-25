package com.stark.paymentforward.service

import com.stark.paymentforward.model.StarkBankInfo
import com.starkbank.Transfer
import org.springframework.stereotype.Service

@Service
class TransferService(
    private val starkBankInfo: StarkBankInfo
) {
    fun processTransfer(
        amountE2: Long?,
        feeE2: Long?
    ) {
        if(amountE2 == null || amountE2 <= 0) {
            throw IllegalArgumentException("Amount must be greater than 0")
        }

        val normalizedFee = feeE2 ?: 0
        val normalizedAmount = amountE2 - normalizedFee

        // I've seen some attributes such as 'fine' and 'interest'
        // Not sure if they should be taken from here... I'll assume not.

        if(normalizedAmount < 0) {
            throw IllegalArgumentException("Amount was lower than fee")
        } else if (normalizedAmount > 0) {
            val transfers = mutableListOf<Transfer>()
            val dataTransfer = hashMapOf<String, Any>(
                "amount" to normalizedAmount,
                "name" to starkBankInfo.name,
                "taxId" to starkBankInfo.taxId,
                "bankCode" to starkBankInfo.code,
                "branchCode" to starkBankInfo.branchCode,
                "accountNumber" to starkBankInfo.account,
                "accountType" to starkBankInfo.accountType,
            )

            transfers.add(Transfer(dataTransfer))
            val createdTransfer = Transfer.create(transfers)

            for (transfer in createdTransfer) {
                println("Transferência criada com sucesso: ${transfer.id}")
            }
        } // In case it's 0, we won't need to transfer a zero amount
    }
}
