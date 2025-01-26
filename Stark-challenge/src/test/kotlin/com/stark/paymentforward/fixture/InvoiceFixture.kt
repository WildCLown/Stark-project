package com.stark.paymentforward.fixture

import com.stark.paymentforward.model.webhook.Invoice

object InvoiceFixture {
    val invoice = Invoice(
        amount = 743,
        brcode = "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/1c5e22c27bfb4edfbb4d664515680b0f5204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***6304A2FC",
        created = "2025-01-24T23:50:39.702103+00:00",
        descriptions = emptyList(),
        discountAmount = 0,
        discounts = emptyList(),
        displayDescription = "",
        due = "2025-01-26T23:50:39.676362+00:00",
        expiration = 5097600,
        fee = 0L,
        fine = 2,
        fineAmount = 0,
        id = "5663371076567040",
        interest = 1,
        interestAmount = 0,
        link = "https://challenge-gabriel-almeida.sandbox.starkbank.com/invoicelink/1c5e22c27bfb4edfbb4d664515680b0f",
        name = "Anna Martinez",
        nominalAmount = 743,
        pdf = "https://sandbox.api.starkbank.com/v2/invoice/1c5e22c27bfb4edfbb4d664515680b0f.pdf",
        rules = emptyList(),
        splits = emptyList(),
        status = "created",
        tags = emptyList(),
        taxId = "266.262.830-81",
        transactionIds = emptyList(),
        updated = "2025-01-24T23:50:39.804883+00:00"
    )
}