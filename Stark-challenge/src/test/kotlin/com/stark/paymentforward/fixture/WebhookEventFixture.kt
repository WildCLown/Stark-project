package com.stark.paymentforward.fixture

import com.stark.paymentforward.model.webhook.Event
import com.stark.paymentforward.model.webhook.WebhookEvent

object WebhookEventFixture {
    val webhookEvent = WebhookEvent(
        event = Event(
            created = "2025-01-24T23:50:40.105244+00:00",
            id = "5844958483316736",
            log = LogFixture.log,
            subscription = "invoice",
            workspaceId = "6276212603224064"
        )
    )
}