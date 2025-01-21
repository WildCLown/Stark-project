package com.stark.paymentforward

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaymentForwardApplication

fun main(args: Array<String>) {
    runApplication<PaymentForwardApplication>(*args)
}