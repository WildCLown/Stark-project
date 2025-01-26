package com.stark.paymentforward.fixture

import com.stark.paymentforward.model.webhook.Log

object LogFixture {
    val log = Log(
        authentication = "",
        created = "2025-01-24T23:50:39.804830+00:00",
        errors = emptyList(),
        id = "5100421123145728",
        invoice = InvoiceFixture.invoice,
        type = "credited"
    )
}