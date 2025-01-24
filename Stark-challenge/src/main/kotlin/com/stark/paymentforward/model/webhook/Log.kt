package com.stark.paymentforward.model.webhook

data class Log(
    val authentication: String? = null,
    val created: String? = null,
    val errors: List<Any>? = null,
    val id: String? = null,
    val invoice: Invoice? = null,
    val type: String? = null
)

fun Log.isCredited(): Boolean {
    return this.type == "credited"
}
