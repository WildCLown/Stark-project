package com.stark.paymentforward.service

import com.starkbank.Transfer
import org.springframework.stereotype.Service

@Service
class TransferService() {
    private val bankName = "Stark Bank S.A."
    private val branchCode = "0001"
    private val bankAccount = "6341320293482496"
    private val bankCode = "20018183"
    private val bankTaxId = "20.018.183/0001-80"
    private val bankAccountType = "payment"

    fun processTransfer(
        amountE2: Long?,
        feeE2: Long?
    ) {
        if(amountE2 == null || amountE2 <= 0) {
            throw IllegalArgumentException("Amount must be greater than 0")
        }

        val normalizedFee = feeE2 ?: 0
        val normalizedAmount = amountE2 - normalizedFee
        if(normalizedAmount < 0) {
            throw IllegalArgumentException("Amount was lower than fee")
        } else if (normalizedAmount > 0) {
            val transfers = mutableListOf<Transfer>()
            val dataTransfer = hashMapOf<String, Any>(
                "amount" to amountE2,
                "name" to bankName,
                "taxId" to bankTaxId,
                "bankCode" to bankCode,
                "branchCode" to branchCode,
                "accountNumber" to bankAccount,
                "accountType" to bankAccountType,
            )

            transfers.add(Transfer(dataTransfer))
            val createdTransfer = Transfer.create(transfers)

            for (transfer in createdTransfer) {
                println("Transferência criada com sucesso: ${transfer.id}")
            }
        } // In case it's 0, we won't need to transfer a zero amount
    }
}
