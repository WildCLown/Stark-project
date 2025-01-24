package com.stark.paymentforward.model.webhook

data class Event(
    val created: String? = null,
    val id: String? = null,
    val log: Log? = null,
    val subscription: String? = null,
    val workspaceId: String? = null
)
