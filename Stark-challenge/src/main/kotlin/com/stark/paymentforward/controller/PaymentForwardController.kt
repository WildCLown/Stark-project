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
        println("Received webhook event with following payload")
        println("$payload")
        if(payload.event?.log?.isCredited() == true) {
            println("Credited event")
            try {
                transferService.processTransfer(
                    amountE2 = payload.event.log.invoice?.amount,
                    feeE2 = payload.event.log.invoice?.fee
                )
                return ResponseEntity(HttpStatus.OK)
            } catch (ex: Exception) {
                return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else {
            println("Not credited event, it was: [${payload.event?.log?.type}]")
            return ResponseEntity(HttpStatus.OK)
        }
    }

    @GetMapping("/ping")
    fun ping(): ResponseEntity<String> {
        return ResponseEntity("Pong", HttpStatus.CREATED)
    }
}
