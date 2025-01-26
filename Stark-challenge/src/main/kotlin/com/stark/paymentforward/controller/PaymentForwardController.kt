package com.stark.paymentforward.service.controller

import com.stark.paymentforward.model.webhook.WebhookEvent
import com.stark.paymentforward.model.webhook.isCredited
import com.stark.paymentforward.service.TransferService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PaymentForwardController(
    private val transferService: TransferService
) {

    @PostMapping("/webhook")
    fun webhookCallback(
        @RequestBody payload: WebhookEvent
    ): ResponseEntity<Void> {
        if(payload.event?.subscription == "invoice") {
            return ResponseEntity(invoiceEvents(payload))
        } else {
            println("Not invoice event, it was: [${payload.event?.subscription}]")
            return ResponseEntity(HttpStatus.OK)
        }
    }

    @GetMapping("/ping")
    fun ping(): ResponseEntity<String> {
        return ResponseEntity("Pong", HttpStatus.CREATED)
    }

    private fun invoiceEvents(
        payload: WebhookEvent
    ): HttpStatus {
        if(payload.event?.log?.isCredited() == true) {
            println("Credited event")
            try {
                transferService.processTransfer(
                    amountE2 = payload.event.log.invoice?.amount,
                    feeE2 = payload.event.log.invoice?.fee
                )
                return HttpStatus.OK
            } catch (ex: Exception) {
                return HttpStatus.INTERNAL_SERVER_ERROR
            }
        } else {
            println("Not credited event, it was: [${payload.event?.log?.type}]")
            return HttpStatus.OK
        }
    }
}
