package com.stark.paymentforward.service.controller

import com.stark.paymentforward.service.TransferService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PaymentForwardController(
    private val transferService: TransferService
) {

    @PostMapping("/webhook")
    fun webhookCallback(
        @RequestBody payload: Map<String, Any>): String {
        val invoiceId = (payload["invoiceId"] as Number).toLong()
        val amount = (payload["amount"] as Number).toLong()

        transferService.processTransfer(invoiceId, amount)

        return "Processed"
    }

    @GetMapping("/ping")
    fun ping(): String {
        return "Pong"
    }
}
